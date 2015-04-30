package com.vsoyou.sdk.vscenter.view.person;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vsoyou.sdk.vscenter.util.BitmapCache;
import com.vsoyou.sdk.vscenter.util.MetricUtil;

public class PersonLayoutView extends BaseLayout {

	public PersonLayoutView(Context context) {
		super(context);
		super.initUI(context);
	}

	@Override
	protected void onInitUI(Context ctx) {
		FrameLayout sub = (FrameLayout) getView_subject(ctx);
		LinearLayout all = new LinearLayout(ctx);
		sub.addView(all, new FrameLayout.LayoutParams(LP_MM));
		all.setOrientation(HORIZONTAL);
		createLeftView(all, ctx);
		createRightView(all, ctx);

	}

	private void createLeftView(LinearLayout all, Context ctx) {
		LinearLayout lay = new LinearLayout(ctx);
		// lay.setBackgroundColor(Color.RED);
		lay.setOrientation(VERTICAL);
		lay.setGravity(Gravity.CENTER);
		LayoutParams lp_lay = new LayoutParams(LP_MM);
		lp_lay.weight = 0.7f;
		all.addView(lay, lp_lay);

		ImageView img_icon = new ImageView(ctx);
		img_icon.setBackgroundDrawable(BitmapCache.getDrawable(ctx, "icon.png"));
		lay.addView(img_icon, new LayoutParams(LP_WW));

		TextView txt_name = new TextView(ctx);
		txt_name.setText("vsoyou账号登录");
		txt_name.setPadding(0, 10, 0, 10);
		lay.addView(txt_name, new LayoutParams(LP_WW));

		TextView txt_count = new TextView(ctx);
		txt_count.setText("13632570627");
		lay.addView(txt_count, new LayoutParams(LP_WW));

	}

	private void createRightView(LinearLayout rv, Context ctx) {
		ScrollView scroll = new ScrollView(ctx);
		LayoutParams lp_lay = new LayoutParams(LP_MM);
		lp_lay.weight = 0.3f;
		rv.addView(scroll, lp_lay);

		LinearLayout all = new LinearLayout(ctx);
		all.setOrientation(VERTICAL);
		
		scroll.setPadding(MetricUtil.getDip(ctx, 40), MetricUtil.getDip(ctx, 50), MetricUtil.getDip(ctx, 50), MetricUtil.getDip(ctx, 50));
		all.setBackgroundColor(Color.WHITE);
		scroll.addView(all, new LayoutParams(LP_MW));
		for(int i = 0;i<12;i++){
		{
			LinearLayout l_phone = new LinearLayout(ctx);
			l_phone.setOrientation(HORIZONTAL);
			l_phone.setPadding(15, 15, 15, 15);
			all.addView(l_phone, new LayoutParams(LP_MW));
            
			//个人手机 左边图像区域
			LinearLayout l_phone_left = new LinearLayout(ctx);
			l_phone_left.setOrientation(HORIZONTAL);
			l_phone_left.setGravity(Gravity.LEFT);
			
			LayoutParams lp_left = new LayoutParams(LP_MW);
			lp_left.weight = 0.4f;
            l_phone.addView(l_phone_left,lp_left);
            
            ImageView imag_phone = new ImageView(ctx);
            imag_phone.setBackgroundDrawable(BitmapCache.getDrawable(ctx, "phone.png"));
            l_phone_left.addView(imag_phone,new LayoutParams(LP_WW));
            
            TextView txt_phone = new TextView (ctx);
            txt_phone.setText("手机");
            txt_phone.setPadding(MetricUtil.getDip(ctx,15), 0, 0, 0);
            l_phone_left.addView(txt_phone);
            
            LinearLayout l_phone_right = new LinearLayout(ctx);
            l_phone_right.setOrientation(HORIZONTAL);
            l_phone_right.setGravity(Gravity.RIGHT);
            LayoutParams lp_right = new LayoutParams(LP_MW);
            lp_right.weight = 0.6f;
            l_phone.addView(l_phone_right,lp_right);
            TextView text = new TextView(ctx);
            text.setText("未绑定");
            text.setPadding(0, 0, 10, 0);
            l_phone_right.addView(text);
            
            ImageView img_open = new ImageView(ctx);
            img_open.setBackgroundDrawable(BitmapCache.getDrawable(ctx, "open.png"));
            LayoutParams lp_imag = new LayoutParams(LP_WW);
            lp_imag.gravity = Gravity.CENTER;
            l_phone_right.addView(img_open,lp_imag);
		}
		
		}
		
		

	}

}
