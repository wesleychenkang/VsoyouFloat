package com.vsyou.sdk.vscenter.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.view.View.OnClickListener;
import com.example.androidfloat.R;

public class FloatPersonView extends LinearLayout implements OnClickListener {

	public FloatPersonView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		// TODO Auto-generated constructor stub
	}

	public FloatPersonView(Context context) {
		super(context);
		initView(context);
		// TODO Auto-generated constructor stub
	}

	private void initView(Context ctx) {

		setOrientation(HORIZONTAL);
		LinearLayout  ll_forum = new LinearLayout(ctx);
		ll_forum.setOrientation(VERTICAL);
		ll_forum.setId(200001);
		ll_forum.setPadding(20, 0, 20, 0);
		 
		ImageView image_forum = new ImageView(ctx);
		image_forum.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
		ll_forum.addView(image_forum);
		image_forum.setId(200002);
		image_forum.setOnClickListener(this);
		
		TextView txt_forum = new TextView(ctx);
		txt_forum.setText("ÂÛÌ³");
		LayoutParams ll_txt = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		ll_txt.gravity = Gravity.CENTER;
		ll_forum.addView(txt_forum,ll_txt);
		LayoutParams ll_forum_prams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
		addView(ll_forum,ll_forum_prams);
	
		
		LinearLayout ll_person = new LinearLayout(ctx);
		ll_person.setOrientation(VERTICAL);
		ll_person.setId(200003);
	    ImageView image1= new ImageView(ctx);
	    image1.setId(100004);
	    image1.setOnClickListener(this);
	    image1.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
	    ll_person.addView(image1);
	    
	    TextView txt = new TextView(ctx);
	    txt.setText("¸öÈË");
	    ll_person.addView(txt,ll_txt);
	    LayoutParams ll_person_prams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
		ll_forum_prams.setMargins(100, 0, 100, 0);
	    
	    addView(ll_person,ll_person_prams);
	    
	    
	    LinearLayout l = new LinearLayout(ctx);
		l.setBackgroundColor(Color.RED);
		ImageView btn = new ImageView(ctx);
		btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
		//btn.setId(100005);
		//btn.setOnClickListener(this);
		LayoutParams lbtn = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lbtn.gravity = Gravity.CENTER;
		l.addView(btn,lbtn);
		addView(l,new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT));
	}

	@Override
	public void onClick(View arg0) {
		
	}
   
}
