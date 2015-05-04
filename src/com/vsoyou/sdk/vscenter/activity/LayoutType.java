package com.vsoyou.sdk.vscenter.activity;

import com.vsoyou.sdk.ParamChain.KeyGlobal;


class KeyLayoutName implements KeyGlobal{
	final static  String Tag = KeyGlobal._TAG_+"layout_name"+_SEPARATOR_;
}
/**
 * ��ͬ���͵Ĳ����ļ�
 * @author ckz
 *
 */
public enum LayoutType {
	/**��������ҳ��*/
	PERSON_CENTER,
	/**��̳ҳ��*/
	FORUM_CENTER,
	;
	
   public String  key(){
	   return KeyLayoutName.Tag+name();
   }
}
