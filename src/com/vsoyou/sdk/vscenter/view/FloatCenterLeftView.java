package com.vsoyou.sdk.vscenter.view;

import java.lang.reflect.Field;

import android.annotation.SuppressLint;
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
	private ImageView imag_person;
    private LinearLayout ll_person;
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
	    ll_person = new LinearLayout(ctx);
		ll_person.setOrientation(VERTICAL);
		ll_person.setGravity(Gravity.CENTER_VERTICAL);
		ll_person.setId(100003);
		ll_person.setOnClickListener(this);
		imag_person = new ImageView(ctx);
		imag_person.setId(100004);
		imag_person.setOnClickListener(this);
		imag_person.setBackgroundDrawable(BitmapCache.getDrawable(ctx,
				"person.png"));
		LayoutParams lp_image1 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		lp_image1.gravity = Gravity.CENTER_HORIZONTAL;
		ll_person.addView(imag_person, lp_image1);

		TextView txt = new TextView(ctx);
		txt.setText("个人中心");
		txt.setTextColor(Color.parseColor("#2d2d2d"));
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
		txt_forum.setTextColor(Color.parseColor("#2d2d2d"));
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
		imag.setOnClickListener(this);

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
		oldoffsetX = lp.x;
		oldoffsetY = lp.y;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastX = x;
			lastY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			float newx = event.getX();
			float newy = event.getY();
			float movex = newx - lastX;
			float movey = newy -lastY;
			if(Math.abs(movex)>3||Math.abs(movey)>3){
				updatePostion();
				
			}
			lp.x += (int) (x - lastX);
			lp.y += (int) (y - lastY);
		case MotionEvent.ACTION_UP:

			break;

		}
		return false;

	}

	private void updatePostion() {
		FloatWindowManager.disPlayCenterView(getContext(), 1);
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
		if (arg0 == ll_person) {
			FloatWindowManager.hideFloatView(getContext());
			PersonCenterManager.getInstance().startPersonCenter(getContext());
		}
		if(arg0==imag){
			updatePostion();
		}
	}
}
