package com.epam.mentoring.webdriver.core.utils;

import com.epam.mentoring.webdriver.core.log.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static com.epam.mentoring.webdriver.core.log.Logger.LOG;


/**
 * Created by Alyona_Chernova on 12/3/2015.
 */
public class TestListener extends TestListenerAdapter {
    public static final String PREFIX_METHOD_STARTED = "METHOD STARTED";
    public static final String PREFIX_METHOD_SUCCESS = "METHOD SUCCESS";
    public static final String PREFIX_METHOD_FAILED = "METHOD FAILED";
    public static final String PREFIX_METHOD_SKIPPED = "METHOD SKIPPED";
    public static final String PREFIX_CONFIGURATION_STARTED = "CONFIGURATION STARTED";
    public static final String PREFIX_CONFIGURATION_SUCCESS = "CONFIGURATION SUCCESS";
    public static final String PREFIX_CONFIGURATION_FAILED = "CONFIGURATION FAILED";
    public static final String PREFIX_CONFIGURATION_SKIPPED = "CONFIGURATION SKIPPED";
    public static final String PREFIX_TEST_STARTED = "TEST STARTED";
    public static final String PREFIX_TEST_FINISHED = "TEST FINISHED";

    @Override
    public void onStart(ITestContext context) {
        LOG.debug(buildMessage(PREFIX_TEST_STARTED, context.getName()));
    }

    @Override
    public void onFinish(ITestContext context) {
        LOG.debug(buildMessage(PREFIX_TEST_FINISHED, context.getName()));
    }

    @Override
    public void onTestStart(ITestResult result) {
        LOG.debug(buildMessage(PREFIX_METHOD_STARTED, getMethodName(result)));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.debug(buildMessage(PREFIX_METHOD_SUCCESS, getMethodName(result), result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOG.debug(buildMessage(PREFIX_METHOD_FAILED, getMethodName(result), result));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOG.debug(buildMessage(PREFIX_METHOD_SKIPPED, getMethodName(result)));
    }

    @Override
    public void onConfigurationSuccess(ITestResult result) {
        LOG.debug(buildMessage(PREFIX_CONFIGURATION_SUCCESS, getMethodName(result), result));
    }

    @Override
    public void onConfigurationFailure(ITestResult result) {
        LOG.debug(buildMessage(PREFIX_CONFIGURATION_FAILED, getMethodName(result), result));
    }

    @Override
    public void onConfigurationSkip(ITestResult result) {
        LOG.debug(buildMessage(PREFIX_CONFIGURATION_SKIPPED, getMethodName(result)));
    }

    @Override
    public void beforeConfiguration(ITestResult tr) {
        LOG.debug(buildMessage(PREFIX_CONFIGURATION_STARTED, getMethodName(tr)));
    }

    private String getMethodName(ITestResult result) {
        StringBuilder builder = new StringBuilder();
        builder.append(result.getTestClass().getRealClass().getSimpleName());
        builder.append(".");
        builder.append(result.getMethod().getMethodName());
        Object description = result.getMethod().getDescription();

        if ((description != null)) {
            if (!description.equals("")) {
                builder.append(" - ");
                builder.append(description);
            }
        }

        return builder.toString();
    }

    private String processTestResult(ITestResult result) {
        String res = null;

        if (result.getThrowable() != null) {
            res = "     Following error thrown while test execution: " + result.getThrowable().getMessage();
        }
        return res;
    }

    private String buildMessage(String prefix, String msg) {
        return buildMessage(prefix, msg, null);
    }

    private String buildMessage(String prefix, String msg, ITestResult result) {
        String error = null;
        StringBuilder builder = new StringBuilder();
        builder.append(prefix);
        builder.append(" - ");
        builder.append(msg);

        if (result != null) {
            error = processTestResult(result);
        }
        if (error != null) {
            builder.append("\n").append(error);
        }

        return builder.toString();
    }
}

