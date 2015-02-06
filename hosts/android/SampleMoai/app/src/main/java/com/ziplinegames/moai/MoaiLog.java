//----------------------------------------------------------------//
// Copyright (c) 2010-2011 Zipline Games, Inc. 
// All Rights Reserved. 
// http://getmoai.com
//----------------------------------------------------------------//

package com.ziplinegames.moai;

import android.util.Log;

//================================================================//
// MoaiLog
//================================================================//
public class MoaiLog {

	private static boolean logEnabled = true;

	//----------------------------------------------------------------//
	public static void setLogEnabled ( boolean isEnabled ) {
		
	    logEnabled = isEnabled;
	}

	//----------------------------------------------------------------//
	public static void e ( String message, Exception e ) {

		if ( logEnabled ) Log.e ( "MoaiLog", message, e );
	}

	//----------------------------------------------------------------//
	public static void e ( String message ) {
		
	    if ( logEnabled ) Log.e ( "MoaiLog", message );
	}
	
	//----------------------------------------------------------------//
	public static void w ( String message ) {
		
	    if ( logEnabled ) Log.w ( "MoaiLog", message );
	}

	//----------------------------------------------------------------//
	public static void d ( String message ) {
		
	    if ( logEnabled ) Log.d ( "MoaiLog", message );
	}

	//----------------------------------------------------------------//
	public static void i ( String message ) {
		
	    if ( logEnabled ) Log.i ( "MoaiLog", message );
	}
}