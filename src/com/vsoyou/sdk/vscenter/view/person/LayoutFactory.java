package com.vsoyou.sdk.vscenter.view.person;
import java.lang.reflect.Constructor;
import android.content.Context;

import com.vsoyou.sdk.ParamChain;


public class LayoutFactory { 
	
	/**
	 * 产生指定的Layout
	 * @param context
	 * @return
	 */
	public static ILayoutView createLayoutView(Context context){
		
		return null;
	}
	
	/**
	 * 构造指定类名
	 * 
	 * @param ctx
	 * @param className
	 *            类名
	 * @param classLoader
	 *            加载器，若为null表示使用虚拟机的默认类加载器
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
