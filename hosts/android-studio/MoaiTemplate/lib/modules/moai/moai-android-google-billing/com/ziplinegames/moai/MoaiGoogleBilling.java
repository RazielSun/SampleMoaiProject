//----------------------------------------------------------------//
// Copyright (c) 2010-2011 Zipline Games, Inc. 
// All Rights Reserved. 
// http://getmoai.com
//----------------------------------------------------------------//

package com.ziplinegames.moai;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.android.vending.billing.IInAppBillingService;
import android.app.PendingIntent;
import android.content.ServiceConnection;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

//================================================================//
// MoaiGoogleBilling
//================================================================//
public class MoaiGoogleBilling {

	private static Activity 				sActivity = null;
	private static IInAppBillingService		sService = null;
	private static ServiceConnection		sServiceConn = null;
	
	public static String 					sCurrentSKU = "";
	
	private static boolean					sInAppSupported = false;
	private static boolean					sSubscriptionSupported = false;
	
	// purchase types
	public static final String				PURCHASE_TYPE_INAPP = "inapp";
	public static final String				PURCHASE_TYPE_SUBSCRIPTION = "subs";
	
	// Billing response codes
    public static final int 				BILLING_RESPONSE_RESULT_OK = 0;
    public static final int					BILLING_RESPONSE_RESULT_USER_CANCELED = 1;
    public static final int 				BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3;
    public static final int 				BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4;
    public static final int 				BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5;
    public static final int 				BILLING_RESPONSE_RESULT_ERROR = 6;
    public static final int 				BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7;
    public static final int 				BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 8;

	// AKU callbacks	
	protected static native void 			AKUNotifyGoogleBillingSupported				( boolean supported );
	protected static native void 			AKUNotifyGooglePurchaseResponseReceived 	( int responseCode, String sku, String purchaseToken );
	protected static native void 			AKUNotifyGooglePurchaseStateChanged			( int code, String jidentifier, String jorder, String jnotification, String jpayload );
	protected static native void 			AKUNotifyGoogleRestoreResponseReceived		( int code );
	
	//----------------------------------------------------------------//
	public static void onCreate ( Activity activity ) {
		
		MoaiLog.i ( "MoaiGoogleBilling v3 onCreate: Initializing Google Billing" );
		
		sActivity = activity;
		
		sServiceConn = new ServiceConnection () {
			
			//--------------------------------------------------------// 
			@Override
			public void onServiceDisconnected ( ComponentName name ) {
				
				MoaiLog.i ( "MoaiGoogleBilling : onServiceDisconnected" );
				sService = null;
			}

			//--------------------------------------------------------//			
			@Override
			public void onServiceConnected ( ComponentName name, IBinder service ) {
				
				MoaiLog.i ( "MoaiGoogleBilling : onServiceConnected" );
				
				sService = IInAppBillingService.Stub.asInterface ( service );
				
				// check supported billing types
				try {
					
					String packageName = sActivity.getPackageName ();
				
					// in app purchases
					int response = sService.isBillingSupported ( 3, packageName, PURCHASE_TYPE_INAPP );
	                if ( response == BILLING_RESPONSE_RESULT_OK ) {
						sInAppSupported = true;
						MoaiLog.i ( "MoaiGoogleBilling : In-app supported" );
						sInAppSupported = true;
					} else {
						sInAppSupported = false;
						MoaiLog.i ( "MoaiGoogleBilling : In-app not supported" );
						sInAppSupported = false;
					}
				
					// subscriptions
					response = sService.isBillingSupported (3, packageName, PURCHASE_TYPE_SUBSCRIPTION );
	                if ( response == BILLING_RESPONSE_RESULT_OK)  {
	                    sSubscriptionSupported = true;
						MoaiLog.i ( "MoaiGoogleBilling : Subscriptions supported" );
						sSubscriptionSupported = true;
	                }
	                else {
	                	sSubscriptionSupported = false;
						MoaiLog.i ( "MoaiGoogleBilling : Subscriptions not supported" );
						sSubscriptionSupported = false;
	                }
	
				} catch ( RemoteException e ) {
					
                    e.printStackTrace ();
                }
			}
		};
		
		sActivity.bindService ( new Intent ( "com.android.vending.billing.InAppBillingService.BIND" ), sServiceConn, Context.BIND_AUTO_CREATE );
	}

	//----------------------------------------------------------------//
	public static void onDestroy () {
	
		MoaiLog.i ( "MoaiGoogleBilling onDestroy: Unbinding billing service" );

		if ( sServiceConn != null ) {
			sActivity.unbindService ( sServiceConn );
		}
	}

