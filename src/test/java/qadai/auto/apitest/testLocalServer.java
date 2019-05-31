package qadai.auto.apitest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Log;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import org.slf4j.Logger;

import qadai.auto.apitest.common.utils.ExcelUtils;
import qadai.auto.apitest.common.utils.ProxyInterface;
import qadai.auto.apitest.config.GlobalVars;
import qadai.auto.apitest.log.LogUtils;
import qadai.auto.apitest.testdata.TCsEntity;
import qadai.auto.apitest.testobject.ILocalServer;

public class testLocalServer {
	private static Logger log = LogUtils.className(testLocalServer.class);
	private ILocalServer localServer = ProxyInterface.create(ILocalServer.class);
	private Response response;	
	
	  @BeforeTest
	  public void beforeTest() {
	  }

	  @AfterTest
	  public void afterTest() {
	  }
	  
	  @DataProvider(name="testdata")
	  public static Object[][] data(){
		  return new Object [][]{
			  {"wanglaosi","woman","status","1" },
			  {"wanglaosi","woman1","status","2" }
		  };
	  }
	  
	  @Test(dataProvider = "TCApiProvider", dataProviderClass = TCsEntity.class)
	  public void testFailed__paramxxxxx(String param1, String param2, String expectedcode ,String v1, String v1Value){
		  GlobalVars.HEADERS.put("content-type", "application/json");
		  
		  response = localServer.testPostHeader(param1, param2);
		  if (expectedcode=="200"){
			  response.then().body(v1, equalTo(v1Value));
		  }
		  else if (expectedcode == "400") {
			log.info("结果返回400");
		}
		  	  
	  }
	  
//	  @Test
//	  public void testParam(){		  
//		  response = localServer.testPostHeader("huhansan", "man");
//		  response.then().body("text", equalTo("胡汉三带参数来了"));		  
//	  }
	  
		  
}
