/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.samples;

import ch.racic.trp.annotations.TRPGroup;
import ch.racic.trp.dao.TrpStepReport;
import ch.racic.trp.dao.TrpTestMethodReport;
import ch.racic.trp.testng.Reporter;
import ch.racic.trp.testng.guicemodule.TestStepInterceptor;
import ch.racic.trp.testng.harness.AbstractTrpTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

/**
 * Created by rac on 22.09.14.
 */
@Guice(moduleFactory = TestStepInterceptor.class)
public class SampleTest2 extends AbstractTrpTest {

    private static final Logger log = LogManager.getLogger(SampleTest2.class);

    @BeforeClass
    public void beforeClass(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @AfterClass
    public void afterClass(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @BeforeGroups("TestGroup")
    public void beforeGroups(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @AfterGroups("TestGroup")
    public void afterGroups(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @BeforeMethod
    public void beforeMethod(ITestContext iTestContext, Method method, Object[] params) {
        log.entry(iTestContext, method, params);

    }

    @AfterMethod
    public void afterMethod(ITestResult iTestResult, Method method) {
        log.entry(iTestResult, method);

    }

    @BeforeSuite
    public void beforeSuite(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @AfterSuite
    public void afterSuite(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @BeforeTest
    public void beforeTest(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @AfterTest
    public void afterTest(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @Test(testName = "ding dong 1")
    public void test1(ITestContext iTestContext) {
        log.entry(iTestContext);
        TrpTestMethodReport stepReporter = (TrpTestMethodReport) iTestContext.getAttribute("teststepreporter");
        //stepReporter.add(new TrpStepReport("Sample step in ding dong 1"));
        Reporter.log("Sample log in ding dong 1");
    }

    @Test(groups = "TestGroup", suiteName = "test2 suite")
    public void test2(ITestContext iTestContext) {
        log.entry(iTestContext);
        TrpTestMethodReport stepReporter = (TrpTestMethodReport) iTestContext.getAttribute("teststepreporter");
        //stepReporter.add(new TrpStepReport("Sample step in test2"));
        Reporter.log(new TrpStepReport("logstep","Sample step in test2"));

    }

    @Test(testName = "ding", description = "Mach dein ding")
    public void test3(ITestContext iTestContext) {
        log.entry(iTestContext);
        Reporter.log("mach dein ding in report");
        Reporter.log(new TrpStepReport("logstep", "Sample step in ding"));
    }

    @Test(groups = "TestGroup", testName = "ding")
    public void test4(ITestContext iTestContext) {
        log.entry(iTestContext);
        Reporter.log("ding method report");
        Assert.fail();

    }

    @Test
    public void test5(ITestContext iTestContext) {
        log.entry(iTestContext);
        dummyStep();

    }

    @TRPGroup("Dummy steps")
    public void dummyStep() {
        Reporter.log("Dummy log");
        System.out.println("println test");
        log.info("logger capture test info");
        log.warn("warn logger test");
        log.error("error log", new Exception("test"));
    }

}
