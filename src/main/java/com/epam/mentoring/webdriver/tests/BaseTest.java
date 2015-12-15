package com.epam.mentoring.webdriver.tests;

import com.epam.mentoring.webdriver.core.driver.Browser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Created by Alyona_Chernova on 12/7/2015.
 */
public class BaseTest {
    @BeforeTest
    public void openBrowser() {
        Browser.open();
    }

    @AfterTest
    public void closeBrowser() {
        Browser.close();
        Browser.quit();
    }
}
