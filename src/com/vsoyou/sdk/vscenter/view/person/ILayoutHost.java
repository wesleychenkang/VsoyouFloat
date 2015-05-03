package com.vsoyou.sdk.vscenter.view.person;

import com.vsoyou.sdk.ParamChain;
import com.vsoyou.sdk.ParamChain.KeyGlobal;
import com.vsoyou.sdk.protocols.ActivityControlInterface;

public interface ILayoutHost {
	 public static class KeyILayoutHost implements KeyGlobal{
	    	public static final String Tag = KeyGlobal._TAG_+"key_layout_host"+_SEPARATOR_;
	    	public static final String K_HOST = Tag+"host";
	    }
	/**
	 *������һ����
	 */
	public void back();
	
	/**
	 * �˳�
	 */
	public void exit();
	
	/**
	 * ����ҳ��
	 */
	public void enter(ParamChain chain);
	
	
	public void addActivityControl(ActivityControlInterface control);
   
	public void  removeActivityControl(ActivityControlInterface control);
	

}
