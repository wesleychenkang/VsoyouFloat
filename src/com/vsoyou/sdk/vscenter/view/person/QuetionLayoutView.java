package com.vsoyou.sdk.vscenter.view.person;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vsoyou.sdk.vscenter.ParamChain;
import com.vsoyou.sdk.vscenter.util.BitmapCache;
import com.vsoyou.sdk.vscenter.util.MetricUtil;
import com.vsoyou.sdk.vscenter.view.person.ILayoutHost.KeyILayoutHost;

public class QuetionLayoutView extends BaseLayout {
	private TextView txt_all;
	private TextView txt_solved;
	private TextView txt_unsolved;
	private TextView txt_ask;
	private View all_line;
	private View unsolved_line;
	private View solved_line;
	private String[] str = { "今天我测试了一下，谢谢！", "最近有什么关于充值的优惠活动", "充值活动的 截止日期是",
			"最近新出的人物有哪些", "如何才能获取更多的战力", "如何才能更改我的昵称和更改绑定的手机号码" };

	public QuetionLayoutView(Context context, ParamChain env) {
		super(context, env);
		super.initUI(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onInitUI(Context ctx) {
		setTitleText("我的提问");
		FrameLayout sub = (FrameLayout) getView_subject(ctx);
		LinearLayout all = new LinearLayout(ctx);
		all.setOrientation(VERTICAL);
//		all.setPadding(MetricUtil.getDip(ctx, 5), MetricUtil.getDip(ctx, 0),
//				MetricUtil.getDip(ctx, 5), 0);
		sub.addView(all);

		LinearLayout top = new LinearLayout(ctx);
		top.setBackgroundColor(Color.parseColor("#fcfcfc"));
		all.addView(top);
		LayoutParams lp = new LayoutParams(LP_MW);
		lp.weight = 0.3f;
		LayoutParams lp_line = new LayoutParams(LP_MW);
		lp_line.height = MetricUtil.getDip(ctx, 3);

		{
			LinearLayout l_all = new LinearLayout(ctx);
			l_all.setOrientation(VERTICAL);
			top.addView(l_all, lp);

			txt_all = new TextView(ctx);
			txt_all.setOnClickListener(this);
			txt_all.setGravity(Gravity.CENTER);
			int count = 0;;
			String str = "全部( <font color='red'>"+count+"</font> )";
			txt_all.setText(Html.fromHtml(str));
			txt_all.setTextColor(Color.parseColor("#016ADE"));
			txt_all.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
			txt_all.setPadding(MetricUtil.getDip(ctx, 5),
					MetricUtil.getDip(ctx, 10), MetricUtil.getDip(ctx, 5),
					MetricUtil.getDip(ctx, 10));
			l_all.addView(txt_all, new LayoutParams(LP_MW));

			all_line = new View(ctx);
			all_line.setBackgroundColor(Color.rgb(36, 112, 209));
			all_line.setVisibility(VISIBLE);
			l_all.addView(all_line, lp_line);
		}

		{

			LinearLayout l_unsolved = new LinearLayout(ctx);
			l_unsolved.setOrientation(VERTICAL);
			top.addView(l_unsolved, lp);
			txt_unsolved = new TextView(ctx);
			txt_unsolved.setOnClickListener(this);
			txt_unsolved.setText("待解决(" + 0 + ")");
			txt_unsolved.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
			txt_unsolved.setTextColor(Color.parseColor("#4c4c4c"));
			txt_unsolved.setGravity(Gravity.CENTER);
			txt_unsolved.setPadding(MetricUtil.getDip(ctx, 5),
					MetricUtil.getDip(ctx, 10), MetricUtil.getDip(ctx, 5),
					MetricUtil.getDip(ctx, 10));
			l_unsolved.addView(txt_unsolved, new LayoutParams(LP_MW));

			unsolved_line = new View(ctx);
			unsolved_line.setVisibility(GONE);
			unsolved_line.setBackgroundColor(Color.rgb(36, 112, 209));

			l_unsolved.addView(unsolved_line, lp_line);
		}

		{
			LinearLayout l_solved = new LinearLayout(ctx);
			l_solved.setOrientation(VERTICAL);
			top.addView(l_solved, lp);

			txt_solved = new TextView(ctx);
			txt_solved.setText("已解决(" + 0 + ")");
			txt_solved.setTextColor(Color.parseColor("#4c4c4c"));
			txt_solved.setGravity(Gravity.CENTER);
			txt_solved.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
			txt_solved.setPadding(MetricUtil.getDip(ctx, 5),
					MetricUtil.getDip(ctx, 10), MetricUtil.getDip(ctx, 5),
					MetricUtil.getDip(ctx, 10));
			txt_solved.setOnClickListener(this);

			l_solved.addView(txt_solved, new LayoutParams(LP_MW));

			solved_line = new View(ctx);
			solved_line.setBackgroundColor(Color.rgb(36, 112, 209));
			solved_line.setVisibility(GONE);
			l_solved.addView(solved_line, lp_line);

		}
		txt_ask = new TextView(ctx);
		txt_ask.setGravity(Gravity.CENTER);
		txt_ask.setOnClickListener(this);
		txt_ask.setText("我要提问");
		txt_ask.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
//		txt_ask.setBackgroundDrawable(BitmapCache.getDrawable(ctx,
//				"get_token.png"));
		txt_ask.setTextColor(Color.parseColor("#4c4c4c"));
         
		// lp.leftMargin = MetricUtil.getDip(ctx, 5);
		LayoutParams lp_ask = new LayoutParams(LP_MW);
		lp_ask.weight = 0.3f;
		lp_ask.height = MetricUtil.getDip(ctx, 35);
		lp_ask.leftMargin = MetricUtil.getDip(ctx, 5);
		lp_ask.rightMargin = MetricUtil.getDip(ctx, 5);
		top.addView(txt_ask, lp_ask);

		View l = new View(ctx);
		l.setBackgroundDrawable(BitmapCache.getDrawable(ctx, "line.png"));
		LayoutParams lp_l = new LayoutParams(LP_MW);
		lp_l.height = 1;
		lp_l.topMargin = MetricUtil.getDip(ctx, 5);
		all.addView(l,lp_l);
		
		ExpandableListView list = new ExpandableListView(ctx);
		list.setChildIndicator(null);
		list.setGroupIndicator(null);
		Drawable b = BitmapCache.getDrawable(ctx, "line.png");
		list.setDivider(b);
		list.setDividerHeight(MetricUtil.getDip(getContext(), 1));
		LayoutParams lp_list = new LayoutParams(LP_MW);
		lp_list.topMargin = MetricUtil.getDip(ctx, 2);
		all.addView(list, lp_list);
		MyExpandAdapter dapter = new MyExpandAdapter();
		list.setAdapter(dapter);

	}

	/**
	 * 带展示的ListView
	 */
	private void createExpandListView() {

	}

	@Override
	protected void initEnv(Context ctx, ParamChain env) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v == txt_all) {
			undateTextViewColor(txt_all);
		} else if (v == txt_ask) {
			entryAsdkLayout();
			
		} else if (v == txt_solved) {
			undateTextViewColor(txt_solved);
		} else if (v == txt_unsolved) {
			undateTextViewColor(txt_unsolved);
		}
	}

