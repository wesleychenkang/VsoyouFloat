package com.vsyou.sdk.vscenter.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidfloat.R;
import com.vsyou.sdk.vscenter.person.PersonCenter;

public class FloatCenterPresonView extends LinearLayout {

	public FloatCenterPresonView(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context context) {
		setVisibility(View.VISIBLE);
		setBackgroundColor(Color.TRANSPARENT);
		setOrientation(VERTICAL);
		FrameLayout liner = new FrameLayout(context);
		TextView txt = new TextView(context);
		txt.setText("个人中心");
		liner.addView(txt, new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.CENTER));

		ImageView img_close = new ImageView(context);
		img_close.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.ic_launcher));
		liner.addView(img_close, new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				Gravity.RIGHT));
		View line = new View(getContext());
		line.setBackgroundColor(Color.RED);
		FrameLayout.LayoutParams line_prams = new FrameLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
				Gravity.BOTTOM);
		line_prams.height = 1;
		liner.addView(line, line_prams);
		
		addView(liner);

		ListView list_view = new ListView(context);
		setPadding(100, 0, 100, 0);
		addView(list_view, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		list_view.setAdapter(new PersonCenterAdapter());

	}

	class PersonCenterAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return PersonCenter.str.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return PersonCenter.str[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			FrameLayout frame = new FrameLayout(getContext());
			// frame.setBackgroundColor(Color.RED);
			LinearLayout all = new LinearLayout(getContext());
			frame.addView(all, new FrameLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

			LinearLayout ly = new LinearLayout(getContext());
			ly.setGravity(Gravity.LEFT);
			ly.setOrientation(LinearLayout.HORIZONTAL);
			ImageView image = new ImageView(getContext());
			image.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.ic_launcher));
			ly.addView(image);
			TextView txt_name = new TextView(getContext());
			txt_name.setText(PersonCenter.str[position]);
			txt_name.setGravity(Gravity.CENTER);
			ly.addView(txt_name, new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.MATCH_PARENT));

			all.addView(ly, new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT, 0.6f));

			LinearLayout ltxt = new LinearLayout(getContext());
			ltxt.setGravity(Gravity.RIGHT);
			ltxt.setOrientation(HORIZONTAL);
			TextView txt = new TextView(getContext());
			txt.setText("未绑定");
			txt.setGravity(Gravity.CENTER);
			LayoutParams lp_txt = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
			ltxt.addView(txt,lp_txt);

			ImageView image_right = new ImageView(getContext());
			image_right.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.ic_launcher));
			ltxt.addView(image_right);

			all.addView(ltxt, new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT, 0.4f));

			return frame;
		}

	}

}
