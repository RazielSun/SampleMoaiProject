//----------------------------------------------------------------//
// Copyright (c) 2014 CloudTeam 
// All Rights Reserved. 
//----------------------------------------------------------------//

package com.ziplinegames.moai;

import android.app.Activity;

import com.chartboost.sdk.*;
//import com.chartboost.sdk.Tracking.CBPostInstallTracker;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
//import com.chartboost.sdk.Tracking.CBPostInstallTracker.CBIAPType;

//================================================================//
// MoaiChartboost
//================================================================//
public class MoaiChartboost {

	private static Activity 				sActivity = null;
	private static boolean					purchaseTracking = false;
	private static ChartboostDelegate		chartboostDelegate;
	
	protected static native void AKUNotifyChartboostInterstitialDismissed ( String location );
	protected static native void AKUNotifyChartboostInterstitialLoadFailed ( String location );
	protected static native void AKUNotifyChartboostInterstitialWillShow ( String location );
	protected static native void AKUNotifyChartboostVideoDismissed ( String location );
	protected static native void AKUNotifyChartboostVideoReward ( String location, int reward );
	protected static native void AKUNotifyChartboostVideoWillShow ( String location );
	protected static native void AKUNotifyChartboostReportPurchase ( Object purcase, String id );

	//----------------------------------------------------------------//
	public static void onCreate ( Activity activity ) {
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost onCreate:  -------------------------------------------------------- " );
		sActivity = activity;
	}
	
	//----------------------------------------------------------------//
	public static void onResume () {
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost onResume:  -------------------------------------------------------- " );
		if ( sActivity != null ) Chartboost.onResume ( sActivity );
	}
	
	//----------------------------------------------------------------//
	public static void onPause () {
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost onPause:  -------------------------------------------------------- " );
		if ( sActivity != null ) Chartboost.onPause ( sActivity );
	}

	//----------------------------------------------------------------//
	public static void onStart () {
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost onStart:  -------------------------------------------------------- " );
		if ( sActivity != null ) Chartboost.onStart ( sActivity ); 
	}

	//----------------------------------------------------------------//
	public static void onStop () {
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost onStop:  -------------------------------------------------------- " );
		if ( sActivity != null ) Chartboost.onStop ( sActivity );
	}
	
	//----------------------------------------------------------------//
	public static void onDestroy () {
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost onDestroy:  -------------------------------------------------------- " );
		if ( sActivity != null ) Chartboost.onDestroy ( sActivity );
	}

	//----------------------------------------------------------------//
	public static void onBackPressed () {
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost onBackPressed: chartboost.onBackPressed (); -------------------------------------------------------- " );
		if ( sActivity != null ) Chartboost.onBackPressed ();
	}
	
	//================================================================//
	// Chartboost JNI callback methods
	//================================================================//
	
	//----------------------------------------------------------------//	
	public static void init ( String appId, String appSignature ) {
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost: init - Initializing Chartboost -------------------------------------------------------- " );
	    Chartboost.startWithAppId ( sActivity, appId, appSignature );
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost: init start app with id -------------------------------------------------------- " );
	    chartboostDelegate = new ChartboostDelegate () {
			
			//----------------------------------------------------------------//
			public void didDismissInterstitial ( String location ) {
				MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost didDismissInterstitial: for location - "+location+" -------------------------------------------------------- " );
				synchronized ( Moai.sAkuLock ) {
					AKUNotifyChartboostInterstitialDismissed ( location );
				}
			}
			
			//----------------------------------------------------------------//
			public void didFailToLoadInterstitial ( String location, CBImpressionError error ) {
				MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost didFailToLoadInterstitial: error - "+error.name()+" for location - "+location+" -------------------------------------------------------- " );
				synchronized ( Moai.sAkuLock ) {
					AKUNotifyChartboostInterstitialLoadFailed ( location );
				}
			}
			
			//----------------------------------------------------------------//
			public void didDisplayInterstitial ( String location ) {
				MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost didDisplayInterstitial: for location - "+location+" -------------------------------------------------------- " );
				synchronized ( Moai.sAkuLock ) {
					AKUNotifyChartboostInterstitialWillShow ( location );
				}
			}
			
			//----------------------------------------------------------------//
			public void didDismissRewardedVideo ( String location ) {
				MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost didDismissRewardedVideo: for location - "+location+" -------------------------------------------------------- " );
				synchronized ( Moai.sAkuLock ) {
					AKUNotifyChartboostVideoDismissed ( location );
				}
			}
			
			//----------------------------------------------------------------//
			public void didCompleteRewardedVideo ( String location, int reward ) {
				MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost didCompleteRewardedVideo: reward - "+reward+" for location - "+location+" -------------------------------------------------------- " );
				synchronized ( Moai.sAkuLock ) {
					AKUNotifyChartboostVideoReward ( location, reward );
				}
			}
			
			//----------------------------------------------------------------//
			public void willDisplayVideo ( String location ) {
				MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost willDisplayVideo: for location - "+location+" -------------------------------------------------------- " );
				synchronized ( Moai.sAkuLock ) {
					AKUNotifyChartboostVideoWillShow ( location );
				}
			}
		};
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost onCreate (init):  -------------------------------------------------------- " );
		Chartboost.onCreate ( sActivity );
		Chartboost.setShouldRequestInterstitialsInFirstSession ( true );
		Chartboost.setImpressionsUseActivities ( true );
		Chartboost.setLoggingLevel ( Level.ALL );
		Chartboost.setDelegate ( chartboostDelegate );
	}
	
