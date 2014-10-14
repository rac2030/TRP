/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.samples;

import ch.racic.trp.annotations.TRPGroup;
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
public class SampleTest extends AbstractTrpTest {

    private static final Logger log = LogManager.getLogger(SampleTest.class);

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

    @Test
    public void test1(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @Test(groups = "TestGroup")
    public void test2(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @Test
    public void test3(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @Test(groups = "TestGroup")
    public void test4(ITestContext iTestContext) {
        log.entry(iTestContext);

    }

    @Test
    public void test5(ITestContext iTestContext) {
        log.entry(iTestContext);
        log.info("Starting test5");
        dummyStep();
        Assert.fail();

    }

    @TRPGroup("Dummy steps")
    private void dummyStep() {
        log.info("Dummy log");
    }

}
