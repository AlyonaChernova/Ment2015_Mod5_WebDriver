package com.epam.mentoring.webdriver.application.pages;

import com.epam.mentoring.webdriver.application.entity.Mail;
import com.epam.mentoring.webdriver.core.driver.Browser;
import com.epam.mentoring.webdriver.core.utils.FindElement;
import com.epam.mentoring.webdriver.core.utils.WebElementActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.epam.mentoring.webdriver.core.log.Logger.LOG;

/**
 * Created by Alyona_Chernova on 12/14/2015.
 */
public class ContactPage extends AbstractPage {

    JavascriptExecutor js = ((JavascriptExecutor) Browser.getInstance());

    @FindBy(xpath = "//a[@href='/addressbook']")
    WebElement lnkContact;

    @FindBy(xpath = "//span[text()='Добавить']")
    private WebElement btNewContact;

    @FindBy(xpath = "//span[text()='Удалить']")
    private WebElement btDeleteContact;

    @FindBy(xpath = "//span[text()='Сохранить']")
    private WebElement btSaveContact;

    @FindBy(xpath = "//span[text()='Все контакты']")
    private WebElement lnkAllContacts;

    @FindBy(id = "firstname")
    private WebElement inpFirstname;

    @FindBy(id = "lastname")
    private WebElement inpLastname;

    @FindBy(id = "emails_0")
    private WebElement inpEmails;

    @FindBy(className = "contact__header__title__text")
    private WebElement titleNewContName;

    @FindBy(className = "popup js-layer popup_dark popup_")
    private WebElement alertDeleteConfirmation;

    @FindBy(xpath = "//button[@class='btn btn_main confirm-ok']")
    private WebElement btConfirmationDelete;

    public ContactPage(WebDriver driver) {
        super(Browser.getInstance());
    }

    public void openPage() {
        LOG.info("going to the contact page...");
        WebElementActions.highlightElement(lnkContact, "yellow");
        js.executeScript("arguments[0].click();", lnkContact);
    }

    public boolean createNewContact(Mail mail) {
        LOG.info("Creating new contact is starting up...");
        WebElementActions.highlightElement(btNewContact, "yellow");
        js.executeScript("arguments[0].click()", btNewContact);
        LOG.info("Filling in the data for new contact is starting up ...");
        fillContact(mail);
        return FindElement.isElementPresent(titleNewContName);
    }

    public void deleteNewContact() {
        LOG.info("Deleting the created contact is starting up ...");
        WebElementActions.highlightElement(btDeleteContact, "yellow");
        js.executeScript("arguments[0].click()", btDeleteContact);
        WebElementActions.highlightElement(btConfirmationDelete, "yellow");
        js.executeScript("arguments[0].click()", btConfirmationDelete);
    }

    private void fillContact(Mail mail) {
        LOG.debug("Filling the Firstname...");
        WebElementActions.highlightElement(inpFirstname, "yellow");
        js.executeScript("arguments[0].value = 'Иван'", inpFirstname);
        LOG.debug("Filling the Lastname...");
        WebElementActions.highlightElement(inpLastname, "yellow");
        js.executeScript("arguments[0].value = 'Иванов'", inpLastname);
        LOG.debug("Filling the email...");
        WebElementActions.highlightElement(inpEmails, "yellow");
        js.executeScript("arguments[0].value = '" + mail.getReceiver() + "'", inpEmails);
        WebElementActions.highlightElement(btSaveContact, "yellow");
        js.executeScript("arguments[0].click()", btSaveContact);
    }

    @Override
    WebElement getIndicator() {
        return lnkAllContacts;
    }
}
