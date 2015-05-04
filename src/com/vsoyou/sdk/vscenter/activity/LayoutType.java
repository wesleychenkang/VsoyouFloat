package com.vsoyou.sdk.vscenter.activity;

import com.vsoyou.sdk.ParamChain.KeyGlobal;


class KeyLayoutName implements KeyGlobal{
	final static  String Tag = KeyGlobal._TAG_+"layout_name"+_SEPARATOR_;
}
/**
 * 不同类型的布局文件
 * @author ckz
 *
 */
public enum LayoutType {
	/**个人中心页面*/
	PERSON_CENTER,
	/**论坛页面*/
	FORUM_CENTER,
	;
	
   public String  key(){
	   return KeyLayoutName.Tag+name();
   }
}
