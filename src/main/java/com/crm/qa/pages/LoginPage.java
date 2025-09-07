package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// step 7 extends TestBase class by all the child clsses i.e pages
public class LoginPage extends TestBase {
    //Step 8 >>Page Factory - object REPO ::used to inilized the web elemant
    @FindBy(id = "login-username")
    @CacheLookup
    WebElement userName;
    @FindBy(id = "login-password")
    WebElement password;
    @FindBy(id = "js-login-btn")
    WebElement signInButton;
//    @FindBy(className = "text-link Td(n)")
//    WebElement Start_a_free_trial_link;
    @FindBy(id = "vow-login-logo")
    WebElement crmLogo;

    @FindBy(className = "notification-box-description")
    WebElement errorMessage;

    //step 9 initializing the page objects
    public LoginPage() {
        // used initElements method to inilizied the web elemwnt
        // this refer the currnt of the class
        PageFactory.initElements(driver, this);
    }

    // step 10 Actions
    @Step("Allure-Getting login page title step.....") // this is coming from allure
    public String validateLoginPageTitle() {
        return driver.getTitle();
    }

    @Step("Allure-check logo is displayed on login page step.....")
    public boolean validateCRMLOGOImage() {
        return crmLogo.isDisplayed();
    }

    @Step("Allure-login with username : {0} and password :{1} step.....")
    public HomePageGetStartedPage validateLogin(String un, String pwd) {
        userName.sendKeys(un);
        password.sendKeys(pwd);
        signInButton.click();

        // step 11 Here used return with new object of next page (wanted to land on new page )
        return new HomePageGetStartedPage();
    }
    @Step("Allure-login with invalid_username : {0} and invalid_password :{1} step.....")
    public void validateInvalidLogin(String un , String pwd){
        userName.sendKeys(un);
        password.sendKeys(pwd);
        signInButton.click();
    }
    @Step("Allure-Error Message After Invalid Login into app  step.....")
    public String validateErrorMessageAfterInvalidLogin(){

        return errorMessage.getText();
    }

}
