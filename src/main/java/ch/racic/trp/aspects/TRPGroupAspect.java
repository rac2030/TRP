/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.aspects;

import ch.racic.trp.annotations.TRPGroup;
import ch.racic.trp.testng.Reporter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by rac on 10.10.14.
 */
@Aspect
public class TRPGroupAspect {
    @Pointcut(value = "execution(public * *(..))")
    public void anyPublicMethod() {
    }

    @Around("anyPublicMethod() && @annotation(group)")
    public Object groupWrapper(ProceedingJoinPoint pjp, TRPGroup group) throws Throwable {
        Reporter.log("Starting group " + group.value());

        // Do what you want with the join point arguments
        for (Object object : pjp.getArgs()) {
            System.out.println(object);
        }
        Object ret;
        try{
        ret = pjp.proceed();
        } finally {

        Reporter.log("Finished group " + group.value());
        }
        return ret;

    }



}
