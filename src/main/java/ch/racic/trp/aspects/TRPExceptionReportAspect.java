/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.aspects;

import ch.racic.trp.dao.ITestReportEntry;
import ch.racic.trp.dao.TrpStepReport;
import ch.racic.trp.testng.Reporter;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by rac on 10.10.14.
 */
@Aspect
public class TRPExceptionReportAspect {
    @Pointcut(value = "execution(public * *(..))")
    public void anyMethod() {
    }

    @AfterThrowing(pointcut = "anyMethod()", throwing = "exception")
    public void afterThrowing(Throwable exception) {
        ITestReportEntry exceptionReport = new TrpStepReport("Exception has been thrown", exception.getMessage())
                .start()
                .setThrowable(exception)
                .finish(null);
        Reporter.log(exceptionReport);
        //Reporter.getCurrentTestResult().setThrowable(exception);
    }


}
