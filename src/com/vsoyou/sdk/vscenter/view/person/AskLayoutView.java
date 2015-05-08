package com.vsoyou.sdk.vscenter.view.person;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.vsoyou.sdk.ParamChain;
import com.vsoyou.sdk.vscenter.util.BitmapCache;
import com.vsoyou.sdk.vscenter.util.MetricUtil;

public class AskLayoutView extends BaseLayout {
	private Button btn_commit;

	public AskLayoutView(Context context, ParamChain env) {
		super(context, env);
		super.initUI(context);
	}

	@Override
	protected void initEnv(Context ctx, ParamChain env) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onInitUI(Context ctx) {
		setTitleText("我要提问");
		FrameLayout sub = (FrameLayout) getView_subject(ctx);
		sub.setPadding(MetricUtil.getDip(ctx, 15), MetricUtil.getDip(ctx,10),
				MetricUtil.getDip(ctx, 15), MetricUtil.getDip(ctx, 5));

		LinearLayout all = new LinearLayout(ctx);
		all.setOrientation(VERTICAL);
		sub.addView(all, new LayoutParams(LP_MW));

		LinearLayout top_lin = new LinearLayout(ctx);
		top_lin.setOrientation(VERTICAL);
		top_lin.setBackgroundDrawable(BitmapCache.getNinePatchDrawable(ctx,
				"black_white.9.png"));
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
		txt_name.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
		quetion.addView(txt_name);
       
		EditText edit_quetion = new EditText(ctx);
		edit_quetion.setHint("请输入问题内容");
		edit_quetion.setTextColor(Color.parseColor("#cbcdcb"));
		edit_quetion.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
		edit_quetion.setImeOptions(EditorInfo.IME_ACTION_DONE);
		// edit_phone.setInputType(InputType.TYPE_CLASS_PHONE);
		edit_quetion.setBackgroundColor(Color.TRANSPARENT);
		edit_quetion.setPadding(MetricUtil.getDip(ctx, 6),
				MetricUtil.getDip(ctx, 10), 0, MetricUtil.getDip(ctx, 10));
		quetion.addView(edit_quetion);
         
		addLine(ctx, top_lin); //添加一条线
		
		
		LinearLayout email = new LinearLayout(ctx);
		email.setOrientation(HORIZONTAL);
		top_lin.addView(email);
		TextView txt_email = new TextView(ctx);
		txt_email.setText("邮箱地址:");
		txt_email.setTextColor(Color.parseColor("#4c4c4c"));
		txt_email.setPadding(MetricUtil.getDip(ctx, 5),
				MetricUtil.getDip(ctx, 5), MetricUtil.getDip(ctx, 5),
				MetricUtil.getDip(ctx, 5));
		txt_email.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
		email.addView(txt_email);

		EditText edit_email = new EditText(ctx);
		edit_email.setHint("请输入邮箱地址");
		edit_email.setTextColor(Color.parseColor("#cbcdcb"));
		edit_email.setImeOptions(EditorInfo.IME_ACTION_DONE);
		edit_email.setBackgroundColor(Color.TRANSPARENT);
		edit_email.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
		edit_email.setPadding(MetricUtil.getDip(ctx, 6),
				MetricUtil.getDip(ctx, 10), 0, MetricUtil.getDip(ctx, 10));
		email.addView(edit_email);
		addLine(ctx, top_lin); //添加一条线
		
		
		LinearLayout phone = new LinearLayout(ctx);
		phone.setOrientation(HORIZONTAL);
		top_lin.addView(phone);
		TextView txt_phone = new TextView(ctx);
		txt_phone.setText("手机号码:");
		txt_phone.setTextColor(Color.parseColor("#4c4c4c"));
		txt_phone.setPadding(MetricUtil.getDip(ctx, 5),
				MetricUtil.getDip(ctx, 5), MetricUtil.getDip(ctx, 5),
				MetricUtil.getDip(ctx, 5));
		txt_phone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
		phone.addView(txt_phone);

		EditText edit_phone = new EditText(ctx);
		edit_phone.setHint("请输入手机号码");
		edit_phone.setImeOptions(EditorInfo.IME_ACTION_DONE);
		edit_phone.setInputType(InputType.TYPE_CLASS_PHONE);
		edit_phone.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
		edit_phone.setBackgroundColor(Color.TRANSPARENT);
		edit_phone.setPadding(MetricUtil.getDip(ctx, 6),
				MetricUtil.getDip(ctx, 10), 0, MetricUtil.getDip(ctx, 10));
		phone.addView(edit_phone);

		btn_commit = new Button(ctx);
		btn_commit.setOnClickListener(this);
		btn_commit.setText("提交");
		btn_commit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		btn_commit.setBackgroundDrawable(BitmapCache.getNinePatchDrawable(ctx,
				"btn_blue.9.png"));
		btn_commit.setPadding(MetricUtil.getDip(ctx, 10),
				MetricUtil.getDip(ctx, 6), MetricUtil.getDip(ctx, 10),
				MetricUtil.getDip(ctx, 6));
		LayoutParams lp_commit = new LayoutParams(LP_MW);
		lp_commit.topMargin = MetricUtil.getDip(ctx, 20);
		all.addView(btn_commit, lp_commit);
	}
    private void addLine(Context context,LinearLayout linear){
    	View line = new View(context);
		line.setBackgroundColor(Color.rgb(235, 235, 235));
		LayoutParams lp_line = new LayoutParams(LP_MW);
		lp_line.height = 3;
		linear.addView(line,lp_line);
    }
	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v == btn_commit) {
			showDialog(getActivity());

		}

	}

}
