/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng.listener;

import ch.racic.trp.dao.ITestReportEntry;
import ch.racic.trp.dao.TrpGroupReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by rac on 22.09.14.
 */
public class TrpTestListener extends TestListenerAdapter {

    public static final String TEST_STEP_REPORT_KEY = "current.trp.test.step.report";
    public static final String TEST_METHOD_OUTPUT_LOCATION_KEY = "current.trp.test.method.output.location";
    public static final String TEST_METHOD_STEP_COUNTER_KEY = "current.trp.test.method.step.counter";
    private static final Logger log = LogManager.getLogger(TrpTestListener.class);
    private static int testMethodCounter = 0;
    private static int testConfigurationCounter = 0;

    public void onStart(ITestContext iTestContext) {
        log.entry(iTestContext);
    }

    @Override
    public void beforeConfiguration(ITestResult iTestResult) {
        log.entry(iTestResult);
        Test testAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
        String testName = (testAnnotation != null) ? testAnnotation.testName() : null;
        if (testName == null || testName.trim().equals(""))
            testName = iTestResult.getName();
        ITestReportEntry rootGroup = new TrpGroupReport(testName).setSourceReference(iTestResult);
        File testMethodOutRoot = new File(iTestResult.getTestContext().getOutputDirectory(), "configurations/" + String.format("%04d", ++testConfigurationCounter) + "-" + rootGroup.getTestClass() + "." + rootGroup.getTestMethod());
        testMethodOutRoot.mkdirs();
        //save it in context
        iTestResult.setAttribute(TEST_METHOD_OUTPUT_LOCATION_KEY, testMethodOutRoot);
        iTestResult.setAttribute(TEST_METHOD_STEP_COUNTER_KEY, new Integer(0));
        rootGroup.start();
        iTestResult.setAttribute(TEST_STEP_REPORT_KEY, rootGroup);

    }

    @Override
    public void onConfigurationFailure(ITestResult iTestResult) {
        log.entry(iTestResult);
        TrpGroupReport rootGroup = (TrpGroupReport) iTestResult.getAttribute(TEST_STEP_REPORT_KEY);
        rootGroup.finish(iTestResult);
    }

    @Override
    public void onConfigurationSkip(ITestResult iTestResult) {
        log.entry(iTestResult);
        TrpGroupReport rootGroup = (TrpGroupReport) iTestResult.getAttribute(TEST_STEP_REPORT_KEY);
        rootGroup.finish(iTestResult);
    }

    @Override
    public void onConfigurationSuccess(ITestResult iTestResult) {
        log.entry(iTestResult);
        TrpGroupReport rootGroup = (TrpGroupReport) iTestResult.getAttribute(TEST_STEP_REPORT_KEY);
        rootGroup.finish(iTestResult);
    }

    public void onTestStart(ITestResult iTestResult) {
        log.entry(iTestResult);
        Test testAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
        String testName = testAnnotation.testName();
        if (testName == null || testName.trim().equals(""))
            testName = iTestResult.getName();
        File testMethodOutRoot = new File(iTestResult.getTestContext().getOutputDirectory(), String.format("%04d", ++testMethodCounter) + "-" + testName);
        testMethodOutRoot.mkdirs();
        // save it in context
        iTestResult.setAttribute(TEST_METHOD_OUTPUT_LOCATION_KEY, testMethodOutRoot);
        iTestResult.setAttribute(TEST_METHOD_STEP_COUNTER_KEY, new Integer(0));
        //TODO do I need it in context or can I just set it in the root group?
        ITestReportEntry rootGroup = new TrpGroupReport(testName)
                .setSourceReference(iTestResult)
                .setExpectedExceptions(testAnnotation.expectedExceptions(), testAnnotation.expectedExceptionsMessageRegExp())
                .start();
        iTestResult.setAttribute(TEST_STEP_REPORT_KEY, rootGroup);

    }

    public void onTestSuccess(ITestResult iTestResult) {
        log.entry(iTestResult);
        TrpGroupReport rootGroup = (TrpGroupReport) iTestResult.getAttribute(TEST_STEP_REPORT_KEY);
        rootGroup.finish(iTestResult);
    }

    public void onTestFailure(ITestResult iTestResult) {
        log.entry(iTestResult);
        TrpGroupReport rootGroup = (TrpGroupReport) iTestResult.getAttribute(TEST_STEP_REPORT_KEY);
        rootGroup.finish(iTestResult);
    }

    public void onTestSkipped(ITestResult iTestResult) {
        log.entry(iTestResult);
        TrpGroupReport rootGroup = (TrpGroupReport) iTestResult.getAttribute(TEST_STEP_REPORT_KEY);
        rootGroup.finish(iTestResult);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.entry(iTestResult);
        TrpGroupReport rootGroup = (TrpGroupReport) iTestResult.getAttribute(TEST_STEP_REPORT_KEY);
        rootGroup.finish(iTestResult);
    }

    private void startTestMethodLog() {
        //TODO how?
        //LogManager.getRootLogger();
        /**
         FileAppender testlog = FileAppender.createAppender();
         FileAppender fa = new FileAppender();
         fa.setName("FileLogger");
         fa.setFile("mylog.log");
         fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
         fa.setThreshold(Level.DEBUG);
         fa.setAppend(true);
         fa.activateOptions();

         //add appender to any Logger (here is root)
         Logger.getRootLogger().addAppender(fa)
         **/
    }


}
