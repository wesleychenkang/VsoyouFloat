package com.vsoyou.sdk.vscenter.view.person;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vsoyou.sdk.ParamChain;
import com.vsoyou.sdk.ParamChain.KeyGlobal;
import com.vsoyou.sdk.protocols.ActivityControlInterface;
import com.vsoyou.sdk.vscenter.view.person.ILayoutHost.KeyILayoutHost;

@SuppressLint("NewApi")
public abstract class Baset extends LinearLayout implements ILayoutView {
	public Baset(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 浼樺厛浜庡瓙绫荤殑鎴愬憳鍙橀噺鐨勫垵濮嬪寲
	 * 
	 * @param ctx
	 * @param env
	 */
	public abstract void initEnv(Context ctx, ParamChain env);

	protected abstract void onInitUI(Context ctx);

	protected static interface IIDC {
		/**
		 * ID鐨勮捣鐐�
		 */
		final static int _start_ = 0x01332C6E;

		/** 鑾峰彇ID (ordinal()+_start_) */
		public int id();
	}

	static enum IDC implements IIDC {
		/** popupWindow */
		ACT_POPUP, 
		TV_POPUP_WAIT_LABEL_SUMMARY,
		ACT_WAIT_PANEL, 
		/**閫�嚭鍏呭�涓績*/
		BT_EXIT,
		
		/**杩斿洖娓告垙*/
		BT_BACK,
		
		/***/
		SUBVIEW,
		/***/
		ADVERTISE
		,
		
		_MAX_;
		;

		@Override
		public int id() {

			return ordinal() + _start_;
		}
		public final static IDC fromID(int id){
			id -= _start_;
			if(id >=0 && id<_MAX_.ordinal()){
				return values()[id];
			}
			return _MAX_;
		}

	}

	/**
	 * 
	 * 娲诲姩鐘舵�
	 */
	static enum RUNSTATE {
		/** 鏈垵濮嬪寲 */
		UNINITED,
		/** 娲诲姩 */
		ACTIVE,
		/** 鏆傚仠 */
		PAUSED,
		/** 缁撴潫浜�*/
		FINISHED,

	}

	protected Context mContext;
	private ParamChain mEnv;
	private AsyncTask<?, ?, ?> mTask;
	private ArrayList<Dialog> mDialogList;
	private ActivityControlInterface mActivityControl;
	private RUNSTATE mRunState;
	private long mExitTriggerLastTime, mExitTriggerInterval;
	private String mExitTriggerTip;
	/** 妯悜鏍规嵁鍐呭濉厖锛岀珫鍚戝～婊�*/
	protected final static LayoutParams LP_WM = new LayoutParams(
			LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
	/** 妯悜濉弧锛岀珫鍚戝～婊�*/
	protected final static LayoutParams LP_MM = new LayoutParams(
			LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	/** 妯悜鏍规嵁鍐呭濉厖锛岀珫鍚戞牴鎹唴瀹瑰～鍏�*/
	protected final static LayoutParams LP_WW = new LayoutParams(
			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	/** 妯悜濉弧锛岀珫鍚戞牴鎹唴瀹瑰～鍏�*/
	protected final static LayoutParams LP_MW = new LayoutParams(
			LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	/** 榛樿闂撮殧锛�s */
	protected static final long DEFAULT_EXITTRIGGER_INTERVAL = 2 * 1000;

	protected static final long ANIMA_DUR_SHOW_POPUP = 300;
	protected static final long ANIMA_DUR_SHOW_POPUP_CHILD = 400;
	protected static final long ANIMA_DUR_HIDE_POPUP = 400;

	public Baset(Context context, ParamChain env) {
		super(context);
		mContext = context;
		mRunState = RUNSTATE.UNINITED;
		init(context, env);
		setBackgroundColor(Color.rgb(241, 241, 241));
	}

	private void init(Context contex, ParamChain env) {
		mEnv = env.grow(((Object) this).getClass().getName());
		mDialogList = new ArrayList<Dialog>();
		initEnv(contex, mEnv);
	}

	/**
	 * 璇ユ柟娉曠敱瀛愮被缁熶竴璋冪敤 骞跺寘鎷簡 onInitUI; 瀛愮被鐨勫垵濮嬪寲UI鍐欏湪 onInitUI鏂规硶閲岄潰
	 * 
	 * @param ctx
	 */
	protected void initAllUI(Context ctx) {
		setOrientation(VERTICAL);

		createView(ctx, this);
		onInitUI(ctx);

	}

	private void createView(Context ctx, LinearLayout rv) {
		FrameLayout title = new FrameLayout(ctx);
		LayoutParams lp = new LayoutParams(LP_MW);
		rv.addView(title, lp);
		title.setBackgroundDrawable(null);
		title.setPadding(0, 0, 0, 0);
		{
			// 澶撮儴鍖哄煙
			TextView tv = new TextView(ctx);
			tv.setSingleLine();
			tv.setGravity(Gravity.CENTER);
			title.addView(tv,new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,Gravity.CENTER));
		     
		   
		    
		    ImageButton iv  = new ImageButton(ctx);
		    title.addView(iv,new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,Gravity.LEFT|Gravity.CENTER_VERTICAL));
		    iv.setId(IDC.BT_EXIT.id());
		    iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		    iv.setClickable(true);
		    iv.setBackgroundDrawable(null);
		    iv.setPadding(20, 0, 0, 0);
		}
		
		// 瀹㈡埛鍖�
		FrameLayout fl = new FrameLayout(ctx);
		rv.addView(fl, new LayoutParams(LP_MM));
		{
			View view = createView_subject(ctx);
			fl.addView(view,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,1.0f));
		
		}
		{
			FrameLayout popup = new FrameLayout(ctx);
			fl.addView(popup, new FrameLayout.LayoutParams(LP_MM));
			popup.setId(IDC.ACT_POPUP.id());
			popup.setBackgroundColor(0xcc333333);
			popup.setVisibility(View.GONE);
			popup.setFocusable(true);
			popup.setFocusableInTouchMode(true);

		}
		// 骞垮憡鍖�
		{
			FrameLayout lay = new FrameLayout(ctx);
			lay.setId(IDC.ADVERTISE.id());
			lay.setPadding(2, 0, 2, 1);
		    fl.addView(lay, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,Gravity.BOTTOM) );
		}
		

	}

	@Override
	public boolean onEnter() {
		if (mRunState != RUNSTATE.UNINITED) {
			return false;
		}
		mRunState = RUNSTATE.ACTIVE;
		return true;
	}

	@Override
	public boolean onPause() {
		if (mRunState == RUNSTATE.ACTIVE) {
			mRunState = RUNSTATE.PAUSED;
		} else if (mRunState == RUNSTATE.PAUSED) {
			// nothing
		} else {

			return false;
		}
		disableActivityControlInterface();
		return true;
	}

	@Override
	public boolean onResume() {
		if (mRunState == RUNSTATE.ACTIVE) {

		} else if (mRunState == RUNSTATE.PAUSED) {
			mRunState = RUNSTATE.ACTIVE;
		} else {
			return false;
		}
		return true;
	}

	@Override
	public boolean isExitEnabled(boolean isBack) {
		if (!isBack && tryHidePopup()) {
			return false;
		}
		if (mExitTriggerInterval > 0) {
			long tick = SystemClock.uptimeMillis();
			if (tick > mExitTriggerLastTime + mExitTriggerInterval) {
				mExitTriggerLastTime = tick;
				return false;
			}
		}
		return true;
	}
	protected FrameLayout getSubjectView(){
		return (FrameLayout)findViewById(IDC.SUBVIEW.id());
	}
	
	protected FrameLayout getAdvertisView(){
		
		return (FrameLayout)findViewById(IDC.ADVERTISE.id());
	}
	protected View createView_subject(Context ctx){
		FrameLayout frame = new FrameLayout(ctx);
		int landscape = 8;
		int portait = 8;
		frame.setPadding(landscape, portait, landscape, portait);
		frame.setId(IDC.SUBVIEW.id());
		return frame;
	}

	protected boolean tryHidePopup() {
		View v = get_pop_view();
		if (v != null && v.getVisibility() == VISIBLE) {
			Object tag = v.getTag();
			if (tag instanceof Boolean && (Boolean) tag) {
				hide_popup(v);
				return true;
			} else {

			}
		} else {

		}
		return false;
	}

	@Override
	public boolean onExit() {
		if (mRunState == RUNSTATE.UNINITED) {

		} else if (mRunState == RUNSTATE.FINISHED) {
			return false;
		}
		mRunState = RUNSTATE.FINISHED;
		return true;
	}

	@Override
	public ParamChain getEnv() {
		// TODO Auto-generated method stub
		return mEnv;
	}

	protected void clean() {
		removeAllViews();
		cancelCurrentTask();
		removeActivityControlInterface();
		cleanDialogMonitor();
		if (null != mEnv) {
			mEnv.reset();
			mEnv = null;
		}
		mContext = null;

	}

	private void cleanDialogMonitor() {
		for (Dialog dialog : mDialogList) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}
		mDialogList.clear();

	}

