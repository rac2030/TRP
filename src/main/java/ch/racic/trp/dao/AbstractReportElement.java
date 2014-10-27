/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.dao;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IClass;
import org.testng.ISuite;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.io.File;
import java.util.Date;
import java.util.Set;

/**
 * Created by rac on 24.09.14.
 */
public abstract class AbstractReportElement implements ITestReportEntry {

    private static final Logger log = LogManager.getLogger(AbstractReportElement.class);

    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String testClass;
    @XStreamAsAttribute
    private String testMethod;
    @XStreamAsAttribute
    private String testInstance;
    @XStreamAsAttribute
    private String threadId;
    @XStreamOmitField
    private boolean writtenToFile = false;
    @XStreamAsAttribute
    private long startTime;
    @XStreamAsAttribute
    private long endTime;
    @XStreamAsAttribute
    private String status;
    private String description;
    @XStreamOmitField
    private boolean inProgress;
    private String testLog;
    private Throwable throwable;
    private Class[] expectedExceptions;
    private String expectedExceptionMessageRegex;
    @XStreamAsAttribute
    private String uid;

    public AbstractReportElement(String name) {
        this.name = name;
    }

    public AbstractReportElement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWrittenToFile() {
        return writtenToFile;
    }

    public void setWrittenToFile(boolean writtenToFile) {
        this.writtenToFile = writtenToFile;
    }

    public File writeOutToFile() {
        log.error("Not implemented");
        return null;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public ITestReportEntry setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
        return this;
    }

    public ITestReportEntry start() {
        setStartTime(new Date().getTime());
        return setInProgress(true);
    }

    public ITestReportEntry finish(ITestResult result) {
        // Set end time to current time, will be overwritten if a result has been handed over
        setEndTime(new Date().getTime());
        if (result != null) {
            // extract result details and fill attributes
            setStartTime(result.getStartMillis());
            setEndTime(result.getEndMillis());
            String status;
            switch (result.getStatus()) {
                case ITestResult.STARTED:
                    status = "STARTED";
                    break;
                case ITestResult.SKIP:
                    status = "SKIP";
                    break;
                case ITestResult.SUCCESS:
                    status = "SUCCESS";
                    break;
                case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
                    status = "SUCCESS_PERCENTAGE_FAILURE";
                    break;
                case ITestResult.FAILURE:
                    status = "FAILURE";
                    break;
                default:
                    status = Integer.toString(result.getStatus());
            }
            setStatus(status);
            if (result.getThrowable() != null) {
                getCurrentActiveStep().setThrowable(result.getThrowable());
            }
            String instance = result.getInstanceName() + "@" + result.getMethod().getInstanceHashCodes();
            getCurrentActiveStep().setTestInstance(instance);

        } else {
            // Result was null, lets see if throwable has been set and let it pass/fail
            if (getCurrentActiveStep().getThrowable() != null) {
                setStatus("FAILURE");
            } else {
                setStatus("SUCCESS");
            }
        }

        return setInProgress(false);
    }

    public String getTestLog() {
        return testLog;
    }

    public void setTestLog(String testLog) {
        this.testLog = testLog;
    }

    public void appendLog(String log) {
        if (testLog == null) {
            testLog = "";
        }
        this.testLog += "***log*start***[" + new Date() + "]\n" + log + "\n***log*end***\n";
    }

    public ITestReportEntry setTestMethod(String method) {
        testMethod = method;
        return this;
    }

    public ITestReportEntry setTestClass(String clazz) {
        testClass = clazz;
        return this;
    }

    public ITestReportEntry setTestInstance(String instanceIdentifier) {
        testInstance = instanceIdentifier;
        return this;
    }

    public String getTestClass() {
        return testClass;
    }

    public String getTestMethod() {
        return testMethod;
    }

    public String getTestInstance() {
        return testInstance;
    }

    public String getThreadId() {
        return threadId;
    }

    public ITestReportEntry setThreadId(String threadId) {
        this.threadId = threadId;
        return this;
    }

    public long getStartTime() {
        return startTime;
    }

    public ITestReportEntry setStartTime(long startTime) {
        this.startTime = startTime;
        return this;
    }

    public long getEndTime() {
        return endTime;
    }

    public ITestReportEntry setEndTime(long endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public ITestReportEntry setThrowable(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    public ITestReportEntry setExpectedExceptions(Class[] classes, String messageRegex) {
        expectedExceptions = classes;
        expectedExceptionMessageRegex = messageRegex;
        return this;
    }

    public ITestReportEntry setSourceReference(ITestResult iTestResult) {
        String host = iTestResult.getHost();
        Object instance = iTestResult.getInstance();
        Set<String> attributeNames = iTestResult.getAttributeNames();
        ITestNGMethod method = iTestResult.getMethod();
        Object[] parameters = iTestResult.getParameters();
        String name = iTestResult.getName();
        String testName = iTestResult.getTestName();
        IClass testClass = iTestResult.getTestClass();
        String suiteName = null;
        ISuite suite = iTestResult.getTestContext().getSuite();
        if (suite != null) {
            suiteName = suite.getName();
        }

        log.debug("Suite = " + suiteName);

        log.debug(host + instance + attributeNames + parameters + name + testName + testClass);

        setTestClass(method.getRealClass().getCanonicalName());
        setTestMethod(method.getMethodName());
        setTestInstance(iTestResult.getInstanceName());
        setThreadId(method.getId());

        return this;
    }
}
