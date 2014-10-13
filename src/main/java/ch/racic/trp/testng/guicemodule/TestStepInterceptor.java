/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 */

package ch.racic.trp.testng.guicemodule;

import ch.racic.trp.annotations.TRPGroup;
import ch.racic.trp.testng.Reporter;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.matcher.Matcher;
import com.google.inject.matcher.Matchers;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.testng.IModuleFactory;
import org.testng.ITestContext;

/**
 * Created by rac on 08.10.14.
 */
public class TestStepInterceptor extends AbstractModule implements MethodInterceptor, IModuleFactory{
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        TRPGroup groupAnnotation = methodInvocation.getMethod().getAnnotation(TRPGroup.class);
        String groupName = groupAnnotation.value();
        Reporter.log("Group " + groupName + " started");
        Object proceed;
        try{
            proceed = methodInvocation.proceed();
        } finally {
            Reporter.log("Group " + groupName + " finished");
        }
        return proceed;
    }

    @Override
    protected void configure() {
        install(this);
        bindInterceptor(Matchers.any(), Matchers.any(), this);
    }

    /**
     * @param context   The current test context
     * @param testClass The test class
     * @return The Guice module that should be used to get an instance of this test class.
     */
    public Module createModule(ITestContext context, Class<?> testClass) {
        //Module ret = new TestStepInterceptor();
        context.addGuiceModule(TestStepInterceptor.class, this);
        return this;
    }
}
