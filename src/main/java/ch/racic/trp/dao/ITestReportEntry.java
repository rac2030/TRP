/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rac on 24.09.14.
 */
public interface ITestReportEntry {

    List<ITestReportEntry> entries = new ArrayList<ITestReportEntry>();

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public boolean isInProgress();

    public ITestReportEntry setInProgress(boolean inProgress);

    public ITestReportEntry start();

    public ITestReportEntry finish();

    public ITestReportEntry getCurrentActiveStep();

}
