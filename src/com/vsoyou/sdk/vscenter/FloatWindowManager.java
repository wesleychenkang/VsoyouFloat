package com.vsoyou.sdk.vscenter;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.vsoyou.sdk.vscenter.view.FloatCenter;
import com.vsoyou.sdk.vscenter.view.FloatCenterLeftView;
import com.vsoyou.sdk.vscenter.view.FloatCenterPresonView;
import com.vsoyou.sdk.vscenter.view.FloatCenterRightView;

public class FloatWindowManager {
	private static FloatCenter center;
	private static FloatCenterLeftView centerLeft;
	private static WindowManager.LayoutParams centerParams;
	private static WindowManager.LayoutParams leftParams;
	private static WindowManager.LayoutParams rightParams;
	private static FloatCenterRightView centerRight;
	private static FloatCenterPresonView centerPerson;
	private static WindowManager.LayoutParams personParams;
	private static int x;
	private static int y;

	/**
	 * 创建浮窗中心页面
	 * 
	 * @param ctx
	 */
	public static void createCenterView(Context ctx) {
		WindowManager manager = getWindowManger(ctx);
		// if (center == null) {
		center = new FloatCenter(ctx);
		DisplayMetrics dm = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		if (centerParams == null) {
			centerParams = new WindowManager.LayoutParams();
			centerParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
					| LayoutParams.FLAG_NOT_FOCUSABLE;
			centerParams.format = PixelFormat.RGBA_8888;
			centerParams.type = LayoutParams.TYPE_PHONE;
			centerParams.width = LayoutParams.WRAP_CONTENT;
			centerParams.height = LayoutParams.WRAP_CONTENT;

		}

		if (centerParams.x == 0 && centerParams.y == 0) {
			centerParams.x = screenWidth / 2;
			centerParams.y = -screenHeight / 2;
		} else {

			x = centerParams.x;
			y = centerParams.y;
		}
		center.setMangerLayParams(centerParams);
		// }
		createCenterRightView(ctx);
		createCenterLeftView(ctx);
		//createCenterPersonView(ctx);
		manager.addView(center, centerParams);
	}

	// public static void removeCenterView(Context ctx) {
	// WindowManager manager = getWindowManger(ctx);
	// if (center != null) {
	// manager.removeView(center);
	// center = null;
	// }
	// }

	/**
	 * 创建左边显示的页面
	 * 
	 * @param ctx
	 */
	public static void createCenterLeftView(Context ctx) {
		WindowManager manager = getWindowManger(ctx);
		// if (centerLeft == null) {
		centerLeft = new FloatCenterLeftView(ctx);
		leftParams = new WindowManager.LayoutParams();
		leftParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
				| LayoutParams.FLAG_NOT_FOCUSABLE;
		leftParams.format = PixelFormat.RGBA_8888;
		leftParams.type = LayoutParams.TYPE_PHONE;
		leftParams.width = LayoutParams.WRAP_CONTENT;
		leftParams.height = LayoutParams.WRAP_CONTENT;
		leftParams.x = centerParams.x - 80;
		leftParams.y = centerParams.y;

		// }
		centerLeft.setMangerLayParams(leftParams);
		manager.addView(centerLeft, leftParams);

	}

	/**
	 * 创建右边显示的页面
	 * 
	 * @param ctx
	 */
	public static void createCenterRightView(Context ctx) {
		WindowManager manager = getWindowManger(ctx);
		if (centerRight == null) {
			centerRight = new FloatCenterRightView(ctx);
			rightParams = new WindowManager.LayoutParams();
			rightParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
					| LayoutParams.FLAG_NOT_FOCUSABLE;
			rightParams.format = PixelFormat.RGBA_8888;
			rightParams.type = LayoutParams.TYPE_PHONE;
			rightParams.width = LayoutParams.WRAP_CONTENT;
			rightParams.height = LayoutParams.WRAP_CONTENT;
			rightParams.x = centerParams.x;
			rightParams.y = centerParams.y;

		}
		centerRight.setMangerLayParams(rightParams);
		manager.addView(centerRight, rightParams);

	}
    
	public static void createCenterPersonView(Context ctx){
		WindowManager manager = getWindowManger(ctx);
		DisplayMetrics dm = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		if (centerPerson == null) {
			centerPerson = new FloatCenterPresonView(ctx);
			personParams = new WindowManager.LayoutParams();
			personParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
					| LayoutParams.FLAG_NOT_FOCUSABLE;
			personParams.format = PixelFormat.RGBA_8888;
			personParams.type = LayoutParams.TYPE_PHONE;
			personParams.width = LayoutParams.WRAP_CONTENT;
			personParams.height = LayoutParams.WRAP_CONTENT;
			personParams.x = 0 ;
			personParams.y = screenHeight/2;
			
			
			
			//personParams.gravity = Gravity.BOTTOM| Gravity.CENTER;
		}
		//centerPerson.setMangerLayParams(rightParams);
		manager.addView(centerPerson, personParams);
		
	}
	
	public static void disPlayRightView(Context ctx) {
		WindowManager manager = getWindowManger(ctx);
		centerLeft.setVisibility(View.GONE);
		centerRight.setVisibility(View.VISIBLE);
		center.setVisibility(View.GONE);
		centerParams.x = centerParams.x + 80;
		manager.updateViewLayout(centerRight, centerParams);

	}

	public static void disPlayLeftView(Context ctx) {
		WindowManager manager = getWindowManger(ctx);
		centerRight.setVisibility(View.GONE);
		centerLeft.setVisibility(View.VISIBLE);
		center.setVisibility(View.GONE);
		centerParams.x = centerParams.x - 80;
		manager.updateViewLayout(centerLeft, centerParams);

	}

	/**
	 * 
	 * @param ctx
	 * @param place
	 *            1，代表左边 ， 0 代表右边,其它值表示只显示centerView
	 */
	public static void disPlayCenterView(Context ctx, int place) {

		WindowManager manager = getWindowManger(ctx);
		centerRight.setVisibility(View.GONE);
		centerLeft.setVisibility(View.GONE);
//		centerPerson.setVisibility(View.GONE);
		center.setVisibility(View.VISIBLE);
		if (place == 0) {
			centerParams.x = centerParams.x - 80;
		} else if(place ==1) {
			centerParams.x = centerParams.x + 80;

		}else{
			
			
		}
		manager.updateViewLayout(center, centerParams);

	}
    
	/**
	 * 隐藏浮窗
	 * @param ctx
	 */
    public static void hideFloatView(Context ctx){
    	center.setVisibility(View.GONE);
    	centerLeft.setVisibility(View.GONE);
    	centerRight.setVisibility(View.GONE);
    }
    
   
	public static void disPlayPersonView(Context ctx){
		if(centerRight != null){
		centerRight.setVisibility(View.GONE);
		}
		if(centerLeft!=null){
		centerLeft.setVisibility(View.GONE);
		}
		if(center!=null){
		center.setVisibility(View.GONE);
		}
		centerPerson.setVisibility(View.VISIBLE);
	}
	private static WindowManager getWindowManger(Context ctx) {

		return (WindowManager) ctx.getApplicationContext().getSystemService(
				Context.WINDOW_SERVICE);
	}

	public static WindowManager.LayoutParams getWindowLayoutParams() {
		return new WindowManager.LayoutParams();
	}

}
