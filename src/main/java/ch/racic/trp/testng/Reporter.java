/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng;

import ch.racic.trp.dao.ITestReportEntry;
import ch.racic.trp.dao.TrpGroupReport;
import ch.racic.trp.dao.TrpStepReport;
import ch.racic.trp.testng.listener.TrpTestListener;
import org.testng.ITestResult;
import org.testng.TestRunner;
import org.testng.collections.Lists;
import org.testng.collections.Maps;
import org.testng.util.Strings;

import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by rac on 03.10.14. Taken code from org.testng.Reporter as base, just added some bits regarding Trp
 */
public class Reporter {
    // when tests are run in parallel, each thread may be working with different
    // 'current test result'. Also, this value should be inherited if the test code
    // spawns its own thread.
    private static ThreadLocal<ITestResult> m_currentTestResult = new InheritableThreadLocal<ITestResult>();

    /**
     * All output logged in a sequential order.
     */
    private static List<String> m_output = new Vector<String>();

    private static Map<ITestResult, List<Integer>> m_methodOutputMap = Maps.newHashMap();

    private static boolean m_escapeHtml = false;

    public static List<String> getOutput() {
        return m_output;
    }

    /**
     * Erase the content of all the output generated so far.
     */
    public static void clear() {
        m_methodOutputMap.clear();
        m_output.clear();
    }

    /**
     * @param escapeHtml If true, use HTML entities for special HTML characters (<, >, &, ...).
     */
    public static void setEscapeHtml(boolean escapeHtml) {
        m_escapeHtml = escapeHtml;
    }

    private static synchronized void log(String s, ITestResult m) {
        // Escape for the HTML reports
        if (m_escapeHtml) {
            s = Strings.escapeHtml(s);
        }
        ITestReportEntry testReportRoot = (ITestReportEntry) m.getAttribute(TrpTestListener.TEST_STEP_REPORT_KEY);
        if (testReportRoot == null)
            return;
        ITestReportEntry activeStep = testReportRoot.getCurrentActiveStep();
        if (activeStep instanceof TrpStepReport) {
            // We are inside a step, add it to log?
            ((TrpStepReport) activeStep).appendLog(s);
        } else if (activeStep instanceof TrpGroupReport) {
            // We are in a group but outside a step
            // Add a log step
            ((TrpGroupReport) activeStep).add(new TrpStepReport("LOG", s).start().finish(null));
        }
        // synchronization needed to ensure the line number and m_output are updated atomically
        /*int n = getOutput().size();
        List<Integer> lines = m_methodOutputMap.get(m);
        if (lines == null) {
            lines = Lists.newArrayList();
            m_methodOutputMap.put(m, lines);
        }
        lines.add(n);
        getOutput().add(s);*/
    }

    /**
     * Log the passed string to the HTML reports
     *
     * @param s The message to log
     */
    public static void log(String s) {
        log(s, getCurrentTestResult());
    }

    public static void log(ITestReportEntry step) {
        //log("step[" + step.getName() + ", " + step.getDescription() + "]", getCurrentTestResult());
        ITestReportEntry testReportRoot = (ITestReportEntry) getCurrentTestResult().getAttribute(TrpTestListener.TEST_STEP_REPORT_KEY);
        if (testReportRoot == null)
            return;
        ITestReportEntry activeStep = testReportRoot.getCurrentActiveStep();
        if (activeStep instanceof TrpStepReport) {
            // We are inside a step, what to do, this should not happen?
            System.err.println("reporter error, a step is active but we are in a new step");
        } else if (activeStep instanceof TrpGroupReport) {
            // We are in a group but outside a step
            // Add a log step
            ((TrpGroupReport) activeStep).add(step);
        }
    }

    /**
     * Log the passed string to the HTML reports if the current verbosity is equal or greater than the one passed in
     * parameter. If logToStandardOut is true, the string will also be printed on standard out.
     *
     * @param s                The message to log
     * @param level            The verbosity of this message
     * @param logToStandardOut Whether to print this string on standard out too
     */
    public static void log(String s, int level, boolean logToStandardOut) {
        if (TestRunner.getVerbose() >= level) {
            log(s, getCurrentTestResult());
            if (logToStandardOut) {
                System.out.println(s);
            }
        }
    }

    /**
     * Log the passed string to the HTML reports.  If logToStandardOut is true, the string will also be printed on
     * standard out.
     *
     * @param s                The message to log
     * @param logToStandardOut Whether to print this string on standard out too
     */
    public static void log(String s, boolean logToStandardOut) {
        log(s, getCurrentTestResult());
        if (logToStandardOut) {
            System.out.println(s);
        }
    }

    /**
     * Log the passed string to the HTML reports if the current verbosity is equal or greater than the one passed in
     * parameter
     *
     * @param s     The message to log
     * @param level The verbosity of this message
     */
    public static void log(String s, int level) {
        if (TestRunner.getVerbose() >= level) {
            log(s, getCurrentTestResult());
        }
    }

    /**
     * @return the current test result.
     */
    public static ITestResult getCurrentTestResult() {
        return m_currentTestResult.get();
    }

    public static void setCurrentTestResult(ITestResult m) {
        m_currentTestResult.set(m);
    }

    public static synchronized List<String> getOutput(ITestResult tr) {
        List<String> result = Lists.newArrayList();
        List<Integer> lines = m_methodOutputMap.get(tr);
        if (lines != null) {
            for (Integer n : lines) {
                result.add(getOutput().get(n));
            }
        }

        return result;
    }
}
