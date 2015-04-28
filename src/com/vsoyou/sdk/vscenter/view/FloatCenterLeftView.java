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
import android.widget.LinearLayout.LayoutParams;

import com.example.androidfloat.R;
import com.vsoyou.sdk.vscenter.FloatWindowManager;
import com.vsoyou.sdk.vscenter.util.BitmapCache;
import com.vsoyou.sdk.vscenter.util.MetricUtil;

@SuppressLint("NewApi")
public class FloatCenterLeftView extends LinearLayout implements OnClickListener {
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
	
	private void initView(Context ctx){
		setOrientation(HORIZONTAL);
		setVisibility(View.GONE);
		setBackgroundDrawable(BitmapCache.getDrawable(ctx, "block_left.png"));
		LinearLayout  ll_forum = new LinearLayout(ctx);
		ll_forum.setOrientation(VERTICAL);
		ll_forum.setGravity(Gravity.CENTER_VERTICAL);
		ll_forum.setId(100001);
		//ll_forum.setPadding(20, 0, 20, 0);
		 
		ImageView image_forum = new ImageView(ctx);
		image_forum.setBackgroundDrawable(BitmapCache.getDrawable(ctx, "forum.png"));
		LayoutParams lp_image_forum =  new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp_image_forum.gravity = Gravity.CENTER_HORIZONTAL;
		ll_forum.addView(image_forum,lp_image_forum);
		image_forum.setId(100002);
		image_forum.setOnClickListener(this);
		
		TextView txt_forum = new TextView(ctx);
		txt_forum.setText("论坛");
		txt_forum.setPadding(0, MetricUtil.getDip(ctx, 10), 0, 0);
		txt_forum.setId(1000021);
		//txt_forum.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, getResources().getDrawable(R.drawable.ic_launcher));
		LayoutParams ll_txt = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		ll_txt.gravity = Gravity.CENTER;
		ll_forum.addView(txt_forum,ll_txt);
		LayoutParams ll_forum_prams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		ll_forum_prams.weight = 0.3f;
		addView(ll_forum,ll_forum_prams);
	
		
		LinearLayout ll_person = new LinearLayout(ctx);
		ll_person.setOrientation(VERTICAL);
		ll_person.setGravity(Gravity.CENTER_VERTICAL);
		ll_person.setId(100003);
	    ImageView image1= new ImageView(ctx);
	    image1.setId(100004);
	    image1.setOnClickListener(this);
	    image1.setBackgroundDrawable(BitmapCache.getDrawable(ctx, "person.png"));
	    LayoutParams lp_image1 =  new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
	    lp_image1.gravity = Gravity.CENTER_HORIZONTAL;
	    ll_person.addView(image1,lp_image1);
	    
	    TextView txt = new TextView(ctx);
	    
	    txt.setText("个人中心");
	    txt.setPadding(0, MetricUtil.getDip(ctx, 10), 0, 0);
	    ll_person.addView(txt,ll_txt);
	    LayoutParams ll_person_prams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		ll_forum_prams.setMargins(MetricUtil.getDip(ctx, 5), 0, MetricUtil.getDip(ctx, 5), 0);
		ll_person_prams.weight = 0.3f;
	    addView(ll_person,ll_person_prams);
	    
	    
	    LinearLayout l = new LinearLayout(ctx);
		//l.setBackgroundColor(Color.RED);
		
		
		FrameLayout lay = new FrameLayout(ctx);
	    ImageView back_round = new ImageView(ctx);
	    back_round.setBackgroundDrawable(BitmapCache.getDrawable(ctx, "cricle.png"));
	    lay.addView(back_round);
	    
		imag = new ImageView(ctx);
	    imag.setBackgroundDrawable(BitmapCache.getDrawable(ctx, "logo.png"));
	    lay.addView(imag,new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,Gravity.CENTER));
	   
		//btn.setId(100005);
		//btn.setOnClickListener(this);
		
		
		LayoutParams lbtn = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lbtn.gravity = Gravity.CENTER;
		l.addView(lay,lbtn);
		LayoutParams ll_btn_prams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		ll_btn_prams.weight = 0.3f;
		addView(l,ll_btn_prams);
	
	}

	public void setMangerLayParams(WindowManager.LayoutParams lp) {

		this.lp = lp;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		if(tag == 0){
			oldoffsetX = lp.x;
			oldoffsetY = lp.y;
		}
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			 lastX = x;
			 lastY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			lp.x += (int)(x-lastX);
			lp.y += (int)(y-lastY);
			tag = 1;
			
			break;
		case MotionEvent.ACTION_UP:
			
			int newoffsetX = lp.x;
			int newoffsetY = lp.y;
			if(oldoffsetX == newoffsetX && oldoffsetY == newoffsetY){
				//hideView();
				updatePostion();
			}else{
				tag = 0;
				
			}
			break;
		
		}
		
		
