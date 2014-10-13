/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng.listener2;

import ch.racic.trp.dao.TrpGroupReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Test;

/**
 * Created by rac on 22.09.14.
 */
public class TrpTestListener extends TestListenerAdapter {

    private static final Logger log = LogManager.getLogger(TrpTestListener.class);
    public static final String TEST_STEP_REPORT_KEY = "current.trp.test.step.report";

    public void onStart(ITestContext iTestContext) {
        log.entry(iTestContext);
        //iTestContext.addGuiceModule(TestStepInterceptor.class, new TestStepInterceptor());

    }

    public void onTestStart(ITestResult iTestResult) {
        log.entry(iTestResult);
        Test testAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
                String testName = testAnnotation.testName();
                if (testName == null || testName.trim().equals(""))
                    testName = iTestResult.getName();
        TrpGroupReport rootGroup = new TrpGroupReport(testName);
        rootGroup.start();
        iTestResult.setAttribute(TEST_STEP_REPORT_KEY, rootGroup);

    }

    public void onTestSuccess(ITestResult iTestResult) {
        log.entry(iTestResult);
        TrpGroupReport rootGroup = (TrpGroupReport) iTestResult.getAttribute(TEST_STEP_REPORT_KEY);
        rootGroup.finish();
    }

    public void onTestFailure(ITestResult iTestResult) {
        log.entry(iTestResult);
        TrpGroupReport rootGroup = (TrpGroupReport) iTestResult.getAttribute(TEST_STEP_REPORT_KEY);
        rootGroup.finish();
    }

    public void onTestSkipped(ITestResult iTestResult) {
        log.entry(iTestResult);
        TrpGroupReport rootGroup = (TrpGroupReport) iTestResult.getAttribute(TEST_STEP_REPORT_KEY);
        rootGroup.finish();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.entry(iTestResult);
        TrpGroupReport rootGroup = (TrpGroupReport) iTestResult.getAttribute(TEST_STEP_REPORT_KEY);
        rootGroup.finish();
    }


}