	/** 鍙栨秷褰撳墠浠诲姟 */
	protected void cancelCurrentTask() {
		if (mTask != null) {
			if (!mTask.isCancelled())
				mTask.cancel(true);
			mTask = null;
		}
	}

	protected void removeActivityControlInterface() {
		if (mActivityControl != null) {
			disableActivityControlInterface();
			mActivityControl = null;
		}
	}

	private void disableActivityControlInterface() {
		if (null != mActivityControl) {
			ILayoutHost host = getHost();
		}

	}

	/**
	 * 璁剧疆鈥滈�鍑衡�閿侊紝闃叉鐢ㄦ埛鎰忓鎸変笅杩斿洖閿�
	 * 
	 * @param interval
	 *            鐢ㄦ埛鐨勫弽搴旈棿闅旓紝蹇呴』鍦ㄦ鏃堕棿鍐呰繛缁�鎷┾�閫�嚭鈥濈殑琛屼负锛屾敞鎰忥細闂撮殧涓嶈兘澶煭锛�1琛ㄧず浣跨敤榛樿鍊�
	 * @param tip
	 *            鎻愮ず璇紝琛ㄧず姝ｅ湪澶勭悊鐨勪簨浠讹紝濡傛灉涓虹┖锛屽垯浣跨敤榛樿鎻愮ず璇�
	 * @see {@link ZZStr#CC_EXIT_LOCKED_TIP}
	 */
	protected void setExitTrigger(long interval, String tip) {
		mExitTriggerLastTime = 0;
		mExitTriggerInterval = interval == -1 ? DEFAULT_EXITTRIGGER_INTERVAL
				: interval;
		mExitTriggerTip = tip;
	}

