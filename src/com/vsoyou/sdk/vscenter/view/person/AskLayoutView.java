package com.vsoyou.sdk.vscenter.view.person;

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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.vsoyou.sdk.vscenter.ParamChain;
import com.vsoyou.sdk.vscenter.util.BitmapCache;
import com.vsoyou.sdk.vscenter.util.MetricUtil;
import com.vsoyou.sdk.vscenter.util.RegularUtil;

public class AskLayoutView extends BaseLayout {
	private Button btn_commit;
	private EditText edit_email;
	private EditText edit_phone;
	private EditText edit_quetion;

	public AskLayoutView(Context context, ParamChain env) {
		super(context, env);
		super.initUI(context);
	}

	@Override
	protected void initEnv(Context ctx, ParamChain env) {
   
	}

	@Override
	protected void onInitUI(Context ctx) {
		setTitleText("我要提问");
		FrameLayout sub = (FrameLayout) getView_subject(ctx);
		sub.setPadding(MetricUtil.getDip(ctx, 15), MetricUtil.getDip(ctx, 10),
				MetricUtil.getDip(ctx, 15), MetricUtil.getDip(ctx, 5));
        ScrollView sroll = new ScrollView(ctx);
		LinearLayout all = new LinearLayout(ctx);
		all.setOrientation(VERTICAL);
		sroll.addView(all);
		sub.addView(sroll,new LayoutParams(LP_MM));
		
		LinearLayout top_lin = new LinearLayout(ctx);
		top_lin.setOrientation(VERTICAL);
		top_lin.setBackgroundDrawable(BitmapCache.getNinePatchDrawable(ctx,
				"person_black.9.png"));
		all.addView(top_lin);

		LinearLayout quetion = new LinearLayout(ctx);
		quetion.setOrientation(HORIZONTAL);
		top_lin.addView(quetion);
		TextView txt_name = new TextView(ctx);
		txt_name.setText("问题内容:");
		txt_name.setTextColor(Color.parseColor("#4c4c4c"));
		txt_name.setPadding(MetricUtil.getDip(ctx, 5),
				MetricUtil.getDip(ctx, 5), MetricUtil.getDip(ctx, 5),
				MetricUtil.getDip(ctx, 5));
		txt_name.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		quetion.addView(txt_name);
        
		LayoutParams lp_edit = new LayoutParams(LP_MW);
		edit_quetion = new EditText(ctx);
		edit_quetion.setHint("请输入问题内容");
		edit_quetion.setTextColor(Color.BLACK);
		edit_quetion.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		edit_quetion.setImeOptions(EditorInfo.IME_ACTION_DONE);
		// edit_phone.setInputType(InputType.TYPE_CLASS_PHONE);
		edit_quetion.setBackgroundColor(Color.WHITE);
		edit_quetion.setPadding(MetricUtil.getDip(ctx, 6f),
				MetricUtil.getDip(ctx, 15f), 0, MetricUtil.getDip(ctx, 15f));
		quetion.addView(edit_quetion,lp_edit);

		addLine(ctx, top_lin); // 添加一条线

		LinearLayout email = new LinearLayout(ctx);
		email.setOrientation(HORIZONTAL);
		top_lin.addView(email);
		TextView txt_email = new TextView(ctx);
		txt_email.setText("邮箱地址:");
		txt_email.setTextColor(Color.parseColor("#4c4c4c"));
		txt_email.setPadding(MetricUtil.getDip(ctx, 5),
				MetricUtil.getDip(ctx, 5), MetricUtil.getDip(ctx, 5),
				MetricUtil.getDip(ctx, 5));
		txt_email.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		email.addView(txt_email);

		edit_email = new EditText(ctx);
		edit_email.setHint("请输入邮箱地址");
		edit_email.setTextColor(Color.BLACK);
		edit_email.setImeOptions(EditorInfo.IME_ACTION_DONE);
		edit_email.setBackgroundColor(Color.WHITE);
		edit_email.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		edit_email.setPadding(MetricUtil.getDip(ctx, 6f),
				MetricUtil.getDip(ctx, 15f), 0, MetricUtil.getDip(ctx, 15f));
		email.addView(edit_email,lp_edit);
		addLine(ctx, top_lin); // 添加一条线

		LinearLayout phone = new LinearLayout(ctx);
		phone.setOrientation(HORIZONTAL);
		top_lin.addView(phone);
		TextView txt_phone = new TextView(ctx);
		txt_phone.setText("手机号码:");
		txt_phone.setTextColor(Color.parseColor("#4c4c4c"));
		txt_phone.setPadding(MetricUtil.getDip(ctx, 5),
				MetricUtil.getDip(ctx, 5), MetricUtil.getDip(ctx, 5),
				MetricUtil.getDip(ctx, 5));
		txt_phone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		phone.addView(txt_phone);

		edit_phone = new EditText(ctx);
		edit_phone.setHint("请输入手机号码");
		edit_phone.setImeOptions(EditorInfo.IME_ACTION_DONE);
		edit_phone.setTextColor(Color.BLACK);
		edit_phone.setInputType(InputType.TYPE_CLASS_PHONE);
		edit_phone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		edit_phone.setBackgroundColor(Color.WHITE);
		edit_phone.setPadding(MetricUtil.getDip(ctx, 6f),
				MetricUtil.getDip(ctx, 15f), 0, MetricUtil.getDip(ctx, 15f));
		phone.addView(edit_phone,lp_edit);

		btn_commit = new Button(ctx);
		btn_commit.setOnClickListener(this);
		btn_commit.setText("提 交");
		btn_commit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		btn_commit.setBackgroundDrawable(BitmapCache.getDrawable(ctx,
				"btn_blue.png"));
		LayoutParams lp_commit = new LayoutParams(LP_WW);
		lp_commit.topMargin = MetricUtil.getDip(ctx, 20);
		lp_commit.gravity = Gravity.CENTER;
		all.addView(btn_commit, lp_commit);
	}

	private void addLine(Context context, LinearLayout linear) {
		View line = new View(context);
		line.setBackgroundColor(Color.rgb(235, 235, 235));
		LayoutParams lp_line = new LayoutParams(LP_MW);
		lp_line.height = 1;
		linear.addView(line, lp_line);
	}

	private String getEmail() {
		return edit_email.getText().toString();
	}

	private String getPhoneNumber() {
		return edit_phone.getText().toString();
	}

	private String getQuetion() {
		String str = edit_quetion.getText().toString();
		if (TextUtils.isEmpty(str)) {
			Toast.makeText(getContext(), "请输入问题的内容", Toast.LENGTH_SHORT).show();
		}
		return edit_quetion.getText().toString();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v == btn_commit) {
			if (RegularUtil.checkPhoneNumber(getPhoneNumber(), getContext())
					|| RegularUtil.checkEmail(getEmail(), getContext())
					&& !TextUtils.isEmpty(getQuetion())) {
				showDialog(getActivity());
             //提交问题的方法
			}

		}
	}

}
