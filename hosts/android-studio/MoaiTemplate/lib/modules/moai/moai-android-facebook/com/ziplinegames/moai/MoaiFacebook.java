//----------------------------------------------------------------//
// Copyright (c) 2010-2011 Zipline Games, Inc. 
// All Rights Reserved. 
// http://getmoai.com
//----------------------------------------------------------------//

package com.ziplinegames.moai;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.facebook.AppEventsLogger;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.facebook.FacebookException;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Request.Callback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.UiLifecycleHelper;

//================================================================//
// MoaiFacebook
//================================================================//
public class MoaiFacebook {

	private static Activity sActivity = null;
	private static AppEventsLogger sLogger = null;
	private static UiLifecycleHelper sUiLifecycleHelper;

	protected static native void	AKUDialogDidNotComplete ();
	protected static native void	AKUDialogDidComplete ( String result );
	protected static native void	AKUPermissionsDenied ( String error );
	protected static native void	AKUPermissionsGranted ();
	protected static native void	AKUReceivedRequestResponse ( boolean status, String result, int callbackIdx );
	protected static native void	AKUSessionDidLogin ();
	protected static native void	AKUSessionDidNotLogin ();
	
	private static final int 		REQUEST_READ_PERMISSION = 007;
	private static final int 		REQUEST_PUBLISH_PERMISSION = 666;
	private static final 			List<String> PERMISSIONS = Arrays.asList("publish_actions");
	private static final 			String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";
	private static boolean 			pendingPublishReauthorization = false;

	//----------------------------------------------------------------//
	private static boolean isSubsetOf ( Collection < String > subset, Collection < String > superset ) {
	    for ( String string : subset ) { if ( !superset.contains ( string ) ) { return false; } }
	    return true;
	}

	//----------------------------------------------------------------//
	public static void onActivityResult ( int requestCode, int resultCode, Intent data ) {
		
		MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: onActivityResult : requestCode::"+requestCode+" resultCode::"+resultCode+" -------------------------------------------------------- " );
		
		if ( requestCode == REQUEST_PUBLISH_PERMISSION || requestCode == REQUEST_READ_PERMISSION ) {
			
			if ( resultCode != 0 ) {
				
				MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: requestPublishPermissions : OK -------------------------------------------------------- " );
				
				synchronized ( Moai.sAkuLock ) {
			
					AKUPermissionsGranted ( );
				}
			} else {
				
				MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: requestPublishPermissions : FAIL -------------------------------------------------------- " );
				
				synchronized ( Moai.sAkuLock ) {
					
					AKUPermissionsDenied ( "ERROR" );
				}
			}
		}
		
		sUiLifecycleHelper.onActivityResult ( requestCode, resultCode, data );
	}

	//----------------------------------------------------------------//
	public static void requestPublishPermissions( Activity activity, Session session, List < String > permissions, int requestCode ) {
		
		if ( session != null ) {
			
			Session.NewPermissionsRequest reauthRequest = new Session.NewPermissionsRequest ( activity, permissions )
			
			.setRequestCode ( requestCode );

			//reauthRequest.setVersion("v1.0");
			
			session.requestNewPublishPermissions ( reauthRequest );
		}
	}

	//----------------------------------------------------------------//
	public static void requestReadPermissions ( Activity activity, Session session, List < String > permissions, int requestCode ) {
		
		if ( session != null ) {
			
			Session.NewPermissionsRequest reauthRequest = new Session.NewPermissionsRequest ( activity, permissions )
			
			.setRequestCode ( requestCode );

			//reauthRequest.setVersion("v1.0");
			
			session.requestNewReadPermissions ( reauthRequest );
		}
	}
	
	//----------------------------------------------------------------//
	public static void onCreate ( Activity activity ) {
		
		MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook onCreate: Initializing Facebook Ui Lifecycle Helper -------------------------------------------------------- " );
		
		sActivity = activity;
		
		sUiLifecycleHelper = new UiLifecycleHelper ( sActivity, null );
		
		sUiLifecycleHelper.onCreate ( null );

		Settings.setPlatformCompatibilityEnabled(true);

		sLogger = AppEventsLogger.newLogger ( sActivity );
				
	}
	
	//----------------------------------------------------------------//
	public static void onStart( ) {

		MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook onStart:  -------------------------------------------------------- " );
	}

	//----------------------------------------------------------------//
	public static void onStop ( ) {
		
		MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook onStop:  -------------------------------------------------------- " );
		
		sUiLifecycleHelper.onStop ();
	}
	
	//----------------------------------------------------------------//
	public static void onResume ( ) {

		MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook onResume:  -------------------------------------------------------- " );
		
		sUiLifecycleHelper.onResume();
	}
	