	private void entryAsdkLayout() {
		ParamChain env = getEnv();
		ILayoutHost host = env.get(KeyILayoutHost.K_HOST, ILayoutHost.class);
		host.enter(env, ((Object) this).getClass().getClassLoader(),
				AskLayoutView.class.getName());
		
	}

	private void undateTextViewColor(TextView view) {

		if (view == txt_all) {

			txt_all.setTextColor(Color.parseColor("#016ADE"));
			txt_solved.setTextColor(Color.parseColor("#4c4c4c"));
			txt_unsolved.setTextColor(Color.parseColor("#4c4c4c"));
			all_line.setVisibility(VISIBLE);
			unsolved_line.setVisibility(GONE);
			solved_line.setVisibility(GONE);

		} else if (view == txt_solved) {

			txt_all.setTextColor(Color.parseColor("#4c4c4c"));
			txt_solved.setTextColor(Color.parseColor("#016ADE"));
			txt_unsolved.setTextColor(Color.parseColor("#4c4c4c"));
			all_line.setVisibility(GONE);
			unsolved_line.setVisibility(GONE);
			solved_line.setVisibility(VISIBLE);
		} else if (view == txt_unsolved) {

			txt_all.setTextColor(Color.parseColor("#4c4c4c"));
			txt_solved.setTextColor(Color.parseColor("#4c4c4c"));
			txt_unsolved.setTextColor(Color.parseColor("#016ADE"));
			all_line.setVisibility(GONE);
			unsolved_line.setVisibility(VISIBLE);
			solved_line.setVisibility(GONE);
		}
	}

