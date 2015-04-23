package com.vsyou.sdk.vscenter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FloatService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
  
	@Override
	public void onCreate() {
		super.onCreate();
		FloatWindowManager.createCenterView(getApplicationContext());
	}
}