	//----------------------------------------------------------------//	
	public static void loadInterstitial ( String location ) {
		if ( location == null ) location = CBLocation.LOCATION_DEFAULT;
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost: Chaching ad for location - "+location+" -------------------------------------------------------- " );
		try {
			Chartboost.cacheInterstitial ( location );
		} catch ( java.lang.IllegalStateException exception ) {
			Chartboost.onStart ( sActivity ); 
			Chartboost.cacheInterstitial ( location );
		}
	}
    
	//----------------------------------------------------------------//	
	public static void cacheVideo ( String location ) {
		if ( location == null ) location = CBLocation.LOCATION_DEFAULT;
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost: Chaching video ad for location - "+location+" -------------------------------------------------------- " );
		try {
			Chartboost.cacheRewardedVideo ( location );
		} catch ( java.lang.IllegalStateException exception ) {
			Chartboost.onStart ( sActivity ); 
			Chartboost.cacheRewardedVideo ( location );
		}
	}
 
	//----------------------------------------------------------------//	
	public static void showInterstitial ( String location ) {
		if ( location == null ) location = CBLocation.LOCATION_DEFAULT;
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost: Show ad for location - "+location+" -------------------------------------------------------- " );
		try {
			Chartboost.showInterstitial ( location );
		} catch ( java.lang.IllegalStateException exception ) {
			Chartboost.onStart ( sActivity ); 
			Chartboost.showInterstitial ( location );
		}
	}

	//----------------------------------------------------------------//	
	public static void showVideo ( String location ) {
		if ( location == null ) location = CBLocation.LOCATION_DEFAULT;
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost: Show video ad for location - "+location+" -------------------------------------------------------- " );
		try {
			Chartboost.showRewardedVideo ( location );
		} catch ( java.lang.IllegalStateException exception ) {
			Chartboost.onStart ( sActivity ); 
			Chartboost.showRewardedVideo ( location );
		}
	}
	
	//----------------------------------------------------------------//	
	public static boolean hasCachedInterstitial ( String location ) {
		if ( location == null ) location = CBLocation.LOCATION_DEFAULT;
		boolean result = false;
		try {
			result = Chartboost.hasInterstitial ( location );
		} catch ( java.lang.IllegalStateException exception ) {
			Chartboost.onStart ( sActivity ); 
			result = Chartboost.hasInterstitial ( location );
		}
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost: hasCachedInterstitial = "+result+" for location - "+location+" -------------------------------------------------------- " );
		return result;
	}
	
	//----------------------------------------------------------------//	
	public static boolean hasCachedVideo ( String location ) {
		if ( location == null ) location = CBLocation.LOCATION_DEFAULT;
		boolean result = false;
		try {
			result = Chartboost.hasRewardedVideo ( location );
		} catch ( java.lang.IllegalStateException exception ) {
			Chartboost.onStart ( sActivity ); 
			result = Chartboost.hasRewardedVideo ( location );
		}
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost: hasCachedVideo = "+result+" for location - "+location+" -------------------------------------------------------- " );
		return result;
	}
	
	//----------------------------------------------------------------//	
	public static void setPurchaseTracking ( boolean tracking ) {
		MoaiLog.i ( " -------------------------------------------------------- MoaiChartboost: setPurchaseTracking = "+tracking+" -------------------------------------------------------- " );
		purchaseTracking = tracking;
	}

	//----------------------------------------------------------------//	
	public static void reportPurchase ( boolean tracking ) {
//		EnumMap<CBIAPPurchaseInfo, String> map = new EnumMap<CBIAPPurchaseInfo, String>(CBIAPPurchaseInfo.class);
//        map.put(CBIAPPurchaseInfo.PRODUCT_ID,"xxx-id");
//        map.put(CBIAPPurchaseInfo.PRODUCT_TITLE,"xxx-title");
//        map.put(CBIAPPurchaseInfo.PRODUCT_DESCRIPTION, "xxx-description");
//        map.put(CBIAPPurchaseInfo.PRODUCT_PRICE, "$0.99");
//        map.put(CBIAPPurchaseInfo.PRODUCT_CURRENCY_CODE,"USD");
//        map.put(CBIAPPurchaseInfo.GOOGLE_PURCHASE_DATA, "xxx-data");
//        map.put(CBIAPPurchaseInfo.GOOGLE_PURCHASE_SIGNATURE, "xxx-signature");
//        trackInAppPurchaseEvent(map, CBIAPType.GOOGLE_PLAY);
	}
}
