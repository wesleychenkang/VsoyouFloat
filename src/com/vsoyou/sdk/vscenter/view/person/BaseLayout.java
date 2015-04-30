package com.vsoyou.sdk.vscenter.view.person;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vsoyou.sdk.vscenter.util.BitmapCache;
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

	private ImageView back;

	private ImageView exit;

	private FrameLayout frame_sub;

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
		
		title.setBackgroundDrawable(BitmapCache.getNinePatchDrawable(context, "top.9.png"));
		TextView tv = new TextView(context);
		tv.setText("个人中心");
		tv.setPadding(0, 10, 0, 10);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25f);
		//tv.setTextColor(Color.parseColor("#fe501b"));
		tv.setGravity(Gravity.CENTER);
		tv.setSingleLine();
		title.addView(tv, new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.CENTER));

		// 返回
		back = new ImageView(context);
		back.setBackgroundDrawable(BitmapCache.getDrawable(context, "back.png"));
		back.setOnClickListener(this);
		FrameLayout.LayoutParams lp_back = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.LEFT|Gravity.CENTER);
		lp_back.leftMargin =MetricUtil.getDip(context, 10) ;
		lp_back.height = MetricUtil.getDip(context, 10) ;
		lp_back.width = MetricUtil.getDip(context, 10) ;
		title.addView(back,lp_back );

		// 退出
		exit = new ImageView(context);
		exit.setBackgroundDrawable(BitmapCache.getDrawable(context, "exit.png"));
		exit.setOnClickListener(this);
		FrameLayout.LayoutParams lp_exit = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.RIGHT|Gravity.CENTER);
		lp_exit.rightMargin = MetricUtil.getDip(context, 10) ;
		lp_exit.height = MetricUtil.getDip(context, 10) ;
		lp_exit.width = MetricUtil.getDip(context, 10) ;
		title.addView(exit,lp_exit);

		{
			// 主体区域
			FrameLayout frame_main = new FrameLayout(context);
			frame_main.setBackgroundColor(Color.parseColor("#FFFFE0"));
			rv.addView(frame_main, new LayoutParams(LP_MM));
			View view = createViewSubject(context);
			frame_main
					.addView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
							LayoutParams.MATCH_PARENT, 1.0f));
			
		}

		// 下标题栏
		FrameLayout title_buttom = new FrameLayout(context);
		FrameLayout.LayoutParams lp_buttom = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT,Gravity.BOTTOM);
		lp_buttom.height= 50;
		title_buttom.setBackgroundColor(Color.RED);
		title_buttom.setBackgroundDrawable(BitmapCache.getNinePatchDrawable(context, "top.9.png"));
		rv.addView(title_buttom, lp_buttom);
	}

	public View createViewSubject(Context context) {
		frame_sub = new FrameLayout(context);
		return frame_sub;
	}

	protected View getView_subject(Context context) {

		return frame_sub;
	}

	@Override
	public void onClick(View v) {
		if (back == v) {
            Toast.makeText(getContext(), "返回", Toast.LENGTH_SHORT).show();
		} else if (exit == v) {

		}

	}

}
