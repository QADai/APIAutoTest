package qadai.auto.apitest.config;

import java.util.HashMap;
import java.util.Map;

public class GlobalVars {
	public static final String REPORTPATH = "test-output/report/";
	public static final String REPORTNAME = "接口自动化测试报告";
    public static final String LOGPATH = "test-output/log"; //这个值配置在xml中
    public static final String TCPATH = "src/main/java/qadai/auto/apitest/testdata/TCs.xlsx";
    public static final int NEEDRUNColumn = 3;
    
    public static int RETRYFAILEDTIMES = 0;
    public static int RETRYFAILEDMAXTIME = 0; 
    
    public static Map<String, String> COOKIES = new HashMap<String, String>();
    public static Map<String, String> HEADERS = new HashMap<String, String>();
    
    
    public static final String TESTSERVER1 = "testserver1";
    public static String TESTSERVER1_URL = "https://httpbin.org";
    public static final String TESTSERVER2 = "testserver2";
    public static String TESTSERVER2_URL = "http://10.101.151.13:8888";
    public static final String TESTSERVER3 = "testserver3";
    public static String TESTSERVER3_URL = "http://api.douban.com/v2/movie";
    public static final String TESTSERVER4 = "testserver4";
    public static String TESTSERVER4_URL = "https://httpbin.org";
    

}