	//----------------------------------------------------------------//
	public static void onPause ( ) {

		MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook onPause:  -------------------------------------------------------- " );
		
		sUiLifecycleHelper.onPause ();
	}
	
	//----------------------------------------------------------------//
	public static void onDestroy ( ) {

		MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook onDestroy:  -------------------------------------------------------- " );
		
		sUiLifecycleHelper.onDestroy ();
	}

	//----------------------------------------------------------------//
	public static void onSaveInstanceState ( Bundle outState ) {
		
		MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook onSaveInstanceState:  -------------------------------------------------------- " );
		
		sUiLifecycleHelper.onSaveInstanceState ( outState );
	}

	//----------------------------------------------------------------//	
	
	//----------------------------------------------------------------//	
	public static void extendToken () {

		//nothing to do
	}

	//----------------------------------------------------------------//	
	public static long getExpirationDate () {

		Session mCurrentSession = null;
	    
	    mCurrentSession = Session.getActiveSession ();
	    
	    if ( isSessionValid ( mCurrentSession ) ) {
	    	
	    	if ( mCurrentSession.getAccessToken () != null ) {
	    		
	    		return mCurrentSession.getExpirationDate().getTime();
	    	} else {
	    		
	    		return 0;
	    	}
	    }
	    
		return 0;
	}

	//----------------------------------------------------------------//	
	public static String getToken () {
		
		Session mCurrentSession = null;
	    
	    mCurrentSession = Session.getActiveSession ();
	    
	    if ( isSessionValid ( mCurrentSession ) ) {
	    	
	    	if ( mCurrentSession.getAccessToken () != null ) {
	    		
	    		return mCurrentSession.getAccessToken ();
	    	} else {
	    		
	    		return "";
	    	}
	    }
	    
	    return "";
	}

    //----------------------------------------------------------------//
    public static void graphRequest ( String method, String path, Bundle params, final int callbackIdx ) {
        final Session mCurrentSession = Session.getActiveSession ();

        if ( sessionValid() ) {
            final Request graphRequest = new Request ( mCurrentSession, path, params, HttpMethod.valueOf ( method ), new Callback () {

                public void onCompleted ( Response response ) {
                    if ( response.getError () != null ) {
                        synchronized ( Moai.sAkuLock ) {
                            AKUReceivedRequestResponse ( true, response.getError().getErrorMessage(), callbackIdx );
                        }
                    } else {
                        synchronized ( Moai.sAkuLock ) {
                            AKUReceivedRequestResponse ( false, response.getGraphObject().getInnerJSONObject().toString(), callbackIdx );
                        }
                    }
                }
            } );
            // graphRequest.setVersion("v1.0");
            sActivity.runOnUiThread(new Runnable() {
                public void run() {
                    graphRequest.executeAsync();
                }
            });
        }
    }

	//----------------------------------------------------------------//	
	public static void init ( String appId ) {
		
		//MoaiLog.i ( "MoaiFacebook appId = "+mCurrentSession.getApplicationId () );
	    //nothing to do
	    // = AppEventsLogger.newLogger ( sActivity );
	}
	
	//----------------------------------------------------------------//	
	public static boolean restoreSession () {
		
		Session mCurrentSession = null;

		mCurrentSession = Session.restoreSession(sActivity, null, null, null);
	    
		if ( mCurrentSession == null || mCurrentSession.getState ().isClosed () ) {
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: cannot restore session! -------------------------------------------------------- " );
			
			return false;
		}

	    if ( !mCurrentSession.isOpened () ) {
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: failed restore session! -------------------------------------------------------- " );
			
	    	return false;
	    } else { 
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: session successufully restored! -------------------------------------------------------- " );
			
	    	return true;
	    }
	}
	
	//----------------------------------------------------------------//	
	public static boolean sessionValid () {
		
		Session mCurrentSession = null;
	    
	    mCurrentSession = Session.getActiveSession ();

		if ( mCurrentSession == null) {
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: session is not valid 'cause is null! -------------------------------------------------------- " );
			
			return false;
		}
		
		if ( mCurrentSession.getState ().isClosed () ) { 
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: session is not valid 'cause is closed! -------------------------------------------------------- " );
			
			return false;
		}

		if ( !mCurrentSession.isOpened () ) {
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: session is not valid 'cause is not opened! -------------------------------------------------------- " );
			
			return false;
		}
		    
		if ( mCurrentSession.getAccessToken() == null ) {
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: session is not valid! -------------------------------------------------------- " );
			
			return false;
		} else {
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: session is valid! -------------------------------------------------------- " );
			
			return true;
		}
	}

