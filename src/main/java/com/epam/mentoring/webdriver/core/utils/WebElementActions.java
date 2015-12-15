package com.epam.mentoring.webdriver.core.utils;

import com.epam.mentoring.webdriver.core.driver.Browser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.epam.mentoring.webdriver.core.log.Logger.LOG;

/**
 * Created by Alyona_Chernova on 12/14/2015.
 */
public class WebElementActions {
    public static void highlightElement(WebElement we, String color){
        JavascriptExecutor js = ((JavascriptExecutor) Browser.getInstance());
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", we);
    }

    public static void dragAndDrop(WebElement draggable, WebElement droppable){

        if ((droppable != null)&&(draggable != null)) {
            Actions builder = new Actions(Browser.getInstance());
            builder.dragAndDrop(draggable, droppable).build().perform();
            LOG.debug("Drag and drop the email is done");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void clickByAction(WebElement clickable){
        Actions builder = new Actions(Browser.getInstance());
        builder.click(clickable).build().perform();
    }

    public static void sendKeyByAction(Keys key){
        Actions builder = new Actions(Browser.getInstance());
        builder.sendKeys(key).build().perform();
    }

}
