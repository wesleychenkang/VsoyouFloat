package com.vsoyou.sdk.vscenter.view.person;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vsoyou.sdk.ParamChain;
import com.vsoyou.sdk.vscenter.util.BitmapCache;
import com.vsoyou.sdk.vscenter.util.MetricUtil;

public class QuetionLayoutView extends BaseLayout {
	private TextView txt_all;
	private TextView txt_solved;
	private TextView txt_unsolved;
	private TextView txt_ask;
	private View all_line;
	private View unsolved_line;
	private View solved_line;

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
		all.setPadding(MetricUtil.getDip(ctx, 5), MetricUtil.getDip(ctx, 15),
				MetricUtil.getDip(ctx, 5), 0);
		sub.addView(all);

		LinearLayout top = new LinearLayout(ctx);
		all.addView(top);
		LayoutParams lp = new LayoutParams(LP_MW);
		lp.weight = 1f;
		LayoutParams lp_line = new LayoutParams(LP_MW);
		lp_line.height = MetricUtil.getDip(ctx, 2);

		{
			LinearLayout l_all = new LinearLayout(ctx);
			l_all.setOrientation(VERTICAL);
			top.addView(l_all, lp);

			txt_all = new TextView(ctx);
			txt_all.setOnClickListener(this);
			txt_all.setGravity(Gravity.CENTER);
			txt_all.setText("全部(" + 0 + ")");
			txt_all.setTextColor(Color.parseColor("#2471d1"));
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
		txt_ask.setBackgroundDrawable(BitmapCache.getNinePatchDrawable(ctx,
				"blue_block.9.png"));
		txt_ask.setGravity(Gravity.CENTER);
		txt_ask.setOnClickListener(this);
		txt_ask.setText("我要提问");
		txt_ask.setTextColor(Color.rgb(21, 108, 211));
		txt_ask.setPadding(MetricUtil.getDip(ctx, 10),
				MetricUtil.getDip(ctx, 5), MetricUtil.getDip(ctx, 10),
				MetricUtil.getDip(ctx, 5));
		lp.leftMargin = MetricUtil.getDip(ctx, 5);
		top.addView(txt_ask, lp);

		ExpandableListView list = new ExpandableListView(ctx);
		list.setChildIndicator(null);
		list.setGroupIndicator(null);
		all.addView(list);
		MyExpandAdapter dapter = 	new MyExpandAdapter();
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
		} else if (v == txt_solved) {
			undateTextViewColor(txt_solved);
		} else if (v == txt_unsolved) {
			undateTextViewColor(txt_unsolved);
		}
	}

	private void undateTextViewColor(TextView view) {

		if (view == txt_all) {

			txt_all.setTextColor(Color.parseColor("#2471d1"));
			txt_solved.setTextColor(Color.parseColor("#4c4c4c"));
			txt_unsolved.setTextColor(Color.parseColor("#4c4c4c"));
			all_line.setVisibility(VISIBLE);
			unsolved_line.setVisibility(GONE);
			solved_line.setVisibility(GONE);

		} else if (view == txt_solved) {

			txt_all.setTextColor(Color.parseColor("#4c4c4c"));
			txt_solved.setTextColor(Color.parseColor("#2471d1"));
			txt_unsolved.setTextColor(Color.parseColor("#4c4c4c"));
			all_line.setVisibility(GONE);
			unsolved_line.setVisibility(GONE);
			solved_line.setVisibility(VISIBLE);
		} else if (view == txt_unsolved) {

			txt_all.setTextColor(Color.parseColor("#4c4c4c"));
			txt_solved.setTextColor(Color.parseColor("#4c4c4c"));
			txt_unsolved.setTextColor(Color.parseColor("#2471d1"));
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
			frame.setBackgroundColor(Color.rgb(235, 235, 235));
			LinearLayout ly = new LinearLayout(getContext());
			frame.addView(ly,new FrameLayout.LayoutParams(LP_MW));
			TextView txt = new TextView(getContext());
			txt.setText("提问详情");
			ly.addView(txt);
			
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
			return 10;
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
			frame.setBackgroundColor(Color.rgb(235, 235, 235));
			LinearLayout ly = new LinearLayout(getContext());
			frame.addView(ly,new FrameLayout.LayoutParams(LP_MW));
			TextView txt = new TextView(getContext());
			txt.setText("我的提问");
			ly.addView(txt);
			
			
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
