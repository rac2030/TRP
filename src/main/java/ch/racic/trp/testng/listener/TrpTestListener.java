/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng.listener;

import ch.racic.trp.dao.TrpTestMethodReport;
import ch.racic.trp.dao.TrpTestNGSuiteReport;
import ch.racic.trp.dao.TrpTestNGTestReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import org.testng.annotations.Test;

/**
 * Created by rac on 22.09.14.
 */
public class TrpTestListener extends TestListenerAdapter implements ITestListener {

    private static final Logger log = LogManager.getLogger(TrpTestListener.class);

    private ISuite suite;
    private TrpTestNGSuiteReport suiteReport;
    private TrpTestNGTestReport testReport;

    public void onTestStart(ITestResult iTestResult) {
        log.entry(iTestResult);
        String className = iTestResult.getTestClass().getName();
        Test testAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
        String testName = testAnnotation.testName();
        if (testName == null || testName.trim().equals(""))
            testName = iTestResult.getName();
        TrpTestMethodReport testMethodReport = new TrpTestMethodReport(testName, iTestResult.getName(), testAnnotation, iTestResult);
        testReport.get(className).add(testMethodReport);
        iTestResult.getTestContext().setAttribute("teststepreporter", testMethodReport);

    }

    public void onTestSuccess(ITestResult iTestResult) {
        log.entry(iTestResult);
        ((TrpTestMethodReport) iTestResult.getTestContext().getAttribute("teststepreporter")).setResult(iTestResult);
    }

    public void onTestFailure(ITestResult iTestResult) {
        log.entry(iTestResult);
        ((TrpTestMethodReport) iTestResult.getTestContext().getAttribute("teststepreporter")).setResult(iTestResult);
    }

    public void onTestSkipped(ITestResult iTestResult) {
        log.entry(iTestResult);
        ((TrpTestMethodReport) iTestResult.getTestContext().getAttribute("teststepreporter")).setResult(iTestResult);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.entry(iTestResult);
        ((TrpTestMethodReport) iTestResult.getTestContext().getAttribute("teststepreporter")).setResult(iTestResult);
    }

    public void onStart(ITestContext iTestContext) {
        log.entry(iTestContext);
        suite = iTestContext.getSuite();
        suiteReport = (TrpTestNGSuiteReport) suite.getAttribute(TrpSuiteListener.SUITE_REPORT_KEY);
        String testName = iTestContext.getName();
        if (suiteReport.get(testName) == null) {
            testReport = new TrpTestNGTestReport(testName);
            suiteReport.add(testReport);
        }
        log.debug(suiteReport);
    }

    public void onFinish(ITestContext iTestContext) {
        log.entry(iTestContext);
    }

    @Override
    public void onConfigurationSuccess(ITestResult itr) {
        log.entry(itr);
        super.onConfigurationSuccess(itr);
    }

    @Override
    public void onConfigurationSkip(ITestResult itr) {
        log.entry(itr);
        super.onConfigurationSkip(itr);
    }

    @Override
    public void beforeConfiguration(ITestResult tr) {
        log.entry(tr);
        super.beforeConfiguration(tr);
    }

    @Override
    public void onConfigurationFailure(ITestResult itr) {
        log.entry(itr);
        super.onConfigurationFailure(itr);
    }
}
