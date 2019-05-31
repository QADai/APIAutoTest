package qadai.auto.apitest.common.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;

import qadai.auto.apitest.common.annotation.Server;
import qadai.auto.apitest.config.GlobalVars;

public class ProxyInterface {
	
	@SuppressWarnings("unchecked")
	public static <T> T create(Class<T> clazz){
		
		//获取接口上的服务器地址信息
		Annotation annotation = clazz.getAnnotation(Server.class);
		if (annotation == null){
			throw new RuntimeException(String.format("接口类%s没有配置@Server", clazz.getName()));
		}
		
		String host;
		switch(clazz.getAnnotation(Server.class).value()){
			case GlobalVars.TESTSERVER1:
				host = GlobalVars.TESTSERVER1_URL;
				break;
			case GlobalVars.TESTSERVER2:
				host = GlobalVars.TESTSERVER2_URL;
				break;
			case GlobalVars.TESTSERVER3:
				host = GlobalVars.TESTSERVER3_URL;
				break;
			case GlobalVars.TESTSERVER4:
				host = GlobalVars.TESTSERVER4_URL;
				break;
			default:
				throw new RuntimeException(String.format("找不到接口类%s服务器地址, 查看全局配置中是否配置", clazz.getName()));
		}
				
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new AnnotationInvocationHandler(host));
		
	}

}
