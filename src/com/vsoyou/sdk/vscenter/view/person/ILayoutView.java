package com.vsoyou.sdk.vscenter.view.person;

import android.view.View;

import com.vsoyou.sdk.ParamChain;

public interface ILayoutView {
	/***
	 * ���룬��ʱ��������ʼ������
	 * 
	 * @return �Ƿ�ɹ���Ӧ
	 */
	public boolean onEnter();

	/**
	 * @return �Ƿ�ɹ���Ӧ��ͣ
	 */
	public boolean onPause();

	/**
	 * @return �Ƿ�ɹ���Ӧ�ָ�
	 */
	public boolean onResume();

	/**
	 * �Ƿ������ر�
	 * 
	 * @param isBack
	 *            true��ʾ�����أ� false��ʾ��Ҫ��ȫ�˳�
	 * @return true ��ʾ����رգ� false ��ʾ����ͣ�����������
	 */
	public boolean isExitEnabled(boolean isBack);

	/**
	 * @return �Ƿ�ɹ���Ӧ���ر�
	 */
	public boolean onExit();

	/**
	 * @return ��ȡ��������
	 */
	public ParamChain getEnv();

	/***
	 * �Ƿ���Ч
	 * 
	 * @return �Ƿ���Ч
	 */
	public boolean isAlive();

	/**
	 * ��ȡ����ͼ�����ڴ�����ʾ
	 * 
	 * @return ����ͼ
	 */
	public View getMainView();

}
