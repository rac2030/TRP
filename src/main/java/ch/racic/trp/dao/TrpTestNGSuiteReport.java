/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.dao;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rac on 24.09.14.
 */
@XStreamAlias("Suite")
public class TrpTestNGSuiteReport extends AbstractReportElement{
    private List<TrpTestNGTestReport> testngTests = new ArrayList<TrpTestNGTestReport>();

    public TrpTestNGSuiteReport(String name) {
        setName(name);
    }

    public List<TrpTestNGTestReport> getTestngTests() {
        return testngTests;
    }

    public void setTestngTests(List<TrpTestNGTestReport> testngTests) {
        this.testngTests = testngTests;
    }

    public void add(TrpTestNGTestReport testngTest) {
        testngTests.add(testngTest);
    }

    public TrpTestNGTestReport get(String name) {
        for(TrpTestNGTestReport test : testngTests) {
            if(name.equals(test.getName())) {
                return test;
            }
        }
        return null;
    }




}
