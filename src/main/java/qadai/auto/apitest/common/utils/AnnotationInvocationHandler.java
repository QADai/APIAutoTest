package qadai.auto.apitest.common.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;

import com.aventstack.extentreports.model.Log;

import qadai.auto.apitest.common.annotation.Delete;
import qadai.auto.apitest.common.annotation.Get;
import qadai.auto.apitest.common.annotation.Param;
import qadai.auto.apitest.common.annotation.PathVar;
import qadai.auto.apitest.common.annotation.Post;
import qadai.auto.apitest.common.annotation.Put;
import qadai.auto.apitest.common.enums.HttpType;
import qadai.auto.apitest.log.LogUtils;
import qadai.auto.apitest.testobject.ReqestEntity;
import qadai.auto.apitest.testsuite.Retry;

/*
 * 调用处理类invocationhandler
 * (invoke三个参数：
	proxy：就是代理对象，newProxyInstance方法的返回对象
 	method：调用的方法
	args: 方法中的参数
 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
 */

public class AnnotationInvocationHandler implements InvocationHandler{
	HttpType httpType;
	String path;
	String description;
	HttpMethod httpMethod;
	private static Logger log = LogUtils.className(AnnotationInvocationHandler.class);
	
	public AnnotationInvocationHandler(String host) {
		httpMethod = new HttpMethod(host);
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		Annotation[] annotations = method.getAnnotations();
		if (annotations.length == 0){
			throw new RuntimeException(String.format("%s方法未配置请求类型注解，如@POST、@GET等",method.getName()));
		}
		
		//解析请求的方法 get post put delete
		if (annotations[0] instanceof Get){
			httpType = HttpType.GET;
			path =  ((Get)annotations[0]).path();
			description = ((Get)annotations[0]).description();
		}else if (annotations[0] instanceof Post) {
			httpType = HttpType.POST;
			path =  ((Post)annotations[0]).path();
			description = ((Post)annotations[0]).description();
		}else if (annotations[0] instanceof Put) {
			httpType = HttpType.PUT;
			path =  ((Put)annotations[0]).path();
			description = ((Put)annotations[0]).description();
		}else if (annotations[0] instanceof Delete) {
			httpType = HttpType.DELETE;
			path =  ((Delete)annotations[0]).path();
			description = ((Delete)annotations[0]).description();
		}else {
			throw new RuntimeException(String.format("不支持%s方法", method.getName()));
		}
		
		//将方法上对应的参数解析出来
		Annotation[][] parameters = method.getParameterAnnotations();
		ReqestEntity  reqestEntity = new ReqestEntity();
		if (parameters.length != 0){
			Map<String, Object> map = new HashMap<String, Object>();
			for (Integer i = 0; i < parameters.length; i++){
				//参数注释类型
				Annotation[] params = parameters[i];
				if(params.length == 0){
					throw new RuntimeException(String.format("方法%s中缺少参数注释，如@param", method.getName()));
				}
				
				//路径变量注释
				if(params[0] instanceof Param){
					map.put(((Param) params[0]).value(), args[i]);
				}else if (params[0] instanceof PathVar) {
					path = path.replaceFirst("\\{\\}", args[i].toString());
				}else {
					throw new RuntimeException(String.format("暂时不支持方法 %s中配置参数注释", method.getName()));
				}
			}
			reqestEntity.setParams(map);
		}
		log.info("[" + path + "]" + description);
		reqestEntity.setType(httpType);
		reqestEntity.setPath(path);		
			
		return httpMethod.request(reqestEntity);
	}
}
