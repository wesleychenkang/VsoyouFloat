package com.vsoyou.sdk.vscenter.view.person;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vsoyou.sdk.ParamChain;
import com.vsoyou.sdk.vscenter.util.BitmapCache;
import com.vsoyou.sdk.vscenter.util.MetricUtil;

public class PasswordLayout extends BaseLayout{

	public PasswordLayout(Context context, ParamChain env) {
		super(context, env);
		super.initUI(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onInitUI(Context ctx) {
		setTitleText("修改密码");
		FrameLayout sub =(FrameLayout) getView_subject(ctx);
		LinearLayout all = new LinearLayout(ctx);
		sub.addView(all);
		all.setOrientation(VERTICAL);
		all.setPadding(MetricUtil.getDip(ctx, 20.0F), MetricUtil.getDip(ctx, 10.0F), MetricUtil.getDip(ctx, 20.0F), 0);
		//账号
		TextView txt_name = new TextView(ctx);
		txt_name.setText("威搜游账号:"+"WN16880368");
		txt_name.setTextColor(Color.parseColor("#151515"));
		txt_name.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
		txt_name.setPadding(0, MetricUtil.getDip(ctx, 10.0F), 0, MetricUtil.getDip(ctx, 15.0F));
		all.addView(txt_name);
		
		//密码布局
		LinearLayout password = new LinearLayout(ctx);
		password.setBackgroundDrawable(BitmapCache.getNinePatchDrawable(ctx, "black_white.9.png"));
		password.setOrientation(VERTICAL);
		all.addView(password);
	
		Drawable drawalbe = BitmapCache.getDrawable(ctx, "password_block.png");
		drawalbe.setBounds(0, 0, MetricUtil.getDip(ctx, 25.0F),
				MetricUtil.getDip(ctx, 25.0F));
		EditText old = new EditText(ctx);
		old.setBackgroundColor(color.transparent);
		old.setHint("请输入旧的密码");
		old.setTextColor(Color.parseColor("#cbcdcb"));
		old.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
		old.setPadding(MetricUtil.getDip(ctx, 10.0F), MetricUtil.getDip(ctx, 10.0F), 0,MetricUtil.getDip(ctx, 10.0F));
		old.setCompoundDrawables(drawalbe, null, null, null);
		old.setCompoundDrawablePadding(MetricUtil.getDip(ctx, 10.0F));
		password.addView(old);
		
		View line = createLine(ctx);
		LayoutParams lp_line = new LayoutParams(LP_MW);
		lp_line.height = MetricUtil.getDip(ctx, 1.0F);
		password.addView(line,lp_line);
		
		EditText newold = new EditText(ctx);
		newold.setBackgroundColor(color.transparent);
		newold.setHint("请输入新的密码");
		newold.setTextColor(Color.parseColor("#cbcdcb"));
		newold.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
		newold.setPadding(MetricUtil.getDip(ctx, 10.0F), MetricUtil.getDip(ctx, 10.0F), 0,MetricUtil.getDip(ctx, 10.0F));
		newold.setCompoundDrawables(drawalbe, null, null, null);
		newold.setCompoundDrawablePadding(MetricUtil.getDip(ctx, 10.0F));
		password.addView(newold);
		
		View line2 = createLine(ctx);
		password.addView(line2,lp_line);
		
		EditText rnewold = new EditText(ctx);
		rnewold.setBackgroundColor(color.transparent);
		rnewold.setHint("请再次输入新的密码");
		rnewold.setTextColor(Color.parseColor("#cbcdcb"));
		rnewold.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
		rnewold.setPadding(MetricUtil.getDip(ctx, 10.0F), MetricUtil.getDip(ctx, 10.0F), 0,MetricUtil.getDip(ctx, 10.0F));
		rnewold.setCompoundDrawables(drawalbe, null, null, null);
		rnewold.setCompoundDrawablePadding(MetricUtil.getDip(ctx, 10.0F));
		password.addView(rnewold);
		
		
		Button btn_submit = new Button(ctx);
		btn_submit.setBackgroundDrawable(BitmapCache.getNinePatchDrawable(ctx, "btn_blue.9.png"));
		btn_submit.setText("提交");
		btn_submit.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
		btn_submit.setPadding(0, MetricUtil.getDip(ctx, 10.0F), 0, MetricUtil.getDip(ctx, 10.0F));
		LayoutParams lp_submit = new LayoutParams(LP_MW);
		lp_submit.topMargin = MetricUtil.getDip(ctx, 20.0F);
		all.addView(btn_submit,lp_submit);
//		
	}
	
	private View createLine(Context ctx){
		View line = new View(ctx);
		line.setBackgroundColor(Color.rgb(235, 235, 235));
		return line;
	}

	@Override
	protected void initEnv(Context ctx, ParamChain env) {
		// TODO Auto-generated method stub
		
	}
	
   
}
