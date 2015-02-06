package com.getmoai.sample.samplemoai;

import com.ziplinegames.moai.MoaiLog;

import android.app.Application;
import android.content.res.Configuration;

//================================================================//
//MoaiApplication
//================================================================//
public class MoaiApplication extends Application {
	
	private static MoaiApplication application;

    //----------------------------------------------------------------//
	public static MoaiApplication getApplication () {
		return application;
	}

    //----------------------------------------------------------------//
	public final void onCreate () {
		MoaiLog.i ( "MoaiApplication onCreate: application CREATED" );
		
		super.onCreate ();
		
		application = this;
	}

    //----------------------------------------------------------------//
	public void onLowMemory () {
		MoaiLog.i ( "MoaiApplication onLowMemory: MEMORY WARNING!" );
		
		super.onLowMemory ();
	}
	
	public void onTrimMemory ( int level ) {
		MoaiLog.i ( "MoaiApplication onTrimMemory: MEMORY TRIM! LEVEL" + level);

//		super.onTrimMemory ( level );
	}

    //----------------------------------------------------------------//
	public void onTerminate () {
		MoaiLog.i ( "MoaiApplication onTerminate: application TERMINATED" );
		
		super.onTerminate ();
	}

    //----------------------------------------------------------------//
	public void onConfigurationChanged ( Configuration newConfig ) {
		MoaiLog.i ( "MoaiApplication onConfigurationChanged: " );
		
		super.onConfigurationChanged ( newConfig );
	}
}
