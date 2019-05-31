package qadai.auto.apitest.testsuite;

import org.slf4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import qadai.auto.apitest.config.GlobalVars;
import qadai.auto.apitest.log.LogUtils;

public class Retry implements IRetryAnalyzer {
    private int retryCount = GlobalVars.RETRYFAILEDTIMES;
	private int maxRetryCount = GlobalVars.RETRYFAILEDMAXTIME;
    private static Logger log = LogUtils.className(Retry.class);

    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
        	log.debug("重跑TC：" + result.getName() + 
        			" 当前状态： "+ getResultStatusName(result.getStatus()) + 
        			" 第 " + (retryCount + 1) + " 次.");
            retryCount++;
            return true;
        }
        resetRetrycount(); 
        return false;
    }

    public String getResultStatusName(int status) {
        String resultName = null;
        if (status == 1)
            resultName = "通过";
        if (status == 2)
            resultName = "失败";
        if (status == 3)
            resultName = "跳过";
        return resultName;
    }

    public boolean isRetryAvailable() {
        return retryCount < maxRetryCount;
    }

    public void resetRetrycount() {
        retryCount = 0;
    }
    
    public int getRetryCount() {
		return retryCount;
	}

	public int getMaxRetryCount() {
		return maxRetryCount;
	}

}