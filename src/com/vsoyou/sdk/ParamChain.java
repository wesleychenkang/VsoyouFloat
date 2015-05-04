package com.vsoyou.sdk;

import java.util.HashMap;

/**
 * �����б�������������
 * 
 * @author kangzhi.chen
 * 
 */
public interface ParamChain {
	/**
	 * ȫ�ֱ�����
	 * <p>
	 * �Ӽ��������������
	 * 
	 * <pre>
	 * // ʾ��������������  KeyLayout����Ϊ layout������һ������ K_YOUR_NAME  
	 * public static interface <font color="#ff0000">KeyLayout</font> extends <b>KeyGlobal</b> {
	 *  	static final String _TAG_ = <b>KeyGlobal._TAG_</b> + "layout" + _SEPARATOR_;
	 *  
	 *  	<i>/** �����Զ�����, ���� {@link String}������˵�� *</i><i>/</i>
	 *  	public static final String K_YOUR_NAME = _TAG_ + "myName";
	 * }
	 * </pre>
	 * 
	 * @author kangzhi.chen
	 * 
	 */
	public static interface KeyGlobal {
		static final String _SEPARATOR_ = ".";
		static final String _TAG_ = "global" + _SEPARATOR_;
		/** ������ */
		static final String BASE_ACTIVITY = _TAG_ + "mainActivity";
		/** ҳ������ */
		static final String LAYOUT_TYPE = _TAG_ + "personLayout";
	    
		/**��������*/
		static final String KEY_UINAME =_TAG_+"ui_activity_name";
	
	}

	public static enum ValType {
		/** ��ͨ���� */
		NORMAL,
		/** ��ʱ�������ڵ��� {@link ParamChain#autoRelease()} ���������� */
		TEMPORARY;
	}

	/**
	 * �����һ���Ӽ���������
	 * 
	 * @return
	 */
	public ParamChain grow();

	/**
	 * �����һ���Ӽ���������
	 * 
	 * @param aliasName
	 *            ����
	 * @return
	 */
	public ParamChain grow(String aliasName);

	/**
	 * �����һ���Ӽ���������
	 * 
	 * @param data
	 *            ���ӵı�����
	 * @return
	 */
	public ParamChain grow(HashMap<String, Object> data);

	/**
	 * ���ز㼶��0 ��ʾ����
	 * 
	 * @return
	 */
	public int getLevel();

	/**
	 * ���ظ�������
	 * 
	 * @return
	 */
	public ParamChain getParent();

	/**
	 * ���ñ�������������һ��
	 * 
	 * @param aliasName
	 *            ����
	 * @return �Ƿ����óɹ�
	 */
	public boolean setAliasName(String aliasName);

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public String getAliasName();

	/**
	 * ���ݱ������ظ����������п��ܷ����Լ�
	 * 
	 * @param aliasName
	 *            ����
	 * @return
	 */
	public ParamChain getParent(String aliasName);

	/**
	 * ���ظ�������
	 * 
	 * @return
	 */
	public ParamChain getRoot();

	/**
	 * ���(��ǰ��)
	 * 
	 * @param key
	 * @param val
	 * @return �Ƿ�ɹ�
	 */
	public boolean add(String key, Object val);

	/**
	 * ���(��ǰ��)
	 * 
	 * @param key
	 *            ������
	 * @param val
	 *            ֵ
	 * @param type
	 *            ����
	 * @return �Ƿ�ɹ�
	 */
	public boolean add(String key, Object val, ValType type);

	/**
	 * ɾ��(��ǰ��)
	 * 
	 * @param key
	 * @return
	 */
	public Object remove(String key);

	/**
	 * ���(��ǰ��)
	 */
	public void reset();

	/**
	 * ���(��ǰ��)��ʱ����
	 */
	public void autoRelease();

	/**
	 * ��ȡ(��ǰ��)
	 * 
	 * @param key
	 * @return
	 */
	public Object getOwned(String key);

	/**
	 * ��ȡָ�����͵ı�������ǰ����
	 * 
	 * @param key
	 *            ������
	 * @param clazz
	 *            ������ֵ������
	 * @return ������������������ͷ��ϣ��򷵻�֮�����򷵻� null
	 * 
	 */
	public <T> T getOwned(String key, Class<T> clazz);

	/**
	 * ��ȡ(���м�)
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key);

	/**
	 * ��ȡָ�����͵ı��������м�����ʾ��
	 * <P>
	 * Double amount = env.get({@link KeyGlobal#K_PAY_AMOUNT}, {@link Double
	 * Double.class});
	 * 
	 * @param key
	 *            ������
	 * @param clazz
	 *            ������ֵ������
	 * @return ������������������ͷ��ϣ��򷵻�֮�����򷵻� null
	 * 
	 */
	public <T> T get(String key, Class<T> clazz);

}
