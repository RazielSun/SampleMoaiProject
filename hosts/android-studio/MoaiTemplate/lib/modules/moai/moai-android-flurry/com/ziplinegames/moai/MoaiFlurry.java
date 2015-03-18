//----------------------------------------------------------------//
// Copyright (c) 2010-2011 Zipline Games, Inc. 
// All Rights Reserved. 
// http://getmoai.com
//----------------------------------------------------------------//

package com.ziplinegames.moai;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import android.app.Activity;

import com.flurry.android.FlurryAgent;

//================================================================//
// MoaiFacebook
//================================================================//
public class MoaiFlurry {

	private static Activity sActivity = null;
	private static String sAPIKey = null;
	
	//----------------------------------------------------------------//
	public static void onCreate ( Activity activity ) {
		
		MoaiLog.i ( "MoaiFlurry onCreate: Initializing Flurry" );
		sActivity = activity;
		//FlurryAgent.setLogEnabled(true);
		//FlurryAgent.setLogEvents(true);
		//FlurryAgent.setLogLevel(0);
	}

	public static void onStart( ) {
		MoaiLog.i ( "MoaiFlurry onStart: " );
		if ( sAPIKey != null ) {
			FlurryAgent.onStartSession( sActivity, sAPIKey );
		}
	}

	public static void onStop ( ) {
		MoaiLog.i ( "MoaiFlurry onStop: Ending Flurry Session" );
		if ( sAPIKey != null ) {
			FlurryAgent.onEndSession( sActivity );
		}
	}
	
	//================================================================//
	// Facebook JNI callback methods
	//================================================================//
	
	//----------------------------------------------------------------//	
	public static void setAppVersion ( String version ) {

		MoaiLog.i ( "MoaiFlurry setAppVersion: "+version );
		if ( version != null ) {
			FlurryAgent.setVersionName ( version );
		}
	}

	//----------------------------------------------------------------//	
	public static void startSession ( String apikey ) {

		MoaiLog.i ( "MoaiFlurry startSession: " );
		if ( sAPIKey == null ) {
			sAPIKey = apikey;
			FlurryAgent.onStartSession( sActivity, sAPIKey );
		}
	}

	//----------------------------------------------------------------//	
	public static void logEvent ( String name ) {
		MoaiLog.i ( "MoaiFlurry logEvent: logEvent(String) " );
		FlurryAgent.logEvent( name );
	}

	//----------------------------------------------------------------//	
	public static void logEvent ( String name, Map<String, String> parameters ) {
		MoaiLog.i ( "MoaiFlurry startSession: logEvent(String, Map) " );
		FlurryAgent.logEvent( name, parameters );
	}

	//----------------------------------------------------------------//	
	public static void logEvent ( String name, boolean timed ) {
		MoaiLog.i ( "MoaiFlurry logEvent: logEvent(String, boolean) " );
		FlurryAgent.logEvent( name, timed );
	}

	//----------------------------------------------------------------//	
	public static void logEvent ( String name, Map<String, String> parameters, boolean timed ) {
		MoaiLog.i ( "MoaiFlurry logEvent: logEvent(String, Map, boolean) " );
		FlurryAgent.logEvent( name, parameters, timed );
	}

	//----------------------------------------------------------------//	
	public static void endTimedEvent ( String name ) {
		MoaiLog.i ( "MoaiFlurry logEvent: endTimedEvent " );
		FlurryAgent.endTimedEvent( name );
	}	
}
