package com.vsoyou.sdk.vscenter.view.person;
import java.lang.reflect.Constructor;
import android.content.Context;

import com.vsoyou.sdk.ParamChain;


public class LayoutFactory { 
	
	/**
	 * ����ָ����Layout
	 * @param context
	 * @return
	 */
	public static ILayoutView createLayoutView(Context context){
		
		return null;
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
