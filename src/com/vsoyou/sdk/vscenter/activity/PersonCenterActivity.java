package com.vsoyou.sdk.vscenter.activity;

import java.util.Stack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.vsoyou.sdk.ParamChain;
import com.vsoyou.sdk.ParamChain.KeyGlobal;
import com.vsoyou.sdk.protocols.ActivityControlInterface;
import com.vsoyou.sdk.vscenter.FloatWindowManager;
import com.vsoyou.sdk.vscenter.ParamChainImpl;
import com.vsoyou.sdk.vscenter.view.person.ILayoutHost;
import com.vsoyou.sdk.vscenter.view.person.ILayoutHost.KeyILayoutHost;
import com.vsoyou.sdk.vscenter.view.person.ILayoutView;
import com.vsoyou.sdk.vscenter.view.person.LayoutFactory;

public class PersonCenterActivity extends Activity {
	private static ParamChain ROOT_ENV;
	private Stack<ILayoutView> mStack = new Stack<ILayoutView>();
	private ParamChain root;

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
		//setContentView(new PersonLayoutView(getApplicationContext(), ROOT_ENV));
		initActivity(this);
	}

	private void initActivity(Activity activity) {
		String name = getIntent().getStringExtra(KeyGlobal.KEY_UINAME);
		ParamChain env = (ParamChain) GET_GLOBAL_PARAM_CHAIN().getParent(PersonCenterActivity.class.getName()).remove(name);
		root = env.grow();
		root.add(KeyGlobal.BASE_ACTIVITY, activity);
		root.add(KeyILayoutHost.K_HOST, new ILayoutHost() {

			@Override
			public void back() {
				popViewFromStack();
			}

			@Override
			public void exit() {
				// TODO Auto-generated method stub
				finish();
			}

			@Override
			public void enter(ParamChain chain, ClassLoader loader,
					String classname) {
				tryEnterView(chain, loader, classname);
			}

			@Override
			public void enter(ParamChain chain, LayoutType type) {
				// TODO Auto-generated method stub

				tryEnterView(chain, type);

			}

			@Override
			public void addActivityControl(ActivityControlInterface control) {
				// TODO Auto-generated method stub

			}

			@Override
			public void removeActivityControl(ActivityControlInterface control) {
				// TODO Auto-generated method stub

			}

		});
		LayoutType type = root.get(KeyGlobal.LAYOUT_TYPE, LayoutType.class);
		tryEnterView(root,type);
	}

	private void tryEnterView(ParamChain chain, ClassLoader loader,
			String classname) {

		ILayoutView layout = LayoutFactory.createLayout(
				getApplicationContext(), classname, loader, chain);
		pushViewToStack(layout);

	}

	private void tryEnterView(ParamChain chain, LayoutType type) {
		ILayoutView layout = LayoutFactory.createLayoutView(
				getApplicationContext(), type, chain);
		pushViewToStack(layout);

	}

	private View checkPopExitView(boolean isBack) {

		if (mStack.size() > 0) {
			ILayoutView layout = mStack.peek();
			if (!layout.isExitEnabled(isBack)) {
				View top = layout.getMainView();
				return top;
			}

		}
		return null;
	}

	private View popViewFromStack() {
		View v = checkPopExitView(true);
		if (v != null) {
			return v;
		}

		if (mStack.size() > 1) {
			ILayoutView layout = mStack.pop();
			if (layout.isAlive()) {
				layout.onExit();
			}
			layout = mStack.peek();
			layout.onResume();
			View cur = layout.getMainView();
			setContentView(cur);
			cur.requestFocus();
			return cur;
		} else {
			finish();
			return null;
		}

	}

	private boolean pushViewToStack(ILayoutView layout) {
		if (mStack.size() > 0) {
			ILayoutView lvOld = mStack.peek();
			if (lvOld.isAlive()) {
				View old = lvOld.getMainView();
				old.clearFocus();
			}
			lvOld.onPause();
		}

		mStack.push(layout);
		layout.onEnter();
		View cur = layout.getMainView();
		setContentView(cur);
		cur.requestFocus();
		return true;
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
