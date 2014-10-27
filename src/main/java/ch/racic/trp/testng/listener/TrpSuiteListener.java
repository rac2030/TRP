/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng.listener;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.io.File;
import java.io.IOException;

/**
 * Created by rac on 27.10.14.
 */
public class TrpSuiteListener implements ISuiteListener {

    private static int suiteCounter = 0;
    private static File outputRoot;

    /**
     * This method is invoked before the SuiteRunner starts.
     *
     * @param suite
     */
    public void onStart(ISuite suite) {
        // Statically initialize output root folder if not yet initialized
        if (outputRoot == null) {
            outputRoot = new File(suite.getOutputDirectory());
        }

        // clean output root for current run to avoid clashes with existing files
        if (outputRoot.exists())
            try {
                FileUtils.deleteDirectory(outputRoot);
            } catch (IOException e) {
                LogManager.getLogger().error("IO Exception during cleanup of outputfolder", e);
            }
        //LogManager.getLogger().info("Cleaned = " + cleaned);
        //TODO start suite folder for results
        //Automatically is in a folder with suite name... what happens if two suites have the same name?
        //File suiteOutRoot = new File(outputRoot, String.format("%04d", suiteCounter++) + "-" + suite.getName());
        //suiteOutRoot.mkdirs();
        //TODO save suite output folder in suite context
    }

    /**
     * This method is invoked after the SuiteRunner has run all the test suites.
     *
     * @param suite
     */
    public void onFinish(ISuite suite) {

    }
}