//		switch(event.getAction()){
//		case MotionEvent.ACTION_DOWN:
//			xInView = event.getX();
//			yInView = event.getY();
//			
//			xInScreen = event.getRawX();
//			yInScreen = event.getRawY()-getStatusBarHeight();
//			
//			downXInScreen = event.getRawX();
//			downYInScreen = event.getRawY()-getStatusBarHeight();
//			break;
//			
//		case MotionEvent.ACTION_MOVE:
//			xInScreen = event.getRawX();
//			yInScreen = event.getRawY()-getStatusBarHeight();
//			updatePostion();
//			break;
//		case MotionEvent.ACTION_UP:
//			if(downXInScreen==xInScreen && downYInScreen == yInScreen){
//				Toast.makeText(getContext(), "点击到我了", Toast.LENGTH_LONG).show();
//				hideView();
//			}
//			break;
//		}
		return true;
		
		
	}
	
	private void showPopuWindow(Context ctx){
		
		
		LinearLayout  ll_forum = new LinearLayout(ctx);
		ll_forum.setOrientation(VERTICAL);
		//ll_forum.setId(200001);
		ll_forum.setPadding(20, 0, 20, 0);
		 
		ImageView image_forum = new ImageView(ctx);
		image_forum.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
		ll_forum.addView(image_forum);
		//image_forum.setId(200002);
		image_forum.setOnClickListener(this);
		
		TextView txt_forum = new TextView(ctx);
		txt_forum.setText("论坛asdfsadfsdaf");
		LayoutParams ll_txt = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		ll_txt.gravity = Gravity.CENTER;
		ll_forum.addView(txt_forum,ll_txt);
		
		
		PopupWindow window = new PopupWindow(ll_forum);
		//window.showAtLocation(imag, Gravity.LEFT, 0, 0);
		System.out.println("调用了");
		window.showAsDropDown(this);
		
	}
	
	private void updatePostion(){
//		WindowManager manager = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
//		manager.updateViewLayout(this, lp);
	//	FloatWindowManager.createCenterView(getContext());
		FloatWindowManager.disPlayCenterView(getContext(),1);
		
	}
//	private void updateView(){
//		WindowManager manager = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
//		FloatPersonView f = new FloatPersonView(getContext());
//		manager.removeView(this);
//		manager.addView(f, lp);
//		
//	}
	
	
//	private void hideView(){
//		ImageView view = (ImageView)findViewById(100002);
//		TextView txt = (TextView)findViewById(1000021);
//		if(view.getVisibility()==View.GONE){
//			view.setVisibility(View.VISIBLE);
//			txt.setVisibility(View.VISIBLE);
//			
//		}else {
//			view.setVisibility(View.GONE);
//			txt.setVisibility(View.GONE);
//			
//			
//		}
//		
//	}
//	private void hideView(){
//		LinearLayout l1 = (LinearLayout)findViewById(100001);
//		if(l1.getVisibility()==View.GONE){
//			
//		l1.setVisibility(View.VISIBLE);
//		}else{
//		l1.setVisibility(View.GONE);	
//		}
//		LinearLayout l2 = (LinearLayout)findViewById(100003);
//		if(l2.getVisibility()==View.VISIBLE){
//		l2.setVisibility(View.GONE);
//		}else{
//			l2.setVisibility(View.VISIBLE);	
//		}
//		
//	}
	
	private void displayView(Context ctx){
		
		
	
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
		switch(arg0.getId()){
		case 100002:
			//
			LinearLayout ly = new LinearLayout(getContext());
			Button btn = new Button(getContext());
			btn.setText("======================");
			ly.addView(btn);
			PopupWindow window = new PopupWindow(ly);
			window.showAsDropDown(this);
			Toast.makeText(getContext(), "论坛", Toast.LENGTH_SHORT).show();
			break;
		case 100004:
			Toast.makeText(getContext(), "个人", Toast.LENGTH_SHORT).show();
//			showPopuWindow(getContext());
//			FloatWindowManager.disPlayPersonView(getContext());
			
			Intent intent = new Intent("android.intent.action.PersonCenterActivity");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getContext().getApplicationContext().startActivity(intent);
			FloatWindowManager.hideFloatView(getContext());
			
			break;
		case 100005:
			Toast.makeText(getContext(), "中心", Toast.LENGTH_SHORT).show();
			
			
//			hideView();//
			break;
			default:
				break;
		
		}
		
             
	}
}
