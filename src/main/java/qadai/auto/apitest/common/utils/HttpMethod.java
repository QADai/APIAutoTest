package qadai.auto.apitest.common.utils;

import java.awt.geom.Area;
import java.util.Map;

import org.jsoup.nodes.Entities.EscapeMode;
import org.slf4j.Logger;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.config.JsonPathConfig.NumberReturnType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import qadai.auto.apitest.log.LogUtils;
import qadai.auto.apitest.testobject.ReqestEntity;
import qadai.auto.apitest.config.GlobalVars;
import qadai.auto.apitest.common.enums.HttpType;
/*
 * 基于rest-assured
 */
public class HttpMethod {
	private static Logger log = LogUtils.className(HttpMethod.class);
	
	private RestAssuredConfig restAssuredConfig;
    private Response response;
    private String baseURL;
    
    HttpMethod(String url) {
        baseURL = url;
     // 根据需要进行设置
//        restAssuredConfig = RestAssured.config().jsonConfig(jsonConfig().numberReturnType(NumberReturnType.DOUBLE));
    }
    
    /*
     * 获取请求的URL参数
     */
    private String getRequestInfo(String path, Map<String, Object> params) {
    	
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : params.keySet()) {
            stringBuilder.append(key).append("=").append(params.get(key)).append("&");
        }

        if (stringBuilder.length() >= 1 && stringBuilder.toString().endsWith("&")) {
            stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
        }

        return getRequestInfo(path) + "?" + stringBuilder;
    }
    
    /*
     * 获取本次请求的URL，不携带参数
     */
    private String getRequestInfo(String path) {
        return RestAssured.baseURI + path;
    }

    /*
     * 获取响应信息
     */
    private String getResponseInfo(Response response) {
        // TODO - 此处容易抛异常
        if (response.contentType().contains("json")) {
            return "[" + response.statusCode() + "]" + response.jsonPath().get();
        } else {
            return "[" + response.statusCode() + "]" + response.htmlPath().get();
        }
    }
    
    
    /*
     * 装载此次请求配置
     */
    private RequestSpecification getRequestSpecification(String path) {
//    	RequestSpecBuilder builder = new RequestSpecBuilder();
//    	builder.addCookie((Cookie) GlobalVars.COOKIES);
//    	builder.addHeaders(GlobalVars.HEADERS);
//    	builder.setConfig(restAssuredConfig);
//    	RequestSpecification requestSpec = builder.build();
//    	return requestSpec;
        return RestAssured.given().headers(GlobalVars.HEADERS).cookies(GlobalVars.COOKIES).config(restAssuredConfig).basePath(path);
    }
    
    /*
     * 带参数请求
     */
    private Response request(HttpType httpType, String path, Map<String, Object> params) {
    	if (httpType == HttpType.GET  ){
    		log.debug("[" + httpType.getValue() + "]" + getRequestInfo(path, params));
    	}
    	else {
    		log.debug("[" + httpType.getValue() + "]" + getRequestInfo(path));
		}
        
        switch (httpType) {
            case GET:
                response = getRequestSpecification(path).params(params).get();
                break;
            case POST:
            	response = getRequestSpecification(path).body(params).post(); //对比下一行，根据具体情况最相应的调整
//                response = getRequestSpecification(path).params(params).post();
                break;
            case PUT:
                response = getRequestSpecification(path).params(params).put();
                break;
            case DELETE:
                response = getRequestSpecification(path).params(params).delete();
                break;
            default:
                throw new RuntimeException(String.format("暂不支持%s请求类型", httpType));
        }

        return response;
    }
    
    
    /*
     * 不带参数请求
     */
    private Response request(HttpType httpType, String path) {
        log.debug("[" + httpType.getValue() + "]" + getRequestInfo(path));

        switch (httpType) {
            case GET:
                response = getRequestSpecification(path).get();
                break;
            case POST:
                response = getRequestSpecification(path).post();
                break;
            case PUT:
                response = getRequestSpecification(path).put();
                break;
            case DELETE:
                response = getRequestSpecification(path).delete();
                break;
            default:
                throw new RuntimeException(String.format("暂不支持%s请求类型", httpType));
        }

        return response;
    }
    
    public Response request(ReqestEntity requestEntity) {
        RestAssured.baseURI = baseURL;

        if (!requestEntity.getParams().isEmpty()) {
            response = request(requestEntity.getType(), requestEntity.getPath(), requestEntity.getParams());
        } else {
            response = request(requestEntity.getType(), requestEntity.getPath());
        }

        log.debug(getResponseInfo(response));
        return response;
    }
}
