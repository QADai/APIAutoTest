package qadai.auto.apitest.testobject;

import java.util.HashMap;
import java.util.Map;

import qadai.auto.apitest.common.enums.HttpType;

public class ReqestEntity {

    //请求类型：get post put delete
    private HttpType type;
    //接口
    private String path;
    //请求携带的参数，key、value格式
    private Map<String, Object> params = new HashMap<String, Object>();
    //消息体
    private String body;
    //Headers
    private Map<String, String> headers = new HashMap<String, String>();

	public HttpType getType() {
        return type;
    }

    public void setType(HttpType type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
}
