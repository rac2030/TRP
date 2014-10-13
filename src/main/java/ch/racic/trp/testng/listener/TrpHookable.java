/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

/**
 * Created by rac on 23.09.14.
 */
public class TrpHookable implements IHookable {

    private static final Logger log = LogManager.getLogger(TrpHookable.class);

    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        log.entry(iHookCallBack, iTestResult);
    }
}
