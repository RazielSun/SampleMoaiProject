//----------------------------------------------------------------//
// Copyright (c) 2010-2011 Zipline Games, Inc. 
// All Rights Reserved. 
// http://getmoai.com
//----------------------------------------------------------------//

package com.getmoai.sample.samplemoai;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.util.DisplayMetrics;

// Moai
import com.ziplinegames.moai.*;

//================================================================//
// MoaiView
//================================================================//
public class MoaiView extends GLSurfaceView {

	public interface OnDrawCallback { public void drawFrame( int iterator ); }

	private static final long	AKU_UPDATE_FREQUENCY = 1000 / 60; // 60 Hz, in milliseconds

	private MoaiActivity	sActivity;
	private Context			mAppContext;
	private Handler			mHandler;
	private int 			mHeight;
	private int 			mWidth;
	
    //----------------------------------------------------------------//
	public MoaiView ( Context context, int width, int height, int glesVersion ) {
		super ( context );
		
		mAppContext = context.getApplicationContext();
		sActivity = ( MoaiActivity ) context;
		setScreenDimensions ( width, height );
		Moai.setScreenSize ( mWidth, mHeight );
		DisplayMetrics metrics = getResources().getDisplayMetrics();

		MoaiLog.i ( "MoaiRenderer setScreenDpi: " + metrics.densityDpi);
		
		Moai.setScreenDpi(metrics.densityDpi);

		if ( glesVersion >= 0x20000 ) {
			// NOTE: Must be set before the renderer is set.
			setEGLContextClientVersion ( 2 );
		}
		
		// Create a handler that we can use to post to the main thread and a pseudo-
		// periodic runnable that will handle calling Moai.update on the main thread.
		mHandler = new Handler ( Looper.getMainLooper ());
		
		setRenderer ( new MoaiRenderer ());
		onPause (); // Pause rendering until restarted by the activity lifecycle.		
		
	}

	
	//================================================================//
	// Public methods
	//================================================================//

	//----------------------------------------------------------------//
	@Override
	public void onSizeChanged ( int newWidth, int newHeight, int oldWidth, int oldHeight ) {
		
		setScreenDimensions ( newWidth, newHeight );
		Moai.setViewSize ( mWidth, mHeight );
	}
	
	//----------------------------------------------------------------//
	public void pause ( boolean paused ) {
		if ( paused ) {
			Moai.pause ( true );
			setRenderMode ( GLSurfaceView.RENDERMODE_WHEN_DIRTY );
			onPause ();
		} else {
			onResume ();
			setRenderMode ( GLSurfaceView.RENDERMODE_CONTINUOUSLY );
			Moai.pause ( false );
		}
	}

	//================================================================//
	// MotionEvent methods
	//================================================================//

    //----------------------------------------------------------------//
	@Override
	public boolean onTouchEvent ( MotionEvent event ) {

		boolean isDown = true;
        
		switch( event.getActionMasked() )
		{
			case MotionEvent.ACTION_CANCEL:
				/*Moai.enqueueTouchEventCancel(
					Moai.InputDevice.INPUT_DEVICE.ordinal (),
					Moai.InputSensor.SENSOR_TOUCH.ordinal ()
				);*/
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
				isDown = false;
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
			{
				final int pointerIndex = event.getActionIndex();
				int pointerId = event.getPointerId ( pointerIndex );
				Moai.enqueueTouchEvent (
					Moai.InputDevice.INPUT_DEVICE.ordinal (),
					Moai.InputSensor.SENSOR_TOUCH.ordinal (),
					pointerId, 
					isDown, 
					Math.round ( event.getX ( pointerIndex )), 
					Math.round ( event.getY ( pointerIndex )), 
					1
				);
				break;
			}
			case MotionEvent.ACTION_MOVE:
			default:
			{
				final int pointerCount = event.getPointerCount ();
				for ( int pointerIndex = 0; pointerIndex < pointerCount; ++pointerIndex ) {
				
					int pointerId = event.getPointerId ( pointerIndex );
					Moai.enqueueTouchEvent (
						Moai.InputDevice.INPUT_DEVICE.ordinal (),
						Moai.InputSensor.SENSOR_TOUCH.ordinal (),
						pointerId, 
						isDown, 
						Math.round ( event.getX ( pointerIndex )), 
						Math.round ( event.getY ( pointerIndex )), 
						1
					);
				}
				break;
			}
		}
		
		return true;
	}
	
