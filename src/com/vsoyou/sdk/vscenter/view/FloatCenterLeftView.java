package com.vsoyou.sdk.vscenter.view;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.Intent;
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
import com.vsoyou.sdk.vscenter.FloatWindowManager;
import com.vsoyou.sdk.vscenter.PersonCenterManager;
import com.vsoyou.sdk.vscenter.util.BitmapCache;
import com.vsoyou.sdk.vscenter.util.MetricUtil;

public class FloatCenterLeftView extends LinearLayout implements
		OnClickListener {
	private WindowManager.LayoutParams lp;
	private int statusBarHeight;
	private int oldoffsetX;
	private int oldoffsetY;
	private int tag = 0;
	private float lastX, lastY;
	private ImageView imag;

	public FloatCenterLeftView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public FloatCenterLeftView(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context ctx) {
		setVisibility(View.GONE);
		FrameLayout all = new FrameLayout(ctx);
		LayoutParams lp_ll = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		lp_ll.width = MetricUtil.getDip(ctx, 180);
		addView(all, lp_ll);
		LinearLayout l_left = new LinearLayout(ctx);
		l_left.setBackgroundDrawable(BitmapCache.getDrawable(ctx,
				"float_left.png"));
		FrameLayout.LayoutParams lp_lleft = new FrameLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lp_lleft.rightMargin = MetricUtil.getDip(ctx, 50);
		lp_lleft.topMargin = MetricUtil.getDip(ctx, 7);
		lp_lleft.gravity = Gravity.RIGHT;
		all.addView(l_left, lp_lleft);
		l_left.setOrientation(HORIZONTAL);

		// 个人中心
		LinearLayout ll_person = new LinearLayout(ctx);
		ll_person.setOrientation(VERTICAL);
		ll_person.setGravity(Gravity.CENTER_VERTICAL);
		ll_person.setId(100003);
		ImageView image1 = new ImageView(ctx);
		image1.setId(100004);
		image1.setOnClickListener(this);
		image1.setBackgroundDrawable(BitmapCache.getDrawable(ctx, "person.png"));
		LayoutParams lp_image1 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		lp_image1.gravity = Gravity.CENTER_HORIZONTAL;
		ll_person.addView(image1, lp_image1);

		TextView txt = new TextView(ctx);
		txt.setText("个人中心");
		txt.setTextSize(MetricUtil.getDip(ctx, 6));
		LayoutParams ll_txt_person = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		ll_txt_person.gravity = Gravity.CENTER;
		ll_person.addView(txt, ll_txt_person);
		LayoutParams ll_person_prams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		ll_person_prams.weight = 0.5f;
		l_left.addView(ll_person, ll_person_prams);

		// 论坛
		LinearLayout ll_forum = new LinearLayout(ctx);
		ll_forum.setOrientation(VERTICAL);
		ll_forum.setGravity(Gravity.LEFT);
		ll_forum.setPadding(MetricUtil.getDip(ctx, 10),
				MetricUtil.getDip(ctx, 5), 0, MetricUtil.getDip(ctx, 5));
		ll_forum.setId(100001);

		ImageView image_forum = new ImageView(ctx);
		image_forum.setBackgroundDrawable(BitmapCache.getDrawable(ctx,
				"forum.png"));
		LayoutParams lp_image_forum = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		// lp_image_forum.gravity = Gravity.CENTER_HORIZONTAL;
		ll_forum.addView(image_forum, lp_image_forum);
		image_forum.setId(100002);
		image_forum.setOnClickListener(this);

		TextView txt_forum = new TextView(ctx);
		txt_forum.setText("论坛");
		txt_forum.setTextSize(MetricUtil.getDip(ctx, 6));
		txt_forum.setId(1000021);
		LayoutParams lp_txt_forum = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		ll_forum.addView(txt_forum, lp_txt_forum);

		LayoutParams ll_forum_prams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		ll_forum_prams.weight = 0.5f;
		l_left.addView(ll_forum, ll_forum_prams);

		FrameLayout frame_logo = new FrameLayout(ctx);
		ImageView back_round = new ImageView(ctx);
		back_round.setBackgroundDrawable(BitmapCache.getDrawable(ctx,
				"cricle.png"));
		frame_logo.addView(back_round);

		imag = new ImageView(ctx);
		imag.setBackgroundDrawable(BitmapCache.getDrawable(ctx, "logo.png"));
		frame_logo.addView(imag, new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.CENTER));

		// btn.setId(100005);
		// btn.setOnClickListener(this);

		FrameLayout.LayoutParams l_logo = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		l_logo.gravity = Gravity.RIGHT;
		all.addView(frame_logo, l_logo);

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
			lp.x += (int) (x - lastX);
			lp.y += (int) (y - lastY);
			tag = 1;

			break;
		case MotionEvent.ACTION_UP:

			int newoffsetX = lp.x;
			int newoffsetY = lp.y;
			if (oldoffsetX == newoffsetX && oldoffsetY == newoffsetY) {
				// hideView();
				updatePostion();
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
		// Toast.makeText(getContext(), "锟斤拷锟斤拷锟斤拷锟斤拷锟�", Toast.LENGTH_LONG).show();
		// hideView();
		// }
		// break;
		// }
		return true;

	}

	private void showPopuWindow(Context ctx) {

		LinearLayout ll_forum = new LinearLayout(ctx);
		ll_forum.setOrientation(VERTICAL);
		// ll_forum.setId(200001);
		ll_forum.setPadding(20, 0, 20, 0);

		ImageView image_forum = new ImageView(ctx);
		image_forum.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.ic_launcher));
		ll_forum.addView(image_forum);
		// image_forum.setId(200002);
		image_forum.setOnClickListener(this);

		TextView txt_forum = new TextView(ctx);
		txt_forum.setText("锟斤拷坛asdfsadfsdaf");
		LayoutParams ll_txt = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		ll_txt.gravity = Gravity.CENTER;
		ll_forum.addView(txt_forum, ll_txt);

		PopupWindow window = new PopupWindow(ll_forum);
		// window.showAtLocation(imag, Gravity.LEFT, 0, 0);
		System.out.println("锟斤拷锟斤拷锟斤拷");
		window.showAsDropDown(this);

	}

	private void updatePostion() {
		// WindowManager manager =
		// (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
		// manager.updateViewLayout(this, lp);
		// FloatWindowManager.createCenterView(getContext());
		FloatWindowManager.disPlayCenterView(getContext(), 1);

	}

	// private void updateView(){
	// WindowManager manager =
	// (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
	// FloatPersonView f = new FloatPersonView(getContext());
	// manager.removeView(this);
	// manager.addView(f, lp);
	//
	// }

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
			LinearLayout ly = new LinearLayout(getContext());
			Button btn = new Button(getContext());
			btn.setText("======================");
			ly.addView(btn);
			PopupWindow window = new PopupWindow(ly);
			window.showAsDropDown(this);
			break;
		case 100004:
			FloatWindowManager.hideFloatView(getContext());
            PersonCenterManager.getInstance().startPersonCenter(getContext());
			break;
		case 100005:

			// hideView();//
			break;
		default:
			break;

		}

	}
}
