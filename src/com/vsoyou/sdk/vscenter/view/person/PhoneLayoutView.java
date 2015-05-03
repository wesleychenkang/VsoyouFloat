package com.vsoyou.sdk.vscenter.view.person;

import com.vsoyou.sdk.ParamChain;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class PhoneLayoutView extends BaseLayout{

	public PhoneLayoutView(Context context,ParamChain chain) {
		super(context,chain);
		super.initUI(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void initEnv(Context ctx, ParamChain env) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onInitUI(Context ctx) {
		// TODO Auto-generated method stub
		setTitleText("ÊÖ»ú°ó¶¨");
		FrameLayout sub = (FrameLayout)getView_subject(ctx);
		
		LinearLayout all = new LinearLayout(ctx);
		all.setOrientation(VERTICAL);
		
		
		
		
		
		
		
		
	}

	

	@Override
	public boolean isExitEnabled(boolean isBack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ParamChain getEnv() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getMainView() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
