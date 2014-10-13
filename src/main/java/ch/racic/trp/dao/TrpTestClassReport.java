/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.dao;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by rac on 24.09.14.
 */
@XStreamAlias("class")
public class TrpTestClassReport extends AbstractReportElement{
    @XStreamAlias("tests")
    private List<TrpTestMethodReport> testMethods = new ArrayList<TrpTestMethodReport>();

    public TrpTestClassReport(String classIdentifier) {
        setName(classIdentifier);
    }

    public List<TrpTestMethodReport> getTestMethods() {
        return testMethods;
    }

    public void setTestMethods(List<TrpTestMethodReport> testMethods) {
        this.testMethods = testMethods;
    }

    public synchronized void add(TrpTestMethodReport testMethod) {
        testMethods.add(testMethod);
    }

    public synchronized TrpTestMethodReport get(String id) {
        for (TrpTestMethodReport testMethod : testMethods) {
            if (id.equals(testMethod.getId())) {
                return testMethod;
            }
        }
        return null;
    }


}
