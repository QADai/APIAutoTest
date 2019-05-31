package qadai.auto.apitest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import qadai.auto.apitest.common.utils.ProxyInterface;
import qadai.auto.apitest.testobject.IHttpBinApi;

import static org.hamcrest.Matchers.equalTo;

public class testHttpBin {
	private IHttpBinApi httpBinApi = ProxyInterface.create(IHttpBinApi.class);
	private Response response;	
	
	  @BeforeTest
	  public void beforeTest() {
	  }

	  @AfterTest
	  public void afterTest() {
	  }

	  @Test
	  public void testSuccessed__apianme() {
		  System.out.println("test");
	  }
	  
	  @Test
	  public void testFailed__apianme(){
		  assertEquals("a", "b");
	  }
	  
	 @Test
	 public void testHttpBin_Get(){
		 response = httpBinApi.testGetmethod();
		 response.then().body("origin", equalTo("119.57.39.202, 119.57.39.202"));
	 }
	  
	 @Test
	 public void testHttpBin_Post(){
		 response = httpBinApi.testPostmethod();
		 response.then().body("headers.Host", equalTo("httpbin.org"));
	 }
	 
	 @Test
	 public void testHttpBin_Put(){
		 response = httpBinApi.testPutmethod();
		 response.then().body("url", equalTo("https://httpbin.org/put"));	 
	 }
	 
	 @Test
	 public void testHttpBin_Delete(){
		 response = httpBinApi.testDeletemethod();
		 response.then().body("headers.Host", equalTo("httpbin.org"));
		 response.then().assertThat().statusCode(200);
		 
	 }
	  

}
