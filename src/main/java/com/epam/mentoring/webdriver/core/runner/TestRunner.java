package com.epam.mentoring.webdriver.core.runner;

import com.epam.mentoring.webdriver.core.utils.TestListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alyona_Chernova on 11/27/2015.
 */
public class TestRunner {

    public static void main(String[] args) {
        TestListenerAdapter tla = new TestListener();
        TestNG testNG = new TestNG();
        testNG.addListener(tla);
        List<String> suites = new ArrayList<>();
        suites.add(".//testNG//testng.xml");
        testNG.setTestSuites(suites);
        testNG.run();
    }
}