	//----------------------------------------------------------------//	
	private static boolean isSessionValid ( Session session ) {
			
		Session mCurrentSession = session;

		if ( mCurrentSession == null) {
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: session is not valid 'cause is null! -------------------------------------------------------- " );
			
			return false;
		}
		
		if ( mCurrentSession.getState ().isClosed () ) { 
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: session is not valid 'cause is closed! -------------------------------------------------------- " );
			
			return false;
		}

		if ( !mCurrentSession.isOpened () ) {
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: session is not valid 'cause is not opened! -------------------------------------------------------- " );
			
			return false;
		}
		    
		if ( mCurrentSession.getAccessToken() == null ) {
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: session is not valid! -------------------------------------------------------- " );
			
			return false;
		} else {
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: session is valid! -------------------------------------------------------- " );
			
			return true;
		}
	}

    //----------------------------------------------------------------//
    public static void logEvent ( String eventName, double valueToSum, Bundle params ) {

        MoaiLog.i ( "MoaiFacebook: logEvent --- event = "+eventName+", value = "+valueToSum );
        sLogger.logEvent(eventName, valueToSum, params);
    }

    //----------------------------------------------------------------//
    public static void logPurchase ( double money, String currency, Bundle params ) {

        MoaiLog.i ( "MoaiFacebook: logPurchase --- event = "+money+", value = "+currency );
        sLogger.logPurchase(BigDecimal.valueOf(money), Currency.getInstance(currency), params);
    }

	//----------------------------------------------------------------//	
	public static void login ( String [] permissions, boolean allowUI ) {
		
		MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: Facebook trying to login! Allow UI is: "+allowUI+" -------------------------------------------------------- " );
		
		Session result = Session.openActiveSession ( sActivity, allowUI, new Session.StatusCallback () {
			
			public void call ( Session session, SessionState state, Exception exception ) {
				
				if ( session != null ) {
					
					MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: Login state = "+state.name()+"! -------------------------------------------------------- " );
					
					if ( state.equals ( SessionState.CREATED_TOKEN_LOADED ) || state.equals ( SessionState.OPENED_TOKEN_UPDATED ) || state.equals ( SessionState.OPENED ) || state.equals ( SessionState.CREATED ) ) {
						
						if ( session.isOpened () ) {
							
							MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: Session login successfully! -------------------------------------------------------- " );
							
							synchronized ( Moai.sAkuLock ) {
								
								AKUSessionDidLogin ();
							}
				        }
					} else if ( state.equals ( SessionState.CLOSED_LOGIN_FAILED ) ) {
						
						MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: Session login failed! -------------------------------------------------------- " );
						
						synchronized ( Moai.sAkuLock ) {
							
							AKUSessionDidNotLogin ();
						}
					}
				} else {
					
					MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: Session login is null! -------------------------------------------------------- " );
					
					synchronized ( Moai.sAkuLock ) {
						
						AKUSessionDidNotLogin ();
					}
		        }
		    }
		});
		
		if ( !allowUI ) {
			
			if ( result != null ) {
				
				MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: Session login successfully! -------------------------------------------------------- " );
				
				synchronized ( Moai.sAkuLock ) {
					
					AKUSessionDidLogin ();
				}
			} else {
				
				MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: Session login is null! -------------------------------------------------------- " );
				
				synchronized ( Moai.sAkuLock ) {
					
					AKUSessionDidNotLogin ();
				}
			}
		}
	}
	
	//----------------------------------------------------------------//	
	public static String [] getPermissions ( ) {

		Session mCurrentSession = null;
		
	    mCurrentSession = Session.getActiveSession ();

		if ( isSessionValid ( mCurrentSession ) ) {

			List < String > permissionList = mCurrentSession.getPermissions ();
			
			String [] permission = new String [ permissionList.size () ];
			
			permission = permissionList.toArray ( permission );
			
			for ( String s : permission )
				MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: permission : "+s+" -------------------------------------------------------- " );
			
			return permission;
		} else {
			
			return new String [] { "" };
		}
	}

	//----------------------------------------------------------------//	
	public static void requestReadPermissions ( String [] permissions ) {

		Session mCurrentSession = null;
		
	    mCurrentSession = Session.getActiveSession ();

		if ( isSessionValid ( mCurrentSession ) ) {
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: requestReadPermissions : requesting...  -------------------------------------------------------- " );
			
			requestReadPermissions ( sActivity, mCurrentSession, Arrays.asList ( permissions ), REQUEST_READ_PERMISSION );
		} else {
			
			MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: requestReadPermissions : FAIL -------------------------------------------------------- " );
		}
	}

