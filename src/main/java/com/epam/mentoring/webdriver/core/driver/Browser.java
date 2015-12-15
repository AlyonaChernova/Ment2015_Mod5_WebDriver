package com.epam.mentoring.webdriver.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alyona_Chernova on 11/27/2015.
 */
public class Browser {
    private static WebDriver driver;

    public static WebDriver getInstance() {
        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    private Browser() {
    }

    public static void open() {
        getInstance().navigate().to(Environment.URL);
    }

    public static void close() {

        getInstance().close();
    }

    public static void quit() {
        getInstance().quit();
        driver = null;
    }
}