	//================================================================//
	// Private methods
	//================================================================//
	
	//----------------------------------------------------------------//
	public void setScreenDimensions ( int width, int height ) {
		Resources resources = mAppContext.getResources();
		Configuration config = resources.getConfiguration();

		if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
			mWidth = Math.min(width, height);
			mHeight = Math.max(width, height);
		} else if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			mWidth = Math.max(width, height);
			mHeight = Math.min(width, height);
		} else {
			mWidth = width;
			mHeight = height;
		}
	}
	
	//================================================================//
	// MoaiRenderer
	//================================================================//
	
	private class MoaiRenderer implements GLSurfaceView.Renderer {

		private int iterator = 0;

		private long time = 0;

		private OnDrawCallback onDrawCallback;

		private boolean mRunScriptsExecuted = false;

	    //----------------------------------------------------------------//
		@Override
		public void onDrawFrame ( GL10 gl ) {
			
			MoaiRenderer.this.iterator = MoaiRenderer.this.iterator + 1;

			if ( onDrawCallback != null ) onDrawCallback.drawFrame ( MoaiRenderer.this.iterator );
			
			Moai.update ();
			Moai.render ();
		}

	    //----------------------------------------------------------------//
		@Override
		public void onSurfaceChanged ( GL10 gl, int width, int height ) {
			setScreenDimensions ( width, height );
			Moai.setViewSize ( mWidth, mHeight );        
		}
        
	    //----------------------------------------------------------------//
		@Override
		public void onSurfaceCreated ( GL10 gl, EGLConfig config ) {
			
			MoaiLog.i ( "MoaiRenderer onSurfaceCreated: surface CREATED" );
			
			Moai.detectGraphicsContext ();
			
			if ( !mRunScriptsExecuted ) {
				
				mRunScriptsExecuted = true;
				
				mHandler.postAtFrontOfQueue ( new Runnable () {
					
					public void run () {
						
						MoaiLog.i ( "MoaiRenderer onSurfaceCreated: Running game scripts" );

						runScripts ( new String [] { "../init.lua" } );

						Moai.runGame ();
						
						Moai.startSession ( false );
						
						Moai.setApplicationState ( Moai.ApplicationState.APPLICATION_RUNNING );
						
						if ( onDrawCallback == null ) {

							onDrawCallback = new OnDrawCallback() {

								public void drawFrame ( int iterator ) {

									// MoaiLog.i ( "MoaiRenderer onSurfaceChanged: drawFrame : "+iterator );

									// MoaiLog.i ( "MoaiRenderer onSurfaceChanged: drawFrame ms : "+time );

									if (( System.currentTimeMillis() - time) <= AKU_UPDATE_FREQUENCY) {

										MoaiActivity.splashCloseCallback.onClose();

										onDrawCallback = null;
									}

									time = System.currentTimeMillis();
								}
							};
						}
					}
				});
			} else {
				
				mHandler.post ( new Runnable () {
					
					public void run () {
						
						Moai.startSession ( true );
						
						Moai.setApplicationState ( Moai.ApplicationState.APPLICATION_RUNNING );
					}
				});
			}
		}	
		
	    //----------------------------------------------------------------//
		private void runScripts ( String [] filenames ) {
			
			for ( String file : filenames ) {
				
				MoaiLog.i ( "MoaiRenderer runScripts: Running " + file + " script" );
				
				Moai.runScript ( file );

				//Moai.runString ( str );
			}
		}	
	}
}
