/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng.listener;

import ch.racic.trp.dao.TrpTestClassReport;
import ch.racic.trp.dao.TrpTestMethodReport;
import ch.racic.trp.dao.TrpTestNGSuiteReport;
import ch.racic.trp.dao.TrpTestNGTestReport;
import com.thoughtworks.xstream.XStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import sun.misc.IOUtils;

/**
 * Created by rac on 22.09.14.
 */
public class TrpSuiteListener implements ISuiteListener {

    private static final Logger log = LogManager.getLogger(TrpSuiteListener.class);
    public static String SUITE_REPORT_KEY = "TrpSuiteReport";

    private TrpTestNGSuiteReport suiteReport;

    public void onStart(ISuite iSuite) {
        log.entry(iSuite);
        suiteReport = new TrpTestNGSuiteReport(iSuite.getName());
        iSuite.setAttribute(SUITE_REPORT_KEY, suiteReport);

    }

    public void onFinish(ISuite iSuite) {
        log.entry(iSuite);
        log.info("=== RESULT TREE ===");
        log.info(suiteReport.getName());
        for(TrpTestNGTestReport test : suiteReport.getTestngTests()) {
            log.info("…" + test.getName());
            for(TrpTestClassReport clazz : test.getTestClasses()) {
                log.info("……" + clazz.getName());
                for(TrpTestMethodReport method : clazz.getTestMethods()) {
                    //log.info("………" + method.getName() + " is " + method.getResult().isSuccess());
                }
            }
        }
        XStream stream = new XStream();
        stream.autodetectAnnotations(true);
        log.info(stream.toXML(suiteReport));

    }

}
