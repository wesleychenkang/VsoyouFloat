package com.vsoyou.sdk.vscenter.view.person;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vsoyou.sdk.resources.ResFontSize;
import com.vsoyou.sdk.vscenter.util.MetricUtil;

public abstract class BaseLayout extends LinearLayout implements
		OnClickListener {
	/** 横向根据内容填充，竖向填满 */
	protected final static LayoutParams LP_WM = new LayoutParams(
			LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
	/** 横向填满，竖向填满 */
	protected final static LayoutParams LP_MM = new LayoutParams(
			LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	/** 横向根据内容填充，竖向根据内容填充 */
	protected final static LayoutParams LP_WW = new LayoutParams(
			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	/** 横向填满，竖向根据内容填充 */
	protected final static LayoutParams LP_MW = new LayoutParams(
			LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

	public BaseLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public BaseLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	protected abstract void onInitUI(Context ctx);

	protected void initUI(Context context) {
		setOrientation(VERTICAL);
		createView(context, this);
		onInitUI(context);
	}

	private void createView(Context context, LinearLayout rv) {
		FrameLayout title = new FrameLayout(context);

		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		rv.addView(title, lp);
		title.setBackgroundDrawable(null);
		TextView tv = new TextView(context);
		tv.setText("个人中心");
		tv.setGravity(Gravity.CENTER);
		tv.setSingleLine();
		tv.setTextSize(MetricUtil.getDip(context, ResFontSize.title));
		title.addView(tv, new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.CENTER));

		ImageView back = new ImageView(context);
		

	}
	

	@Override
	public void onClick(View v) {
		
		

	}

}
