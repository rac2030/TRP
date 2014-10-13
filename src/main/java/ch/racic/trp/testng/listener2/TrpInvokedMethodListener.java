/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng.listener2;

import ch.racic.trp.dao.TrpTestMethodReport;
import ch.racic.trp.testng.Reporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener2;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;

/**
 * Created by rac on 22.09.14.
 */
public class TrpInvokedMethodListener implements IInvokedMethodListener2 {

    private static final Logger log = LogManager.getLogger(TrpInvokedMethodListener.class);

    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult, ITestContext iTestContext) {
        log.entry(iInvokedMethod, iTestResult, iTestContext);
        Reporter.setCurrentTestResult(iTestResult);
        Test testAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
        if (testAnnotation != null) {
            String testName = testAnnotation.testName();
            if (testName == null || testName.trim().equals(""))
                testName = iTestResult.getName();
            TrpTestMethodReport testMethodReport = new TrpTestMethodReport(testName, iTestResult.getName(), testAnnotation, iTestResult);

            iTestResult.setAttribute("teststepreporter", testMethodReport);
        }
    }

    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult, ITestContext iTestContext) {
        log.entry(iInvokedMethod, iTestResult, iTestContext);
        Test testAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
        if (testAnnotation != null) {
            TrpTestMethodReport testMethodReport = (TrpTestMethodReport) iTestResult.getAttribute("teststepreporter");
            testMethodReport.setResult(iTestResult);
        }
    }

    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        log.entry(iInvokedMethod, iTestResult);
    }

    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        log.entry(iInvokedMethod, iTestResult);
    }
}
