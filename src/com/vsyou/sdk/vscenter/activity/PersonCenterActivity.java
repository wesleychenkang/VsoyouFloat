package com.vsyou.sdk.vscenter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.vsyou.sdk.vscenter.FloatWindowManager;
import com.vsyou.sdk.vscenter.view.FloatCenterPresonView;

public class PersonCenterActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		LinearLayout ly = new LinearLayout(this);
		ly.setGravity(Gravity.BOTTOM);
		ly.addView(new FloatCenterPresonView(this),new LayoutParams(LayoutParams.WRAP_CONTENT, 500));
		setContentView(ly);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		FloatWindowManager.disPlayCenterView(getApplicationContext(), -1);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		FloatWindowManager.disPlayCenterView(getApplicationContext(), -1);
	}
}
