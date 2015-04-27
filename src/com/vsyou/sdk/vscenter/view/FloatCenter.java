package com.vsyou.sdk.vscenter.view;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidfloat.R;
import com.vsoyou.sdk.resources.ResourceLoader;
import com.vsyou.sdk.vscenter.FloatWindowManager;
import com.vsyou.sdk.vscenter.util.MetricUtil;
import com.vsyou.sdk.vscenter.util.ScreenUtil;

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
		//setBackgroundColor(Color.RED);
		//setPadding(0, 15, 0, 15);
		imag = new ImageView(ctx);
		imag.setBackgroundDrawable(ResourceLoader.getBitmapDrawable("logo.png"));
		//imag.setPadding(15, 15, 15, 15);
		LayoutParams lbtn = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
//		lbtn.height = MetricUtil.getDip(getContext(), 50);
//		lbtn.width =MetricUtil.getDip(getContext(), 50);
		lbtn.gravity = Gravity.CENTER;
		addView(imag, lbtn);
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
			System.out.println("move" + move);
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
		System.out.println(" lp.x " + x + "with"
				+ ScreenUtil.getWith(getContext()));
		if (lp.x > 0) {
			FloatWindowManager.disPlayLeftView(getContext());
			// FloatWindowManager.createCenterLeftView(getContext());
			// FloatWindowManager.removeCenterView(getContext());
		} else {
			FloatWindowManager.disPlayRightView(getContext());
			// FloatWindowManager.createCenterRightView(getContext());
			// FloatWindowManager.removeCenterView(getContext());

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
