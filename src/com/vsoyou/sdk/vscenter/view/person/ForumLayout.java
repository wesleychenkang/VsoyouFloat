package com.vsoyou.sdk.vscenter.view.person;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.vsoyou.sdk.ParamChain;

public class ForumLayout extends BaseLayout {

	public ForumLayout(Context context, ParamChain env) {
		super(context, env);
		super.initUI(context);
	}

	@Override
	protected void initEnv(Context ctx, ParamChain env) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onInitUI(Context ctx) {
		setTitleText("Õ˛À—”Œ¬€Ã≥");
		FrameLayout sub = (FrameLayout) getView_subject(ctx);
		final WebView web = new WebView(getActivity());
		sub.addView(web,new FrameLayout.LayoutParams(LP_MM));
        web.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				web.loadUrl(url);
				return true;
			}
        });
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("http://vsoyou.com");
	}

}
