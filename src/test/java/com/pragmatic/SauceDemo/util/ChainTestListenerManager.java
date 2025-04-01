package com.pragmatic.SauceDemo.util;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.pragmatic.SauceDemo.base.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ChainTestListenerManager extends BaseTest implements ITestListener {

    public void onTestStart(ITestResult result){
        ChainTestListener.log("started test execution: " +result.getTestClass().getName() + "-" +result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result){
        ChainTestListener.log(result.getName() + ": Test case passed");
    }
    public void onTestFailure(ITestResult result){
        ChainTestListener.log(result.getName() + ": Test case failed");
        ChainTestListener.embed(takeScreenshot(),"image/png");
    }

    public void onTestSkipped(ITestResult result){
        ChainTestListener.log(result.getName() + ": Test case got skipped");

    }

}
