/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.aspects;

import ch.racic.trp.testng.Reporter;

/**
 * Created by rac on 10.10.14.
 */
public aspect LogOutputAspect {
    pointcut sysout():call(* java.io.PrintStream.print*(..));
    after():sysout(){
        Reporter.log("sysout:" + (String) thisJoinPoint.getArgs()[0], false);
    }

    pointcut log4jinfo():call(* org.apache.logging.log4j.Logger.info(..)) && !within(ch.racic.trp.aspects.*);
    after():log4jinfo(){
        Reporter.log("log4jinfo:" + (String) thisJoinPoint.getArgs()[0], false);

    }

    pointcut log4jwarn():call(* org.apache.logging.log4j.Logger.warn(..)) && !within(ch.racic.trp.aspects.*);
    after():log4jwarn(){
        Reporter.log("log4jwarn:" + (String) thisJoinPoint.getArgs()[0], false);

    }

    pointcut log4jerror():call(* org.apache.logging.log4j.Logger.error(..)) && !within(ch.racic.trp.aspects.*);
    after():log4jerror(){
        Reporter.log("log4jerror:" + (String) thisJoinPoint.getArgs()[0], false);

    }



}