	class MyExpandAdapter extends BaseExpandableListAdapter {

		@Override
		public Object getChild(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getChildId(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
				ViewGroup arg4) {
			FrameLayout frame = new FrameLayout(getContext());
			frame.setBackgroundDrawable(BitmapCache.getDrawable(getContext(), "reple.png"));
			LinearLayout ly = new LinearLayout(getContext());
			ly.setOrientation(VERTICAL);
			ly.setPadding(MetricUtil.getDip(getContext(), 5),
					MetricUtil.getDip(getContext(), 5),
					MetricUtil.getDip(getContext(), 5),
					MetricUtil.getDip(getContext(), 10));
			frame.addView(ly, new FrameLayout.LayoutParams(LP_WW));
			TextView txt = new TextView(getContext());
			txt.setText("回复详情:");
			txt.setPadding(0, MetricUtil.getDip(getContext(), 10), 0, 0);
			txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
			txt.setTextColor(Color.parseColor("#545556"));
			ly.addView(txt);
			
			TextView txt_content = new TextView(getContext());
			txt_content.setText("最近有很多活动,期待你们的 参与哇！");
			txt_content.setPadding(0, MetricUtil.getDip(getContext(), 10), 0, 0);
			txt_content.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
			txt_content.setTextColor(Color.parseColor("#545556"));
			ly.addView(txt_content);

			TextView status = new TextView(getContext());
			String str = "回复状况: <font color='red'>未回复</font>";
			status.setText(Html.fromHtml(str), TextView.BufferType.SPANNABLE);
			status.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
			status.setTextColor(Color.parseColor("#545556"));
			status.setPadding(0, MetricUtil.getDip(getContext(), 15),MetricUtil.getDip(getContext(), 5), 0);
			FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LP_WW);
			lp.gravity = Gravity.RIGHT | Gravity.TOP;
			frame.addView(status, lp);

			return frame;
		}

		@Override
		public int getChildrenCount(int arg0) {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public Object getGroup(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return str.length;
		}

		@Override
		public long getGroupId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getGroupView(int arg0, boolean arg1, View arg2,
				ViewGroup arg3) {
			FrameLayout frame = new FrameLayout(getContext());
			frame.setBackgroundColor(Color.WHITE);
			LinearLayout ly = new LinearLayout(getContext());
			ly.setOrientation(VERTICAL);
			frame.addView(ly, new FrameLayout.LayoutParams(LP_MW));
			TextView txt = new TextView(getContext());
			txt.setText(str[arg0]);
			txt.setPadding(MetricUtil.getDip(getContext(), 5), MetricUtil.getDip(getContext(), 5), MetricUtil.getDip(getContext(), 5), MetricUtil.getDip(getContext(), 5));
			txt.setTextColor(Color.parseColor("#545556"));
			txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
			ly.addView(txt);

			TextView txt_time = new TextView(getContext());
			txt_time.setText("提问时间" + "2015-05-07");
			txt_time.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
			txt_time.setTextColor(Color.parseColor("#545556"));
			txt_time.setPadding(MetricUtil.getDip(getContext(), 5), MetricUtil.getDip(getContext(), 2), MetricUtil.getDip(getContext(), 5), MetricUtil.getDip(getContext(), 2));
			ly.addView(txt_time);

			ImageView image = new ImageView(getContext());
			FrameLayout.LayoutParams lp_image = new FrameLayout.LayoutParams(
					LP_WW);
			lp_image.gravity = Gravity.RIGHT|Gravity.CENTER;
			lp_image.rightMargin = MetricUtil.getDip(getContext(), 10);
			if (arg1) {
				image.setBackgroundDrawable(BitmapCache.getDrawable(
						getContext(), "top_icon.png"));

			} else {

				image.setBackgroundDrawable(BitmapCache.getDrawable(
						getContext(), "bottom_icon.png"));
			}
			frame.addView(image, lp_image);

			return frame;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return false;
		}

	}

}
