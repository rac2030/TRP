/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.dao;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rac on 24.09.14.
 */
@XStreamAlias("group")
public class TrpGroupReport extends AbstractReportElement implements ITestReportEntry {
    private List<ITestReportEntry> entries = new ArrayList<ITestReportEntry>();

    public TrpGroupReport(String name) {
        super(name);
    }

    public TrpGroupReport(String name, String description) {
        super(name, description);
    }

    public List<ITestReportEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ITestReportEntry> entries) {
        this.entries = entries;
    }

    public synchronized void add(ITestReportEntry testReportEntry) {
        entries.add(testReportEntry);
    }

    public synchronized ITestReportEntry get(String name) {
        for (ITestReportEntry entry : entries) {
            if (name.equals(entry.getName())) {
                return entry;
            }
        }
        return null;
    }

    protected void cleanupAfterWriteout() {
        super.cleanupAfterWriteout();
        entries = null;
    }

    public synchronized ITestReportEntry getCurrentActiveStep() {
        if (!isInProgress()) {
            // We reached a dead end as this node is not anymore in progress
            return null;
        } else if (entries.isEmpty()) {
            // This is in progress but we don't have any childs yet
            return this;
        }
        // check if the last child is active
        ITestReportEntry ret = entries.get(entries.size() - 1);
        if (!ret.isInProgress()) {
            return this;
        }
        return ret.getCurrentActiveStep();
    }
}
