package com.vsoyou.sdk.vscenter.activity;

import java.util.HashMap;

import com.example.androidfloat.BuildConfig;
import com.vsoyou.sdk.vscenter.ParamChain;
/**
 * ������������������
 * 
 * @author wesley
 * @version v0.1.0.20150428
 */
public class ParamChainImpl implements ParamChain {
	/**ʵ����ȫ��һ������*/
	private static final ParamChain GLOBAL_INSTANCE = new ParamChainImpl();

	/**�ṩ���еķ�����ȫ�ֶ��󷵻س�ȥ**/
	static public ParamChain GLOBAL() {
		return GLOBAL_INSTANCE;
	}

	/** ���ر��� */
	private HashMap<String, Object> mData;
	/** ��ʱ���� */
	private HashMap<String, Object> mDataTmp;

	/** ��һ������ */
	private ParamChain mParent;

	/** ���� */
	private String mAliasName;

	/** �㼶 */
	private int mLevel;

	public ParamChainImpl() {
		this(null);
	}

	public ParamChainImpl(ParamChain base) {
		this(base, null);
	}

	public ParamChainImpl(ParamChain base, HashMap<String, Object> data) {
		mParent = base;
		mLevel = base != null ? (base.getLevel() + 1) : 0;

		if (data == null) {
			mData = new HashMap<String, Object>(8);
		} else {
			mData = new HashMap<String, Object>(data);
		}
		mDataTmp = new HashMap<String, Object>();
	}
	/**
	 * ���ز㼶��0 ��ʾ����
	 * 
	 * @return
	 */
	public int getLevel() {
		return mLevel;
	}

	/**
	 * ���ظ�������
	 * 
	 * @return
	 */
	public ParamChain getParent() {
		return mParent;
	}

	@Override
	public ParamChain getRoot() {
		ParamChain p = this;
		while (p.getParent() != null)
			p = p.getParent();
		return p;
	}

	/**
	 * ���(��ǰ��)
	 * 
	 * @param key
	 * @param val
	 * @return �Ƿ�ɹ�
	 */
	public boolean add(String key, Object val) {
		return add(key, val, ValType.NORMAL);
	}

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
	public boolean add(String key, Object val, ValType type) {
		if (BuildConfig.DEBUG) {
			if (val == null || key == null) {
				return false;
			}
		}
		if (type == ValType.TEMPORARY) {
			return mDataTmp.put(key, val) == val;
		} else {
			return mData.put(key, val) == val;
		}
	}

	/**
	 * ɾ��(��ǰ��)
	 * 
	 * @param key
	 * @return
	 */
	public Object remove(String key) {
		if (mData.containsKey(key)) {
			return mData.remove(key);
		}
		return mDataTmp.remove(key);
	}

	/**
	 * ���(��ǰ��)
	 */
	public void reset() {
		mData.clear();
		mDataTmp.clear();
	}

	/**
	 * ���(��ǰ��)��ʱ����
	 */
	public void autoRelease() {
		mDataTmp.clear();
	}

	/**
	 * ��ȡ(��ǰ��)
	 * 
	 * @param key
	 * @return
	 */
	public Object getOwned(String key) {
		if (mData.containsKey(key)) {
			return mData.get(key);
		}
		return mDataTmp.get(key);
	}

	/**
	 * �Ƿ����(��ǰ��)
	 * 
	 * @param key
	 * @return
	 */
	public ValType containsKeyOwn(String key) {
		if (mData.containsKey(key))
			return ValType.NORMAL;
		if (mDataTmp.containsKey(key))
			return ValType.TEMPORARY;
		return null;
	}


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
	@SuppressWarnings("unchecked")
	public <T> T getOwned(String key, Class<T> clazz) {
		Object ret = getOwned(key);
		if (ret != null && clazz.isInstance(ret)) {
			return (T) ret;
		}
		return null;
	}

	/**
	 * ��ȡ(���м�)
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		ParamChain p = this;
		do {
			Object ret = p.getOwned(key);
			if (ret != null) {
				return ret;
			}
			p = p.getParent();
		} while (p != null);
		return null;
	}

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
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> clazz) {
		Object ret = get(key);
		if (ret != null && clazz.isInstance(ret)) {
			return (T) ret;
		}
		return null;
	}

	@Override
	public ParamChain grow() {
		return new ParamChainImpl(this);
	}

	@Override
	public ParamChain grow(String aliasName) {
		ParamChain p = new ParamChainImpl(this);
		p.setAliasName(aliasName);
		return p;
	}

	@Override
	public ParamChain grow(HashMap<String, Object> data) {
		return new ParamChainImpl(this, data);
	}

	@Override
	public ParamChain getParent(String aliasName) {
		if (aliasName != null) {
			ParamChain p = this;
			do {
				if (aliasName.equals(p.getAliasName())) {
					return p;
				}
				p = p.getParent();
			} while (p != null);
		} else {
			ParamChain p = this;
			do {
				if (aliasName == p.getAliasName()) {
					return p;
				}
				p = p.getParent();
			} while (p != null);
		}
		return null;
	}

	@Override
	public boolean setAliasName(String aliasName) {
		if (mAliasName != null)
			return false;
		mAliasName = aliasName;
		return true;
	}

	@Override
	public String getAliasName() {
		return mAliasName;
	}

}