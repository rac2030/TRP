/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng.listener;

import ch.racic.trp.dao.TrpGroupReport;
import com.google.common.collect.Iterables;
import com.thoughtworks.xstream.XStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;

/**
 * Created by rac on 22.09.14.
 */
public class TrpReporter implements IReporter {

    private static final Logger log = LogManager.getLogger(TrpReporter.class);

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> iSuites, String outputDirectory) {
        log.entry(xmlSuites, iSuites, outputDirectory);
        for (ISuite s : iSuites) {
            for (ISuiteResult sr : s.getResults().values()) {
                ITestContext ctx = sr.getTestContext();
                Iterable<ITestResult> allResults = Iterables.concat(
                        ctx.getFailedButWithinSuccessPercentageTests().getAllResults(),
                        ctx.getFailedConfigurations().getAllResults(),
                        ctx.getFailedTests().getAllResults(),
                        ctx.getPassedConfigurations().getAllResults(),
                        ctx.getPassedTests().getAllResults(),
                        ctx.getSkippedConfigurations().getAllResults(),
                        ctx.getSkippedTests().getAllResults()
                );
                for (ITestResult ires : allResults) {
                    TrpGroupReport testReportRoot = ((TrpGroupReport) ires.getAttribute(TrpTestListener.TEST_STEP_REPORT_KEY));
                    log.info("[" + testReportRoot.getName() + "]");
                    XStream stream = new XStream();
                    stream.autodetectAnnotations(true);
                    log.info(stream.toXML(testReportRoot));
                    /**
                     log.info("Log: " + Joiner.on("; ").skipNulls().join(ch.racic.trp.testng.Reporter.getOutput(ires)));
                     log.info("Steps:");
                     for (ITestReportEntry re : testReportRoot.getEntries()) {
                     log.info("\tStep name: " + re.getName());
                     log.info("\tStep desc: " + re.getDescription());
                     if(re instanceof TrpStepReport)
                     log.info("\t\tStep logs: " + ((TrpStepReport)re).getLog());
                     }
                     **/

                }
            }
        }
    }


}
