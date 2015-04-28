package com.vsoyou.sdk.vscenter.view.person;

import com.vsoyou.sdk.ParamChain;
import com.vsoyou.sdk.ParamChain.KeyGlobal;

public interface ILayoutHost {
	 public static class KeyILayoutHost implements KeyGlobal{
	    	public static final String Tag = KeyGlobal._TAG_+"key_layout_host"+_SEPARATOR_;
	    	public static final String K_HOST = Tag+"host";
	    }
	/**
	 *返回上一界面
	 */
	public void back();
	
	/**
	 * 退出
	 */
	public void exit();
	
	/**
	 * 进入页面
	 */
	public void enter(ParamChain chain);
	
	
   
	
	

}
