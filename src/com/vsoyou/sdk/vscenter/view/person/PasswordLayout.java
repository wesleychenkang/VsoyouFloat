package com.vsoyou.sdk.vscenter.view.person;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vsoyou.sdk.vscenter.ParamChain;
import com.vsoyou.sdk.vscenter.util.BitmapCache;
import com.vsoyou.sdk.vscenter.util.MetricUtil;

public class PasswordLayout extends BaseLayout {
	private EditText old_pass;
	private EditText new_pass;
	private EditText rnew_pass;
	private Button btn_submit;

	public PasswordLayout(Context context, ParamChain env) {
		super(context, env);
		super.initUI(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onInitUI(Context ctx) {
		setTitleText("修改密码");
		FrameLayout sub = (FrameLayout) getView_subject(ctx);
		ScrollView scroll = new ScrollView(ctx);
		sub.addView(scroll, new LayoutParams(LP_MM));

		LinearLayout all = new LinearLayout(ctx);
		scroll.addView(all);
		all.setOrientation(VERTICAL);
		all.setPadding(MetricUtil.getDip(ctx, 20.0F),
				MetricUtil.getDip(ctx, 10.0F), MetricUtil.getDip(ctx, 20.0F), 0);
		// 账号
		TextView txt_name = new TextView(ctx);
		txt_name.setText("威搜游账号:" + "WN16880368");
		txt_name.setTextColor(Color.parseColor("#151515"));
		txt_name.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
		txt_name.setPadding(0, MetricUtil.getDip(ctx, 10.0F), 0,
				MetricUtil.getDip(ctx, 10.0F));
		all.addView(txt_name);

		// 密码布局
		LinearLayout password = new LinearLayout(ctx);
		password.setBackgroundDrawable(BitmapCache.getNinePatchDrawable(ctx,
				"person_black.9.png"));
		password.setOrientation(VERTICAL);
		all.addView(password);

		Drawable drawalbe = BitmapCache.getDrawable(ctx, "password_block.png");
		drawalbe.setBounds(0, 0, MetricUtil.getDip(ctx, 25.0F),
				MetricUtil.getDip(ctx, 25.0F));
		old_pass = new EditText(ctx);
		old_pass.setBackgroundColor(color.transparent);
		old_pass.setHint("请输入旧的密码");
		old_pass.setTextColor(Color.parseColor("#cbcdcb"));
		old_pass.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		old_pass.setPadding(MetricUtil.getDip(ctx, 6f),
				MetricUtil.getDip(ctx, 15f), 0, MetricUtil.getDip(ctx, 15f));
		old_pass.setCompoundDrawables(drawalbe, null, null, null);
		old_pass.setCompoundDrawablePadding(MetricUtil.getDip(ctx, 10.0F));
		password.addView(old_pass);

		View line = createLine(ctx);
		LayoutParams lp_line = new LayoutParams(LP_MW);
		lp_line.height = MetricUtil.getDip(ctx, 1.0F);
		password.addView(line, lp_line);

		new_pass = new EditText(ctx);
		new_pass.setBackgroundColor(color.transparent);
		new_pass.setHint("请输入新的密码");
		new_pass.setTextColor(Color.parseColor("#cbcdcb"));
		new_pass.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		new_pass.setPadding(MetricUtil.getDip(ctx, 6f),
				MetricUtil.getDip(ctx, 15f), 0, MetricUtil.getDip(ctx, 15f));
		new_pass.setCompoundDrawables(drawalbe, null, null, null);
		new_pass.setCompoundDrawablePadding(MetricUtil.getDip(ctx, 10.0F));
		password.addView(new_pass);

		View line2 = createLine(ctx);
		password.addView(line2, lp_line);

		rnew_pass = new EditText(ctx);
		rnew_pass.setBackgroundColor(color.transparent);
		rnew_pass.setHint("请再次输入新的密码");
		rnew_pass.setTextColor(Color.parseColor("#cbcdcb"));
		rnew_pass.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		rnew_pass.setPadding(MetricUtil.getDip(ctx, 6f),
				MetricUtil.getDip(ctx, 15f), 0, MetricUtil.getDip(ctx, 15f));
		rnew_pass.setCompoundDrawables(drawalbe, null, null, null);
		rnew_pass.setCompoundDrawablePadding(MetricUtil.getDip(ctx, 10.0F));
		password.addView(rnew_pass);

		btn_submit = new Button(ctx);
		btn_submit.setOnClickListener(this);
		btn_submit.setBackgroundDrawable(BitmapCache.getDrawable(ctx,
				"btn_blue.png"));
		btn_submit.setText("提交");
		btn_submit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
		LayoutParams lp_submit = new LayoutParams(LP_WW);
		lp_submit.topMargin = MetricUtil.getDip(ctx, 15.0F);
		lp_submit.gravity = Gravity.CENTER;
		all.addView(btn_submit, lp_submit);
		//
	}

	private View createLine(Context ctx) {
		View line = new View(ctx);
		line.setBackgroundColor(Color.rgb(235, 235, 235));
		return line;
	}

	@Override
	protected void initEnv(Context ctx, ParamChain env) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);

	}

}
