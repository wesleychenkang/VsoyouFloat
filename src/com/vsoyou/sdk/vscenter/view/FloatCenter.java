package com.vsoyou.sdk.vscenter.view;

import java.lang.reflect.Field;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vsoyou.sdk.vscenter.FloatWindowManager;
import com.vsoyou.sdk.vscenter.util.BitmapCache;

public class FloatCenter extends LinearLayout implements OnClickListener {
	private static WindowManager.LayoutParams lp;
	private int statusBarHeight;
	private int oldoffsetX;
	private int oldoffsetY;
	private int tag = 0;
	private float lastX, lastY;
	private ImageView imag;

	public FloatCenter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public FloatCenter(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context ctx) {
		setOrientation(HORIZONTAL);
		FrameLayout lay = new FrameLayout(ctx);
		ImageView back_round = new ImageView(ctx);
		back_round.setBackgroundDrawable(BitmapCache.getDrawable(ctx,
				"cricle.png"));
		lay.addView(back_round);

		imag = new ImageView(ctx);
		imag.setBackgroundDrawable(BitmapCache.getDrawable(ctx, "logo.png"));
		lay.addView(imag, new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.CENTER));
		addView(lay);
	}

	public void setMangerLayParams(WindowManager.LayoutParams lp) {

		this.lp = lp;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		if (tag == 0) {
			oldoffsetX = lp.x;
			oldoffsetY = lp.y;
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastX = x;
			lastY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			int move = (int) (x - lastX);
			lp.x += (int) (x - lastX);
			lp.y += (int) (y - lastY);
			tag = 1;
			if (Math.abs(move) > 2)
				updatePostion();
			break;
		case MotionEvent.ACTION_UP:
			int newoffsetX = lp.x;
			int newoffsetY = lp.y;
			if (oldoffsetX == newoffsetX && oldoffsetY == newoffsetY) {
				updateView(oldoffsetX, oldoffsetY);
			} else {
				tag = 0;
			}
			break;

		}
		return true;
	}

	private void updateView(int x, int y) {
		if (lp.x > 0) {
			FloatWindowManager.disPlayLeftView(getContext());
		} else {
			FloatWindowManager.disPlayRightView(getContext());
		}

	}

	private void updatePostion() {
		WindowManager manager = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		manager.updateViewLayout(this, lp);

	}

	private int getStatusBarHeight() {
		if (statusBarHeight == 0) {
			try {
				Class<?> c = Class.forName("com.android.internal.R$dimen");
				Object o = c.newInstance();
				Field field = c.getField("status_bar_height");
				int x = (Integer) field.get(o);
				statusBarHeight = getResources().getDimensionPixelSize(x);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return statusBarHeight;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
