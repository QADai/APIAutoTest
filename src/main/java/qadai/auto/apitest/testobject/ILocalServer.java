package qadai.auto.apitest.testobject;


import io.restassured.response.Response;
import qadai.auto.apitest.common.annotation.Param;
import qadai.auto.apitest.common.annotation.Post;
import qadai.auto.apitest.common.annotation.Server;
import qadai.auto.apitest.config.GlobalVars;

@Server(value = GlobalVars.TESTSERVER2)
public interface ILocalServer {
	@Post(path = "/post/with/headers", description = "这是一个带header信息的post请求")
	Response testPostHeader(@Param("name") String name, @Param("sex") String sex);
	
	@Post(path = "/postdemoparam", description = "模拟一个post请求")
	Response testPostParam(@Param("name") String name, @Param("sex") String sex);
	
}
