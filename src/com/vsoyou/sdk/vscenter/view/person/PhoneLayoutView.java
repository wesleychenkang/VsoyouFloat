package com.vsoyou.sdk.vscenter.view.person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vsoyou.sdk.vscenter.ParamChain;
import com.vsoyou.sdk.vscenter.util.BitmapCache;
import com.vsoyou.sdk.vscenter.util.MetricUtil;
import com.vsoyou.sdk.vscenter.util.RegularUtil;

public class PhoneLayoutView extends BaseLayout {
	private Button btn_token;
	private EditText edit_phone;
	private EditText edit_token;
	private Button btn_confirm;

	public PhoneLayoutView(Context context, ParamChain chain) {
		super(context, chain);
		super.initUI(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initEnv(Context ctx, ParamChain env) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void onInitUI(Context ctx) {
		// TODO Auto-generated method stub
		setTitleText("手机绑定");
		FrameLayout sub = (FrameLayout) getView_subject(ctx);
		LinearLayout all = new LinearLayout(ctx);
		all.setPadding(MetricUtil.getDip(ctx, 20), MetricUtil.getDip(ctx, 30),
				MetricUtil.getDip(ctx, 20), MetricUtil.getDip(ctx, 20));
		all.setOrientation(VERTICAL);
		sub.addView(all, new LayoutParams(LP_MM));

		LinearLayout top = new LinearLayout(ctx);
		top.setOrientation(VERTICAL);
		top.setBackgroundDrawable(BitmapCache.getNinePatchDrawable(ctx,
				"person_black.9.png"));
		LayoutParams lp_top = new LayoutParams(LP_MW);
		all.addView(top, lp_top);

		LinearLayout top_phone = new LinearLayout(ctx);
		top_phone.setOrientation(HORIZONTAL);
		top.addView(top_phone, new LayoutParams(LP_MW));

		LinearLayout top_phone_left = new LinearLayout(ctx);
		LayoutParams lp_left = new LayoutParams(LP_MW);
		lp_left.weight = 0.4f;
		top_phone.addView(top_phone_left, lp_left);

		LayoutParams lp_edit_phone = new LayoutParams(LP_WW);
		lp_edit_phone.gravity = Gravity.CENTER | Gravity.LEFT;
		ImageView phone = new ImageView(ctx);
		phone.setBackgroundDrawable(BitmapCache.getDrawable(ctx,
				"phone_block.png"));
		lp_edit_phone.leftMargin = MetricUtil.getDip(ctx, 6);
		top_phone_left.addView(phone, lp_edit_phone);

		edit_phone = new EditText(ctx);
		edit_phone.setHint("请输入手机号码");
		edit_phone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		edit_phone.setTextColor(Color.BLACK);
		edit_phone.setImeOptions(EditorInfo.IME_ACTION_DONE);
		edit_phone.setInputType(InputType.TYPE_CLASS_PHONE);
		edit_phone.setBackgroundColor(Color.WHITE);
		edit_phone.setPadding(MetricUtil.getDip(ctx, 6),
				MetricUtil.getDip(ctx, 20), 0, MetricUtil.getDip(ctx, 20));
		LayoutParams lp_edit = new LayoutParams(LP_MW);
		top_phone_left.addView(edit_phone, lp_edit);

		LinearLayout l_btn_token = new LinearLayout(ctx);
		l_btn_token.setGravity(Gravity.RIGHT | Gravity.CENTER);
		LayoutParams lp_token = new LayoutParams(LP_MW);
		lp_token.weight = 0.6f;
		lp_token.gravity = Gravity.CENTER;

		btn_token = new Button(ctx);
		btn_token.setGravity(Gravity.CENTER);
		btn_token.setText("获取验证码");
		btn_token.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
		btn_token.setBackgroundDrawable(BitmapCache.getDrawable(ctx,
				"get_token.png"));
		btn_token.setOnClickListener(this);
		btn_token.setTextColor(Color.parseColor("#4C4C4C"));
		LayoutParams lp_btn_token = new LayoutParams(LP_WW);
		lp_btn_token.rightMargin = MetricUtil.getDip(ctx, 6);
		lp_btn_token.height = MetricUtil.getDip(ctx, 40);
		lp_btn_token.width = MetricUtil.getDip(ctx, 120);
		l_btn_token.addView(btn_token, lp_btn_token);

		top_phone.addView(l_btn_token, lp_token);

		// 线条

		View line = new View(ctx);
		line.setBackgroundColor(Color.rgb(235, 235, 235));
		LayoutParams lp_line = new LayoutParams(LP_MW);
		lp_line.height = 1;
		top.addView(line, lp_line);

		// 密码

		LinearLayout top_token = new LinearLayout(ctx);
		top_phone.setOrientation(HORIZONTAL);
		top_token.setGravity(Gravity.CENTER | Gravity.LEFT);
		top.addView(top_token, lp_top);

		ImageView token = new ImageView(ctx);
		token.setBackgroundDrawable(BitmapCache.getDrawable(ctx,
				"password_block.png"));
		LayoutParams lp_img_token = new LayoutParams(LP_WW);
		lp_img_token.leftMargin = MetricUtil.getDip(ctx, 6);
		top_token.addView(token, lp_img_token);

		edit_token = new EditText(ctx);
		edit_token.setHint("请输入验证码");
		edit_token.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		edit_token.setTextColor(Color.BLACK);
		edit_token.setInputType(InputType.TYPE_CLASS_PHONE);
		edit_token.setPadding(MetricUtil.getDip(ctx, 6),
				MetricUtil.getDip(ctx, 20), 0, MetricUtil.getDip(ctx,20));
		edit_token.setBackgroundColor(Color.WHITE);
		LayoutParams lp_edit_token = new LayoutParams(LP_MW);
		top_token.addView(edit_token, lp_edit_token);

		// 确定按钮

		LinearLayout lin_buttom = new LinearLayout(ctx);
		lin_buttom.setGravity(Gravity.CENTER);
		btn_confirm = new Button(ctx);
		btn_confirm.setText("确定");
		btn_confirm.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
		btn_confirm.setOnClickListener(this);
		btn_confirm.setBackgroundDrawable(BitmapCache.getDrawable(ctx,
				"btn_blue.png"));
		LayoutParams lp_confirm = new LayoutParams(LP_WW);
		lp_confirm.gravity = Gravity.CENTER;
		lin_buttom.addView(btn_confirm, lp_confirm);
		LayoutParams lp_buttom = new LayoutParams(LP_MM);
		all.addView(lin_buttom, lp_buttom);
	}

	private String getNumber() {
		return edit_phone.getText().toString();
	}

	private String getToken() {
		return edit_token.getText().toString();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		if (v == btn_token) {
			if (RegularUtil.checkPhoneNumber(getNumber(), getContext())) {
              //进行获取验证码
			}
		}
		if (v == btn_confirm) {
			if (RegularUtil.checkToken(getToken(), getContext())) {
				showDialog(getActivity());
				//进行发送验证码
			}

		}
	}

}
