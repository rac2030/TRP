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
@XStreamAlias("testNG-Test")
public class TrpTestNGTestReport extends AbstractReportElement {
    @XStreamAlias("classes")
    private List<TrpTestClassReport> testClasses = new ArrayList<TrpTestClassReport>();

    public TrpTestNGTestReport(String name) {
        setName(name);
    }

    public List<TrpTestClassReport> getTestClasses() {
        return testClasses;
    }

    public void setTestClasses(List<TrpTestClassReport> testClasses) {
        this.testClasses = testClasses;
    }

    public synchronized void add(TrpTestClassReport testngClass) {
        testClasses.add(testngClass);
    }

    public synchronized TrpTestClassReport get(String name) {
        for (TrpTestClassReport clazz : testClasses) {
            if (name.equals(clazz.getName())) {
                return clazz;
            }
        }
        return null;
    }
}
