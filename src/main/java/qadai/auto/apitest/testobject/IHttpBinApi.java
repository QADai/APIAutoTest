package qadai.auto.apitest.testobject;

import io.restassured.response.Response;
import qadai.auto.apitest.common.annotation.Delete;
import qadai.auto.apitest.common.annotation.Get;
import qadai.auto.apitest.common.annotation.Post;
import qadai.auto.apitest.common.annotation.Put;
import qadai.auto.apitest.common.annotation.Server;
import qadai.auto.apitest.config.GlobalVars;

@Server(value = GlobalVars.TESTSERVER1)
public interface IHttpBinApi {
	
	@Get(path = "/get", description = "测试get请求")
	Response testGetmethod();
	
	@Post(path = "/post", description = "测试post请求")
	Response testPostmethod();
	
	@Put(path ="/put", description = "测试put请求")
	Response testPutmethod();
	
	@Delete(path ="/delete", description = "测试delete请求")
	Response testDeletemethod();

}
