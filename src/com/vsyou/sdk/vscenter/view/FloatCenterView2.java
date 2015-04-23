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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidfloat.R;

public class FloatCenterView2 extends FrameLayout implements OnClickListener {
	private WindowManager.LayoutParams lp;
	private int statusBarHeight;
	private int oldoffsetX;
	private int oldoffsetY;
	private int tag = 0;
	private float lastX, lastY;
	private ImageView imag;
	private FrameLayout frame_right;
	private FrameLayout frame_left;
	private LinearLayout l;

	public FloatCenterView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public FloatCenterView2(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context ctx) {

		frame_left = new FrameLayout(ctx);
		frame_left.setVisibility(View.VISIBLE);
		l = new LinearLayout(ctx);
		// l.setBackgroundColor(Color.RED);
		l.setPadding(0, 0, 0, 0);
		imag = new ImageView(ctx);
		imag.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.ic_launcher));
		LayoutParams lbtn = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		// lbtn.gravity = Gravity.RIGHT;
		l.addView(imag);
		frame_left.addView(l, lbtn);
		LayoutParams ll_btn_prams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		addView(frame_left, ll_btn_prams);

	}

	public void setMangerLayParams(WindowManager.LayoutParams lp) {

		this.lp = lp;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
			lp.x += (int) (x - lastX);
			lp.y += (int) (y - lastY);
			tag = 1;
			updatePostion();
			break;
		case MotionEvent.ACTION_UP:

			int newoffsetX = lp.x;
			int newoffsetY = lp.y;
			if (oldoffsetX == newoffsetX && oldoffsetY == newoffsetY) {
				updateView();
			} else {
				tag = 0;

			}
			break;

		}

		// switch(event.getAction()){
		// case MotionEvent.ACTION_DOWN:
		// xInView = event.getX();
		// yInView = event.getY();
		//
		// xInScreen = event.getRawX();
		// yInScreen = event.getRawY()-getStatusBarHeight();
		//
		// downXInScreen = event.getRawX();
		// downYInScreen = event.getRawY()-getStatusBarHeight();
		// break;
		//
		// case MotionEvent.ACTION_MOVE:
		// xInScreen = event.getRawX();
		// yInScreen = event.getRawY()-getStatusBarHeight();
		// updatePostion();
		// break;
		// case MotionEvent.ACTION_UP:
		// if(downXInScreen==xInScreen && downYInScreen == yInScreen){
		// Toast.makeText(getContext(), "点击到我了", Toast.LENGTH_LONG).show();
		// hideView();
		// }
		// break;
		// }
		return true;

	}

	private void updatePostion() {
		WindowManager manager = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		manager.updateViewLayout(this, lp);

	}

	private void updateView() {
		WindowManager manager = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		manager.removeView(this);
		FloatCenterView3 view3 = new FloatCenterView3(getContext());
		manager.addView(view3, lp);
	}

	// private void hideView(){
	// ImageView view = (ImageView)findViewById(100002);
	// TextView txt = (TextView)findViewById(1000021);
	// if(view.getVisibility()==View.GONE){
	// view.setVisibility(View.VISIBLE);
	// txt.setVisibility(View.VISIBLE);
	//
	// }else {
	// view.setVisibility(View.GONE);
	// txt.setVisibility(View.GONE);
	//
	//
	// }
	//
	// }
	// private void hideView(){
	// LinearLayout l1 = (LinearLayout)findViewById(100001);
	// if(l1.getVisibility()==View.GONE){
	//
	// l1.setVisibility(View.VISIBLE);
	// }else{
	// l1.setVisibility(View.GONE);
	// }
	// LinearLayout l2 = (LinearLayout)findViewById(100003);
	// if(l2.getVisibility()==View.VISIBLE){
	// l2.setVisibility(View.GONE);
	// }else{
	// l2.setVisibility(View.VISIBLE);
	// }
	//
	// }

	private void displayView(Context ctx) {

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
		switch (arg0.getId()) {
		case 100002:
			//
			Toast.makeText(getContext(), "论坛", Toast.LENGTH_SHORT).show();
			break;
		case 100004:
			Toast.makeText(getContext(), "个人", Toast.LENGTH_SHORT).show();
			break;
		case 100005:
			Toast.makeText(getContext(), "中心", Toast.LENGTH_SHORT).show();

			// hideView();//
			break;
		default:
			break;

		}

	}
}
