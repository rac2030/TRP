/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.dao;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by rac on 24.09.14.
 */
public abstract class AbstractReportElement implements ITestReportEntry {

    private static final Logger log = LogManager.getLogger(AbstractReportElement.class);

    @XStreamAsAttribute
    private String name;
    @XStreamOmitField
    private boolean writtenToFile = false;
    private String description;
    @XStreamOmitField
    private boolean inProgress;

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
        return setInProgress(true);
    }

    public ITestReportEntry finish() {
        return setInProgress(false);
    }
}
