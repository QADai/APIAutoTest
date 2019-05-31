package qadai.auto.apitest.log;

import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class CustomListener extends TestListenerAdapter {
	private static Logger log = LogUtils.className(CustomListener.class);
	 
    @Override
    public void onTestFailure(ITestResult tr) {
        log.error(tr.getName()+ "--测试失败\n");
    }
	 
    @Override
    public void onTestSkipped(ITestResult tr) {
        log.debug(tr.getName()+ "--测试跳过\n");
    }
	 
    @Override
    public void onTestSuccess(ITestResult tr) {
        log.info(tr.getName()+ "--测试通过\n");
    }

}
