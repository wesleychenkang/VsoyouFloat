package com.vsyou.sdk.vscenter;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.vsyou.sdk.vscenter.view.FloatCenter;
import com.vsyou.sdk.vscenter.view.FloatCenterLeftView;
import com.vsyou.sdk.vscenter.view.FloatCenterRightView;

public class FloatWindowManager {
	private static FloatCenter center;
	private static FloatCenterLeftView centerLeft;
	private static WindowManager.LayoutParams centerParams;
	private static WindowManager.LayoutParams leftParams;
	private static WindowManager.LayoutParams rightParams;
	private static FloatCenterRightView centerRight;
	private static int x;
	private static int y;

	/**
	 * 创建浮窗中心页面
	 * 
	 * @param ctx
	 */
	public static void createCenterView(Context ctx) {
		WindowManager manager = getWindowManger(ctx);
		if (center == null) {
			center = new FloatCenter(ctx);
			DisplayMetrics dm = new DisplayMetrics();
			manager.getDefaultDisplay().getMetrics(dm);
			int screenWidth = dm.widthPixels;
			int screenHeight = dm.heightPixels;
			if(centerParams ==null){
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
		}
		manager.addView(center, centerParams);

	}

	public static void removeCenterView(Context ctx) {
		WindowManager manager = getWindowManger(ctx);
		if (center != null) {
			manager.removeView(center);
			center = null;
		}
	}

	/**
	 * 创建左边显示的页面
	 * 
	 * @param ctx
	 */
	public static void createCenterLeftView(Context ctx) {
		WindowManager manager = getWindowManger(ctx);
	//	if (centerLeft == null) {
			centerLeft = new FloatCenterLeftView(ctx);
			leftParams = new WindowManager.LayoutParams();
			leftParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
					| LayoutParams.FLAG_NOT_FOCUSABLE;
			leftParams.format = PixelFormat.RGBA_8888;
			leftParams.type = LayoutParams.TYPE_PHONE;
			leftParams.width = LayoutParams.WRAP_CONTENT;
			leftParams.height = LayoutParams.WRAP_CONTENT;
			leftParams.x = centerParams.x-80;
			leftParams.y = centerParams.y;
			
	//	}
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
			rightParams.x = centerParams.x+80;
			rightParams.y = centerParams.y;
			
		}
		centerRight.setMangerLayParams(rightParams);
		manager.addView(centerRight, rightParams);

	}
	
	
	public static void removeCenterLeftView(Context ctx) {
		WindowManager manager = getWindowManger(ctx);
		if (centerLeft != null) {
			manager.removeView(centerLeft);
			centerLeft = null;
		}
	}
   
	public static void removeCenterRightView(Context ctx) {
		WindowManager manager = getWindowManger(ctx);
		if (centerRight != null) {
			manager.removeView(centerRight);
			centerRight = null;
		}
	}
	// /**
	// * 创建浮动窗口页面
	// *
	// * @param ctx
	// */
	// public static void createCenterView(Context ctx) {
	//
	// }

	private static WindowManager getWindowManger(Context ctx) {

		return (WindowManager) ctx.getApplicationContext().getSystemService(
				Context.WINDOW_SERVICE);
	}

	public static WindowManager.LayoutParams getWindowLayoutParams() {
		return new WindowManager.LayoutParams();
	}

}