	//----------------------------------------------------------------//
	public static void onActivityResult ( int requestCode, int resultCode, Intent data ) {
		
		if ( requestCode == 1001 ) {      

			if ( resultCode == 0 ) {
				synchronized ( Moai.sAkuLock ) {
					
					AKUNotifyGooglePurchaseResponseReceived ( BILLING_RESPONSE_RESULT_USER_CANCELED, sCurrentSKU, "" );
				}
			}     
			
			int responseCode = data.getIntExtra ( "RESPONSE_CODE", 0 );
		    String purchaseData = data.getStringExtra ( "INAPP_PURCHASE_DATA" ); 
		    String dataSignature = data.getStringExtra ( "INAPP_DATA_SIGNATURE" );
		
			ArrayList jsonData = new ArrayList ();
			jsonData.add ( purchaseData );
			jsonData.add ( dataSignature );
			JSONArray jsonArray = new JSONArray ( jsonData );
			
			if ( responseCode == BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED ) {

				responseCode = 0;
			}

			Bundle ownedItems;
			try {
				ownedItems = sService.getPurchases ( 3, sActivity.getPackageName (), "inapp", "" );
				ArrayList purchasedData = ownedItems.getStringArrayList ( "INAPP_PURCHASE_DATA_LIST" );
				if ( purchasedData != null ) {
					
					for ( Object dataLine : purchasedData ) {
						
						try { 
							
							JSONObject obj = new JSONObject (( String )dataLine );
							
							if ( !obj.getString ( "productId" ).contains( "pack" ) && !obj.getString ( "productId" ).contains( "unlock" ) && !obj.getString ( "productId" ).contains( "reward" ) || obj.getString ( "productId" ).contains( "starter" ) ) {
							
								consumePurchaseSync(obj.getString("purchaseToken"));
							}

							if ( obj.getString ( "productId" ).equals ( sCurrentSKU ) ) {

								MoaiLog.i ( "MoaiGoogleBilling onResult --- "+responseCode+" "+sCurrentSKU );
								
								synchronized ( Moai.sAkuLock ) {
									
									AKUNotifyGooglePurchaseResponseReceived ( responseCode, sCurrentSKU, obj.getString("purchaseToken") );
								}
							}
						} catch ( JSONException e ) {
							e.printStackTrace ();
						}
					}
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}
    
	//================================================================//
	// Google Billing (Android Market) JNI callback methods
	//================================================================//

	//----------------------------------------------------------------//
	public static boolean checkInAppSupported () {
		
		return sInAppSupported;
	}
	
	//----------------------------------------------------------------//
	public static boolean checkSubscriptionSupported () {
		
		return sSubscriptionSupported;
	}
	
	//----------------------------------------------------------------//
	public static int consumePurchaseSync ( String token ) {
		
		try {
			
			int response = sService.consumePurchase ( 3, sActivity.getPackageName (), token );
			return response;
			
		} catch ( RemoteException e ) {
			
			e.printStackTrace ();
		}
		
		return BILLING_RESPONSE_RESULT_ERROR;
	}
	
	//----------------------------------------------------------------//
	public static String getPurchasedProducts ( int productType, String continuation ) {
		
		try {
			
			String type = ( productType == 0 ) ? PURCHASE_TYPE_INAPP : PURCHASE_TYPE_SUBSCRIPTION;
			Bundle ownedItems = sService.getPurchases ( 3, sActivity.getPackageName (), type, continuation );
			
			if ( ownedItems.getInt ( "RESPONSE_CODE" ) == BILLING_RESPONSE_RESULT_OK ) {
								
				HashMap map = new HashMap < String, JSONArray > ();
				
				ArrayList ownedSkus = ownedItems.getStringArrayList ( "INAPP_PURCHASE_ITEM_LIST" );
				if ( ownedSkus != null ) {
					
					JSONArray skus = new JSONArray ( ownedSkus );
					map.put ( "ownedSkus", skus );
				}
				
				ArrayList purchaseData = ownedItems.getStringArrayList ( "INAPP_PURCHASE_DATA_LIST" );
				if ( purchaseData != null ) {
					
					JSONArray dataArray = new JSONArray ();
					for ( Object dataLine : purchaseData ) {
						
						try { 
							JSONObject obj = new JSONObject (( String )dataLine );
							dataArray.put ( obj );
						} catch ( JSONException e ) {
							e.printStackTrace ();
						}
					}
					map.put ( "purchaseData", dataArray );
				}
				
				ArrayList purchaseSigs = ownedItems.getStringArrayList ( "INAPP_DATA_SIGNATURE" );
				if ( purchaseSigs != null ) {
					
					JSONArray sigs = new JSONArray ( purchaseSigs );
					map.put ( "purchaseSigs", sigs );
				}
				
				JSONObject json = new JSONObject ( map );
				String continuationToken = ownedItems.getString ( "INAPP_CONTINUATION_TOKEN" );
				try { json.put ( "continuationToken", continuationToken ); } catch ( JSONException e ) { e.printStackTrace (); }
				return json.toString ();
			}
			
		} catch ( RemoteException e ) {
			
			e.printStackTrace ();
		}
		
		return "";
	}
	
	//----------------------------------------------------------------//
	public static int purchaseProduct ( String sku, int productType, String devPayload ) {
		
		sCurrentSKU = sku;
		
		try {
			
			String type = ( productType == 0 ) ? PURCHASE_TYPE_INAPP : PURCHASE_TYPE_SUBSCRIPTION;
			Bundle buyIntentBundle = sService.getBuyIntent ( 3, sActivity.getPackageName (), sku, type, devPayload );
		
			Bundle ownedItems = sService.getPurchases ( 3, sActivity.getPackageName (), type, "" );
			
			if ( ownedItems.getInt ( "RESPONSE_CODE" ) == BILLING_RESPONSE_RESULT_OK ) {
				
				ArrayList purchaseData = ownedItems.getStringArrayList ( "INAPP_PURCHASE_DATA_LIST" );
				if ( purchaseData != null ) {
					
					for ( Object dataLine : purchaseData ) {
						
						try { 
							
							JSONObject obj = new JSONObject (( String )dataLine );
							
							if ( !obj.getString ( "productId" ).contains( "pack" ) && !obj.getString ( "productId" ).contains( "unlock" ) && !obj.getString ( "productId" ).contains( "reward" ) || obj.getString ( "productId" ).contains( "starter" ) ) {
								
								int result = consumePurchaseSync(obj.getString("purchaseToken"));
								
								if ( result == BILLING_RESPONSE_RESULT_OK ) {
									
									synchronized ( Moai.sAkuLock ) {

										AKUNotifyGooglePurchaseResponseReceived ( BILLING_RESPONSE_RESULT_ERROR, sku, obj.getString ( "purchaseToken" ) );
									}
									
									return BILLING_RESPONSE_RESULT_ERROR;
								}
							}
								
						} catch ( JSONException e ) {
							e.printStackTrace ();
						}
					}
				}
			}
			
			type = ( productType == 0 ) ? PURCHASE_TYPE_INAPP : PURCHASE_TYPE_SUBSCRIPTION;
			buyIntentBundle = sService.getBuyIntent ( 3, sActivity.getPackageName (), sku, type, devPayload );
			
			if ( buyIntentBundle.getInt ( "RESPONSE_CODE" ) == BILLING_RESPONSE_RESULT_OK ) {
			
				PendingIntent pendingIntent = buyIntentBundle.getParcelable ( "BUY_INTENT" );
				sActivity.startIntentSenderForResult ( pendingIntent.getIntentSender (), 1001, new Intent (), Integer.valueOf ( 0 ), Integer.valueOf ( 0 ), Integer.valueOf ( 0 ));
			}
			
			if ( buyIntentBundle.getInt ( "RESPONSE_CODE" ) == BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED ) {
				
				synchronized ( Moai.sAkuLock ) {
					
					AKUNotifyGooglePurchaseResponseReceived ( BILLING_RESPONSE_RESULT_OK, sku, "empty" );
				}
				
				return BILLING_RESPONSE_RESULT_OK;
			}
			
		} catch ( Exception e ) {
						
            e.printStackTrace ();			
		}
		
		return BILLING_RESPONSE_RESULT_ERROR;
	}
	
	//----------------------------------------------------------------//
	public static String requestProductsSync ( String [] skus, int productType ) {
		
		try {
			
			ArrayList skuList = new ArrayList ();
			for ( String sku : skus ) {
			
				skuList.add ( sku );
			}
		
			Bundle querySkus = new Bundle ();
			querySkus.putStringArrayList ( "ITEM_ID_LIST", skuList );
			String type = ( productType == 0 ) ? PURCHASE_TYPE_INAPP : PURCHASE_TYPE_SUBSCRIPTION;
			Bundle skuDetails = sService.getSkuDetails ( 3, sActivity.getPackageName (), type, querySkus );

			//convert to json string
			if ( skuDetails.getInt ( "RESPONSE_CODE" ) == BILLING_RESPONSE_RESULT_OK ) {
			
				ArrayList responseList = skuDetails.getStringArrayList ( "DETAILS_LIST" );				
				JSONArray jsResponse = new JSONArray ( responseList );
				return jsResponse.toString ();
			} 
						
		} catch ( RemoteException e ) {
			
            e.printStackTrace ();
		}
		
		return "";
	}
}