	protected void removeExitTrigger() {
		mExitTriggerLastTime = 0;
		mExitTriggerInterval = 0;
		mExitTriggerTip = null;
	}

	@Override
	public boolean isAlive() {
		return mRunState == RUNSTATE.ACTIVE || mRunState == RUNSTATE.PAUSED;
	}

	@Override
	public View getMainView() {
		// TODO Auto-generated method stub
		return this;
	}

	

	private View get_pop_view() {
		return findViewById(IDC.ACT_POPUP.id());
	}

	/**
	 * 鑾峰彇鎿嶄綔View鐨勫璞�
	 * 
	 * @return
	 */
	private ILayoutHost getHost() {
		if (null != mEnv) {
			return mEnv.get(KeyILayoutHost.K_HOST, ILayoutHost.class);
		}
		return null;
	}
    
	protected boolean callHost_back(){
		ILayoutHost host =  getHost();
		if(null!=host){
		host.back();
		return true;
		}
		return false;
	}
	
	protected boolean callHost_exit(){
		removeExitTrigger();
		ILayoutHost host =  getHost();
		if(null!=host){
			host.exit();
		    return true;
		}
		return false;
			
	}
	
	protected Activity getActivity(){
		return  getEnv().get(KeyGlobal.BASE_ACTIVITY, Activity.class);
		
	}
	/**
	 * 鏅�鐨勭瓑寰呰秴鏃跺洖璋冿紝鏅�绛夊緟8s,鍊掕鏃�0绉�
	 * 
	 * @author Administrator
	 * 
	 */
	protected static abstract class SimpleWaitTimeout implements IWaitTimeout {

		@Override
		public int getStart() {
			// TODO Auto-generated method stub
			return 8;
		}

		@Override
		public int getTimeout() {
			// TODO Auto-generated method stub
			return 30;
		}

		@Override
		public String getTickCountDesc(int timeGap) {
			// TODO Auto-generated method stub
			return String.format("> %02d <", timeGap);
		}

	}

	/** 绛夊緟 20 绉掑悗鍙栨秷 popup 閿侊紝浠ュ厤瀛愮晫闈㈠姞杞藉け璐ヨ�鐢ㄦ埛鏃犳硶鍙栨秷 popup 閬僵 */
	protected final IWaitTimeout DEFAULT_TIMEOUT_AUTO_UNLOCK = new IWaitTimeout() {

		@Override
		public void onTimeOut() {
			showPopup_EnableAutoClose(true);
		}

		@Override
		public int getTimeout() {
			return 0;
		}

		@Override
		public String getTickCountDesc(int timeGap) {
			return "";
		}

		@Override
		public int getStart() {
			return 20;
		}
	};

