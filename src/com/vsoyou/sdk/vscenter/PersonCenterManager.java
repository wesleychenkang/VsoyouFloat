package com.vsoyou.sdk.vscenter;

import android.content.Context;
import android.content.Intent;

import com.vsoyou.sdk.ParamChain;
import com.vsoyou.sdk.vscenter.activity.PersonCenterActivity;

public class PersonCenterManager {
	 private  ParamChain ev;
	 private  static PersonCenterManager personManager;
	 public static PersonCenterManager getInstance(){
		 if(personManager==null){
			 personManager = new PersonCenterManager();
		 }
		 return personManager;
		 
	 }
	 private  PersonCenterManager(){
		 ev = PersonCenterActivity.GET_GLOBAL_PARAM_CHAIN();
	 }
	/**
	 * 开启个人中心
	 * @param ctx
	 * @param chain
	 * @return
	 */
    private  boolean  startPersonCenter(Context ctx,ParamChain chain){
    	Intent intent = new Intent("com.vsoyou.sdk.activity.personcenter");
    	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	ctx.getApplicationContext().startActivity(intent);
    	FloatWindowManager.hideFloatView(ctx);
    	return true;
    }
    public  boolean startPersonCenter(Context ctx){
    	  startPersonCenter(ctx,null);
    	return true;
    }
}
