//----------------------------------------------------------------//
// Copyright (c) 2014 CloudTeam
// All Rights Reserved. 
// http://cloudteam.pro
//----------------------------------------------------------------//

package com.ziplinegames.moai;

import android.app.Activity;
import android.os.Bundle;

import java.util.Arrays;
import java.util.ArrayList;

//================================================================//
// MoaiFmodStudio
//================================================================//
public class MoaiFmodStudio {

	private static Activity sActivity = null;

	//----------------------------------------------------------------//
	public static void onCreate ( Activity activity ) {

		MoaiLog.i ( "MoaiFmodStudio onCreate: Initializing Fmod Studio" );

		sActivity = activity;

		org.fmod.FMOD.init( sActivity );
	}

	//----------------------------------------------------------------//
	public static void onDestroy () {

		MoaiLog.i ( "MoaiFmodStudio onDestroy: Fmod close" );

		org.fmod.FMOD.close ();
	}
}