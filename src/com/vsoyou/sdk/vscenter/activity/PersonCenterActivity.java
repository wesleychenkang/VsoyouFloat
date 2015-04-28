package com.vsoyou.sdk.vscenter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.vsoyou.sdk.ParamChain;
import com.vsoyou.sdk.vscenter.FloatWindowManager;
import com.vsoyou.sdk.vscenter.ParamChainImpl;

public class PersonCenterActivity extends Activity {
	private static ParamChain ROOT_ENV;

	public static final synchronized ParamChain GET_GLOBAL_PARAM_CHAIN() {
		if (ROOT_ENV == null) {
			ROOT_ENV = ParamChainImpl.GLOBAL().grow(
					PersonCenterActivity.class.getName());
		}
		return ROOT_ENV;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
