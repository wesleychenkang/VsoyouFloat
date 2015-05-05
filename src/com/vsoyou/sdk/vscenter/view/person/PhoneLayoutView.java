package com.vsoyou.sdk.vscenter.view.person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vsoyou.sdk.ParamChain;
import com.vsoyou.sdk.vscenter.util.BitmapCache;
import com.vsoyou.sdk.vscenter.util.MetricUtil;

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
				"black_white.9.png"));
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
		edit_phone.setTextColor(Color.parseColor("#cbcdcb"));
		edit_phone.setImeOptions(EditorInfo.IME_ACTION_DONE);
		edit_phone.setInputType(InputType.TYPE_CLASS_PHONE);
		edit_phone.setBackgroundColor(Color.TRANSPARENT);
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
		btn_token.setBackgroundDrawable(BitmapCache.getDrawable(ctx,
				"get_token.png"));
		btn_token.setText("获取验证码");
		btn_token.setOnClickListener(this);
		btn_token.setTextColor(Color.parseColor("#2471d1"));
		LayoutParams lp_btn_token = new LayoutParams(LP_WW);
		lp_btn_token.rightMargin = MetricUtil.getDip(ctx, 6);
		l_btn_token.addView(btn_token, lp_btn_token);

		top_phone.addView(l_btn_token, lp_token);

		// 线条

		View line = new View(ctx);
		line.setBackgroundColor(Color.rgb(235, 235, 235));
		LayoutParams lp_line = new LayoutParams(LP_MW);
		lp_line.height = 3;
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
		edit_token.setTextColor(Color.parseColor("#cbcdcb"));
		edit_token.setInputType(InputType.TYPE_CLASS_PHONE);
		edit_token.setPadding(MetricUtil.getDip(ctx, 6),
				MetricUtil.getDip(ctx, 20), 0, MetricUtil.getDip(ctx, 20));
		edit_token.setBackgroundColor(Color.TRANSPARENT);
		LayoutParams lp_edit_token = new LayoutParams(LP_MW);
		top_token.addView(edit_token, lp_edit_token);

		// 确定按钮

		LinearLayout lin_buttom = new LinearLayout(ctx);
		btn_confirm = new Button(ctx);
		btn_confirm.setText("确定");
		btn_confirm.setOnClickListener(this);
		btn_confirm.setBackgroundDrawable(BitmapCache.getNinePatchDrawable(ctx,
				"btn_blue.9.png"));
		LayoutParams lp_confirm = new LayoutParams(LP_MW);
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
    private boolean checkNumber (){
    	String number = getNumber();
    	if(TextUtils.isEmpty(number)){
			Toast.makeText(getContext(), "请输入手机号码", Toast.LENGTH_SHORT).show();
			return false;
		 }
    	String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$"; 
    	Pattern p = Pattern.compile(regExp); 
    	Matcher m = p.matcher(number); 
    	Toast.makeText(getContext(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
    	return m.find();
    }
    
    private boolean checkToken(){
    	String token = getToken();
    	if(TextUtils.isEmpty(token)){
    		Toast.makeText(getContext(), "验证码不能为空", Toast.LENGTH_SHORT).show();
    		return false;
    	}
    	if(token.trim().length()>8||token.trim().length()<3){
    		Toast.makeText(getContext(), "验证码长度不正确", Toast.LENGTH_SHORT).show();
    		return false;
    	}
    	return true;
    	
    }
    
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		if (v == btn_token) {
			checkNumber ();
		}
		if(v==btn_confirm){
			checkToken();
			
		}
	}

}