	//----------------------------------------------------------------//	
	public static boolean hasGranted ( String permission ) {

		Session mCurrentSession = null;
		
	    mCurrentSession = Session.getActiveSession ();

		if ( isSessionValid ( mCurrentSession ) ) {
			
			return mCurrentSession.isPermissionGranted(permission);
		}

		return false;
	}

	//----------------------------------------------------------------//	
	public static void logout () {

		Session mCurrentSession = null;
	    
	    mCurrentSession = Session.getActiveSession ();
	    
	    if ( isSessionValid ( mCurrentSession ) ) {
	    	
	    	if ( mCurrentSession.getAccessToken () != null ) {
	    		
	    		mCurrentSession.closeAndClearTokenInformation ();
	    	} 
	    }
	}
	
	//----------------------------------------------------------------//	
	public static void postToFeed ( final String link, final String picture, final String name, final String caption, final String description, final String message ) {
		
		Session mCurrentSession = null;
		
	    mCurrentSession = Session.getActiveSession ();

		if ( !isSessionValid ( mCurrentSession ) ) {

			synchronized ( Moai.sAkuLock ) {
			
				AKUDialogDidComplete ( null );
			}
			
			return;
		}
        sActivity.runOnUiThread(new Runnable() {

			public void run() {

				final Bundle parameters = new Bundle ();
				
				if ( link != null )	parameters.putString ( "link", link );
				if ( picture != null )	parameters.putString ( "picture", picture );
				if ( name != null )	parameters.putString ( "name", name );
				if ( caption != null )	parameters.putString ( "caption", caption );
				if ( description != null )	parameters.putString ( "description", description );
				if ( message != null )	parameters.putString ( "message", message );
				
		        WebDialog feedDialog = ( new WebDialog.FeedDialogBuilder ( sActivity, Session.getActiveSession (), parameters ) )
		        		.setOnCompleteListener ( new OnCompleteListener () {

							public void onComplete ( Bundle values, FacebookException error ) {
								
								if ( error != null ) {  
									
									synchronized ( Moai.sAkuLock ) { 
										
										AKUDialogDidNotComplete ();
									}
									
									MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: Posting error -------------------------------------------------------- " );
								} else { 
									
									String postId = "{ \"post_id\" : \""+values.getString("post_id")+"\" }";
									
									synchronized ( Moai.sAkuLock ) {

										AKUDialogDidComplete ( postId );
									}

									MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: Posting completed successfully : "+postId+" -------------------------------------------------------- " );
								}
							}
		        	
		        		} )
		        		.build ();
		        
		        feedDialog.show ();
			}
    	});
	}
	
	//----------------------------------------------------------------//	
	public static void sendRequest ( final String message, final Bundle params ) {

		final Session mCurrentSession = Session.getActiveSession ();
		
		if ( isSessionValid ( mCurrentSession ) ) {
			

	        sActivity.runOnUiThread(new Runnable() {
				public void run() {

				params.putString ( "message", message );
	    	
	    		WebDialog inviteDialog = ( new WebDialog.Builder ( sActivity, mCurrentSession, "apprequests",  params ) ) 
	    		.setOnCompleteListener ( new OnCompleteListener () {

	    			public void onComplete ( Bundle values, FacebookException error ) {
					
	    				if ( error != null ) {  
						
	    					synchronized ( Moai.sAkuLock ) {
							
	    						AKUDialogDidNotComplete ();
	    					}
						
	    					MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: Invite error 'cause : "+error.getMessage()+" -------------------------------------------------------- " );
	    				} else { 
	    					
	    					synchronized ( Moai.sAkuLock ) {
	    						
	    						AKUDialogDidComplete ( "fbconnect://success?request="+values.getString("request")+"&to[0]="+values.getString("to[0]") );
	    					}

	    					MoaiLog.i ( " -------------------------------------------------------- MoaiFacebook: Invite completed successfully: result = "+"fbconnect://success?request="+values.getString("request")+"&to[0]="+values.getString("to[0]")+" -------------------------------------------------------- " );
	    				}
	    			}
    	
	    		} )
	    		.build ();
	    	
	    		inviteDialog.show ();
	    	
				}
	    	});
	    } else {

			synchronized ( Moai.sAkuLock ) {

				AKUDialogDidNotComplete ();
			}
	    }
	}
	
	//----------------------------------------------------------------//	
	public static void setToken ( String token ) {

		//nothing to do
	}
	
	//----------------------------------------------------------------//	
	public static void setExpirationDate ( long expires ) {

		//nothing to do
	}
}
