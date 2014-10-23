/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.dao;

import org.testng.ITestResult;

/**
 * Created by rac on 24.09.14.
 */
public interface ITestReportEntry {

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public boolean isInProgress();

    public ITestReportEntry setInProgress(boolean inProgress);

    public ITestReportEntry start();

    /**
     * @param result can be null
     * @return
     */
    public ITestReportEntry finish(ITestResult result);

    public ITestReportEntry getCurrentActiveStep();

    public String getTestClass();

    public ITestReportEntry setTestClass(String clazz);

    public String getTestMethod();

    public ITestReportEntry setTestMethod(String method);

    public String getTestInstance();

    public ITestReportEntry setTestInstance(String instanceIdentifier);

    public String getThreadId();

    public ITestReportEntry setThreadId(String threadId);

    public Throwable getThrowable();

    public ITestReportEntry setThrowable(Throwable throwable);

    public ITestReportEntry setExpectedExceptions(Class[] classes, String messageRegex);
}
