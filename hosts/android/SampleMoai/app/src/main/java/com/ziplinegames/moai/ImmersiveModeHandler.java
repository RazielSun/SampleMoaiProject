package com.ziplinegames.moai;
 
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.WindowManager;
 
public class ImmersiveModeHandler {
 
	private static final int INITIAL_DELAY = 3000;
	private static final int DEFAULT_DELAY = 500;
       
	private Activity activity;
	private View view;
 
	public ImmersiveModeHandler(Activity activity) {
		this.activity = activity;
	}
 
	@SuppressWarnings("deprecation")
	public Point getScreenSize() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			return getRealMetrics();
		} else {
			Display display = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE))
					.getDefaultDisplay();
			return new Point(display.getWidth(), display.getHeight());
		}
	}
 
	public void setupView(View view) {
		this.view = view;
	       
		mRestoreImmersiveModeHandler.postDelayed(onStartImmersiveModeRunnable, INITIAL_DELAY);
	}
 
	public void handleKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP)
		{
			enableImmersiveModeDelayed();
		}
	}
       
	private void setupListeners() {        
		view.setOnFocusChangeListener(new OnFocusChangeListener() {
		       
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					enableImmersiveMode();
				}
			}
		});
 
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setupVisibilityChangeListener(view);
		}
	}
 
	@TargetApi(Build.VERSION_CODES.KITKAT)
	private void setupVisibilityChangeListener(View view) {
		view.setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener() {                  
			@Override
			public void onSystemUiVisibilityChange(int visibility) {
				enableImmersiveMode();
			}
		});
	}
 
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public void enableImmersiveMode() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			view.setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}
 
	@TargetApi(Build.VERSION_CODES.KITKAT)
	private Point getRealMetrics() {
		DisplayMetrics displayMetrics = new DisplayMetrics();
 
		Display display = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay();
		display.getRealMetrics(displayMetrics);
		return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
	}
       
	/// TIMER STUFF
       
	private Handler mRestoreImmersiveModeHandler = new Handler();
	private Runnable onStartImmersiveModeRunnable = new Runnable()
	{
		public void run()
		{
			enableImmersiveMode();
			setupListeners();
		}
	};
	private Runnable restoreImmersiveModeRunnable = new Runnable()
	{
		public void run()
		{
			enableImmersiveMode();            
		}
	};
 
	public void enableImmersiveModeDelayed() {
		enableImmersiveMode();
		mRestoreImmersiveModeHandler.postDelayed(restoreImmersiveModeRunnable, DEFAULT_DELAY);
	}
}
 
