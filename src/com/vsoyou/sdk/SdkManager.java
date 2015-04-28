package com.vsoyou.sdk;

import com.vsoyou.sdk.vscenter.FloatService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class SdkManager {
  private SdkManager(){
	  
	  
  }
  private static SdkManager manager;
  public static SdkManager getSdkManagerInstance(Context ctx){
	  if(manager==null){
		 manager = new SdkManager();
	  }
	  return manager;
  }
  
  /**
   * 开启个人中心
   */
  public void startCenter(Activity activity){
	  Intent intent = new Intent(activity,FloatService.class);
	  activity.startService(intent);
  }
}
