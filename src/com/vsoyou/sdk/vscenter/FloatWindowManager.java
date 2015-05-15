package com.vsoyou.sdk.vscenter;


import com.vsoyou.sdk.vscenter.service.FloatService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class FloatWindowManager {
  private FloatWindowManager(){
	  
	  
  }
  private static FloatWindowManager manager;
  public static FloatWindowManager getSdkManagerInstance(Context ctx){
	  if(manager==null){
		 manager = new FloatWindowManager();
	  }
	  return manager;
  }
  
  /**
   * 开起浮动窗口
   */
  public void startCenter(Activity activity){
	  Intent intent = new Intent(activity,FloatService.class);
	  activity.startService(intent);
  }
}
