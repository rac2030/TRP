/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng.listener2;

import ch.racic.trp.dao.ITestReportEntry;
import ch.racic.trp.dao.TrpTestMethodReport;
import ch.racic.trp.testng.*;
import com.google.common.base.Joiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;

/**
 * Created by rac on 22.09.14.
 */
public class TrpReporter implements IReporter {

    private static final Logger log = LogManager.getLogger(TrpReporter.class);

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> iSuites, String outputDirectory) {
        log.entry(xmlSuites, iSuites, outputDirectory);
        for (ISuite s : iSuites) {
              for (ISuiteResult sr : s.getResults().values()) {
                  for(ITestResult ires : sr.getTestContext().getPassedTests().getAllResults()) {

                        TrpTestMethodReport testMethodReport = (TrpTestMethodReport) ires.getAttribute("teststepreporter");
                        log.info("Log for " + testMethodReport.getName());
                      log.info("Log: " + Joiner.on("; ").skipNulls().join(ch.racic.trp.testng.Reporter.getOutput(ires)));
                      log.info("Steps:");
                      for(ITestReportEntry re : testMethodReport.getEntries()){
                          log.info("Step name: " + re.getName());
                      }

                  }
              }
            }
    }




}
