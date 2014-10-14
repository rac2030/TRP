/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.dao;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.File;

/**
 * Created by rac on 24.09.14.
 */
@XStreamAlias("step")
public class TrpStepReport extends AbstractReportElement implements ITestReportEntry {
    private String log = "";
    private File response;

    public TrpStepReport(String name) {
        super(name);
    }

    public TrpStepReport(String name, String description) {
        super(name, description);
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public void appendLog(String log) {
        this.log += "***[" + log + "]***";
    }

    public File getResponse() {
        return response;
    }

    public void setResponse(File response) {
        this.response = response;
    }

    public ITestReportEntry getCurrentActiveStep() {
        return isInProgress() ? this : null;
    }
}
