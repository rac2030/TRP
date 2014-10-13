/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng.harness;

import ch.racic.trp.dao.TrpTestClassReport;
import ch.racic.trp.dao.TrpTestNGSuiteReport;
import ch.racic.trp.dao.TrpTestNGTestReport;
import ch.racic.trp.testng.listener.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

/**
 * Created by rac on 23.09.14.
 */
//@Listeners({TrpSuiteListener.class, TrpTestListener.class, TrpInvokedMethodListener.class, TrpReporter.class, TrpAnnotationTransformer.class, /**TrpHookable.class, **/TrpMethodInterceptor.class})
public abstract class AbstractTrpTest {

    private static final Logger log = LogManager.getLogger(AbstractTrpTest.class);

    //@BeforeClass
    public void trpBeforeClass(ITestContext iTestContext) {
        log.entry(iTestContext);
        TrpTestNGSuiteReport suiteReport = ((TrpTestNGSuiteReport) iTestContext.getSuite().getAttribute(TrpSuiteListener.SUITE_REPORT_KEY));
        log.debug(suiteReport.get(iTestContext.getName()));
        String classIdentifier = this.getClass().getName();
        TrpTestNGTestReport currentTest = suiteReport.get(iTestContext.getName());
        if (currentTest.get(classIdentifier) == null) {
            currentTest.add(new TrpTestClassReport(classIdentifier));
        }
    }

    @AfterClass
    public void trpAfterClass(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @BeforeMethod
    public void trpBeforeMethod(ITestContext iTestContext, Method method, Object[] params) {
        log.entry(iTestContext, method, params);

    }

    @AfterMethod
    public void trpAfterMethod(ITestResult iTestResult, Method method) {
        log.entry(iTestResult, method);

    }

    @BeforeSuite
    public void trpBeforeSuite(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @AfterSuite
    public void trpAfterSuite(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @BeforeTest
    public void trpBeforeTest(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @AfterTest
    public void trpAfterTest(ITestContext iTestContext) {
        log.entry(iTestContext);

    }
}