	protected static interface IWaitTimeout {
		/** 鍊掕鏃惰Е鍙戞椂闂寸偣锛屽崟浣峓绉抅 */
		int getStart();

		/** 鍊掕鏃舵椂闀匡紝鍗曚綅[绉抅 */
		int getTimeout();

		/** 鍊掕鏃舵枃鏈弿杩帮紝濡�鍓╀綑[%d]绉� */
		String getTickCountDesc(int timeGap);

		/** 宸茶秴鏃�*/
		void onTimeOut();
	}

	/** 寮瑰嚭绛夊緟杩涘害锛屾寮瑰嚭瑙嗗浘鍙兘涓诲姩鍏抽棴锛屼笉鍙�杩囧崟鍑诲叧闂紝鍏舵枃鏈爣绛綢D涓�{@link IDC#TV_POPUP_WAIT_LABEL} */
	protected void showPopup_Wait() {
		showPopup_Wait(null, null);
	}

	protected void showPopup_EnableAutoClose(boolean autoClose) {
		show_popup_enable_auto_close(get_pop_view(), autoClose);
	}

	protected void showPopup_Wait(CharSequence tip, IWaitTimeout timeoutCallback) {
	}

	protected void set_child_visibility(int visiable,IIDC id){
		View view = findViewById(id.id());
		view.setVisibility(visiable);
		
	}
	

	protected void hidePopup() {
		View popupView = get_pop_view();
		hide_popup(popupView);
	}

	protected void hide_popup(View popupView) {
		if (popupView != null && popupView.getVisibility() != GONE) {
			AnimationSet out = new AnimationSet(true);
			out.setDuration(ANIMA_DUR_HIDE_POPUP);
			out.addAnimation(new AlphaAnimation(1f, 0f));
			out.setFillBefore(true);
			popupView.startAnimation(out);
			popupView.setVisibility(GONE);
			if (popupView instanceof ViewGroup) {
				((ViewGroup) popupView).removeAllViews();
			}
		}
	}

	protected void show_popup(View vPopup, boolean auto_close, View vChild) {
		if (vPopup != null) {
			if (vPopup.getVisibility() != VISIBLE) {
				AnimationSet in = new AnimationSet(true);
				in.setDuration(ANIMA_DUR_SHOW_POPUP);
				in.addAnimation(new AlphaAnimation(0.2f, 1f));
				in.setFillBefore(true);
				vPopup.setVisibility(VISIBLE);
				vPopup.startAnimation(in);
				vPopup.requestFocus();
			}

			if (vChild != null && (vPopup instanceof FrameLayout)) {
				FrameLayout rv = (FrameLayout) vPopup;
				rv.removeAllViews();
				if (vChild.getLayoutParams() == null) {
					vChild.setLayoutParams(new FrameLayout.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT, Gravity.CENTER));
				}
				if (vChild.getAnimation() == null) {
					AnimationSet in = new AnimationSet(true);
					in.setDuration(ANIMA_DUR_SHOW_POPUP_CHILD);
					in.addAnimation(new AlphaAnimation(0.2f, 1f));
					in.setFillBefore(true);
					vChild.setAnimation(in);
				}
				rv.addView(vChild);
				vChild.getAnimation().start();
			}

			show_popup_enable_auto_close(vPopup, auto_close);
		}

	}

	protected void show_popup_enable_auto_close(View v, boolean autoClose) {
		if (v != null) {
			v.setTag(autoClose ? Boolean.TRUE : null);
		}
	}


	protected void showToast(String str) {
		Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
	}

	protected void set_child_visibility(IIDC id, int visibility) {
		View v = findViewById(id.id());
		if (null != v) {
			v.setVisibility(visibility);
		}
	}

	protected void set_child_text(IIDC id, CharSequence str) {
		View v = findViewById(id.id());
		if (null != v) {
			if (v instanceof TextView) {
				((TextView) v).setText(str);
			}
		}
	}

	protected String get_child_text(IIDC id) {
		View v = findViewById(id.id());
		if (v instanceof TextView) {
			CharSequence s = ((TextView) v).getText();
			return s.toString().trim();
		}
		return null;

	}
	protected void showResultSucessForMessage(){
	}

	}

