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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidfloat.R;
import com.vsoyou.sdk.vscenter.FloatWindowManager;

public class FloatCenterRightView extends LinearLayout implements OnClickListener {
	private WindowManager.LayoutParams lp;
	private int statusBarHeight;
	private int oldoffsetX;
	private int oldoffsetY;
	private int tag = 0;
	private float lastX, lastY;
	private ImageView imag;
	 
	public FloatCenterRightView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public FloatCenterRightView(Context context) {
		super(context);
		initView(context);
	}
	
	private void initView(Context ctx){
		setOrientation(HORIZONTAL);
        setVisibility(View.GONE);
	    LinearLayout l = new LinearLayout(ctx);
		l.setBackgroundColor(Color.RED);
		l.setPadding(0, 15, 0, 15);
		imag = new ImageView(ctx);
		imag.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
		
		LayoutParams lbtn = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lbtn.gravity = Gravity.CENTER;
		l.addView(imag,lbtn);
		LayoutParams ll_btn_prams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		ll_btn_prams.weight = 0.3f;
		addView(l,ll_btn_prams);
		
		
		LinearLayout  ll_forum = new LinearLayout(ctx);
		ll_forum.setOrientation(VERTICAL);
		ll_forum.setId(100001);
		ll_forum.setPadding(20, 0, 20, 0);
		 
		ImageView image_forum = new ImageView(ctx);
		image_forum.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
		ll_forum.addView(image_forum);
		image_forum.setId(100002);
		image_forum.setOnClickListener(this);
		
		TextView txt_forum = new TextView(ctx);
		txt_forum.setText("��̳");
		txt_forum.setId(1000021);
		LayoutParams ll_txt = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		ll_txt.gravity = Gravity.CENTER;
		ll_forum.addView(txt_forum,ll_txt);
		LayoutParams ll_forum_prams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		ll_forum_prams.weight = 0.3f;
		addView(ll_forum,ll_forum_prams);
	
		
		LinearLayout ll_person = new LinearLayout(ctx);
		ll_person.setOrientation(VERTICAL);
		ll_person.setId(100003);
	    ImageView image1= new ImageView(ctx);
	    image1.setId(100004);
	    image1.setOnClickListener(this);
	    image1.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
	    ll_person.addView(image1);
	    
	    TextView txt = new TextView(ctx);
	    txt.setText("��������");
	    ll_person.addView(txt,ll_txt);
	    LayoutParams ll_person_prams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		ll_forum_prams.setMargins(5, 0, 5, 0);
		ll_person_prams.weight = 0.3f;
	    addView(ll_person,ll_person_prams);
	    
	    
	
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
		txt_forum.setText("��̳asdfsadfsdaf");
		LayoutParams ll_txt = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		ll_txt.gravity = Gravity.CENTER;
		ll_forum.addView(txt_forum,ll_txt);
		
		
		PopupWindow window = new PopupWindow(ll_forum);
		//window.showAtLocation(imag, Gravity.LEFT, 0, 0);
		System.out.println("������");
		window.showAsDropDown(this);
		
	}
	
	private void updatePostion(){
		//FloatWindowManager.createCenterView(getContext());
		
		FloatWindowManager.disPlayCenterView(getContext(),0);
		
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
			Toast.makeText(getContext(), "��̳", Toast.LENGTH_SHORT).show();
			break;
		case 100004:
			Toast.makeText(getContext(), "����", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent("android.intent.action.PersonCenterActivity");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getContext().getApplicationContext().startActivity(intent);
			FloatWindowManager.hideFloatView(getContext());
			break;
		case 100005:
			Toast.makeText(getContext(), "����", Toast.LENGTH_SHORT).show();
			
//			hideView();//
			break;
			default:
				break;
		
		}
		
             
	}
}