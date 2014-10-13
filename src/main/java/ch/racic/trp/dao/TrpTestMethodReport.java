/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.dao;

import com.google.common.base.Joiner;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rac on 24.09.14.
 */
@XStreamAlias("test")
public class TrpTestMethodReport extends AbstractReportElement {
    @XStreamAlias("method")
    @XStreamAsAttribute
    private String methodName;
    @XStreamAlias("description")
    private String description;
    @XStreamAlias("steps")
    private List<ITestReportEntry> entries = new ArrayList<ITestReportEntry>();
    @XStreamOmitField
    private ITestResult result;
    @XStreamAlias("starttime")
    @XStreamAsAttribute
    private long startTime;
    @XStreamAlias("endtime")
    @XStreamAsAttribute
    private long endTime;
    @XStreamAlias("status")
    @XStreamAsAttribute
    private String status;
    @XStreamAlias("id")
    @XStreamAsAttribute
    private String id;


    public TrpTestMethodReport(String testName, String methodName, Test annotation, ITestResult iTestResult) {
        setName(testName);
        this.methodName = methodName;
        description = annotation.description();
        setResult(iTestResult);
        id = generateId(iTestResult);
    }

    public static String generateId(ITestResult iTestResult) {
        return iTestResult.getMethod().getId();
    }

    public List<ITestReportEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ITestReportEntry> entries) {
        this.entries = entries;
    }

    public synchronized void add(ITestReportEntry testMethod) {
        entries.add(testMethod);
    }

    public synchronized ITestReportEntry get(String name) {
        for (ITestReportEntry entry : entries) {
            if (name.equals(entry.getName())) {
                return entry;
            }
        }
        return null;
    }

    public ITestResult getResult() {
        return result;
    }

    public void setResult(ITestResult iTestResult) {
        this.result = iTestResult;
        startTime = iTestResult.getStartMillis();
        endTime = iTestResult.getEndMillis();
        // find text to status
        switch (iTestResult.getStatus()) {
            case ITestResult.FAILURE:
                status = "FAILURE";
                break;
            case ITestResult.SKIP:
                status = "SKIP";
                break;
            case ITestResult.STARTED:
                status = "STARTED";
                break;
            case ITestResult.SUCCESS:
                status = "SUCCESS";
                break;
            case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
                status = "SUCCESS_PERCENTAGE_FAILURE";
                break;
            default:
                status = "Unknown[" + iTestResult.getStatus() + "]";
        }

    }

    public String getId() {
        return id;
    }
}
