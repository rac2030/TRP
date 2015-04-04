/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng.listenerAll;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.List;

/**
 * Created by rac on 23.09.14.
 */
public class TrpMethodInterceptor implements IMethodInterceptor {

    private static final Logger log = LogManager.getLogger(TrpMethodInterceptor.class);

    public List<IMethodInstance> intercept(List<IMethodInstance> iMethodInstances, ITestContext iTestContext) {
        log.entry(iMethodInstances, iTestContext);
        return iMethodInstances;
    }
}
