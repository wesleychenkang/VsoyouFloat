package com.vsoyou.sdk.vscenter.view.person;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.vsoyou.sdk.vscenter.ParamChain;

public class RecordLayoutView extends BaseLayout{

	public RecordLayoutView(Context context, ParamChain env) {
		super(context, env);
		super.initUI(context);
	}
	@Override
	protected void initEnv(Context ctx, ParamChain env) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onInitUI(Context ctx) {
       setTitleText("³äÖµ¼ÇÂ¼");
       FrameLayout sub = (FrameLayout)getView_subject(ctx);
       ListView mList = new ListView(ctx);
       sub.addView(mList);
	}

	

}
