package com.vsoyou.sdk.vscenter.view.person;
import java.lang.reflect.Constructor;
import android.content.Context;

import com.vsoyou.sdk.ParamChain;


public class LayoutFactory { 
	
	public static final int PERSON = 0;
	
	public static final int FORUM = 1;
	
	public static final int PHONE = 2;
	
	public static final int EMAIL = 3;
	
	public static final int PASSWORD = 4;
	
	public static final int QUESTION = 5;
	
	public static final int CUSTOMER = 6;
	
	public static final int RECODER = 7;
	/**
	 * ����ָ����Layout
	 * @param context
	 * @return
	 */
	public static ILayoutView createLayoutView(Context context,int viewType,ParamChain env){
		ILayoutView view = null;
		switch(viewType){
		case PERSON :
			view = new PersonLayoutView(context, env);
			break;
		case FORUM:
			break;
			
		case PHONE:
			view = new PhoneLayoutView(context, env);
			break;
		}
		return view;
	}
	
	/**
	 * ����ָ������
	 * 
	 * @param ctx
	 * @param className
	 *            ����
	 * @param classLoader
	 *            ����������Ϊnull��ʾʹ���������Ĭ���������
	 * @param rootEnv
	 * @return
	 */
	public  static ILayoutView createLayout(Context ctx, String className,
			ClassLoader classLoader, ParamChain rootEnv) {
		try {
			Class<?> lFactoryClass = Class
					.forName(className, true, classLoader);
			if (ILayoutView.class.isAssignableFrom(lFactoryClass)) {
				Constructor<?> c = lFactoryClass.getConstructor(Context.class,
						ParamChain.class);
				return (ILayoutView) c.newInstance(ctx, rootEnv);
				// return (ILayoutView) lFactoryClass.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
  
}
