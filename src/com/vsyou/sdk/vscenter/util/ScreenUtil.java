package com.vsyou.sdk.vscenter.util;

import android.content.Context;
import android.util.TypedValue;
import android.view.WindowManager;

public class ScreenUtil {
	/**
	 * 获取屏幕的宽度
	 * @return
	 */
   public static int getWith(Context ctx){
	   WindowManager windowManger = (WindowManager)ctx.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
	   return  windowManger.getDefaultDisplay().getWidth();
   }
   /**
	 * 获取屏幕的高度
	 * @return
	 */
   public static int getHeight(Context ctx){
	   WindowManager windowManger = (WindowManager)ctx.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
	   return windowManger.getDefaultDisplay().getHeight();
   }
   public static int getDip(Context context, float param) {
       return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, param, context.getResources().getDisplayMetrics());
   }
}
