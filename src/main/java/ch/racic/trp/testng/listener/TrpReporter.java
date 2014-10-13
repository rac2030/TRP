/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

/**
 * Created by rac on 22.09.14.
 */
public class TrpReporter implements IReporter {

    private static final Logger log = LogManager.getLogger(TrpReporter.class);

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> iSuites, String outputDirectory) {
        log.entry(xmlSuites, iSuites, outputDirectory);
    }




}
