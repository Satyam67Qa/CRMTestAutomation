package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends TestBase {

    @FindBy(css = "[data-qa='page-heading']")
    WebElement dashboard;

    @FindBy(css= "[data-qa='lufexuloga']")
    WebElement userName;

    @FindBy(css = "[data-qa='nav-main-home']")
    WebElement moveDashboard;

    @FindBy(className = "navbar-btn-label")
    WebElement moveDashboardTesting;


    public DashboardPage(){
        PageFactory.initElements(driver,this);
    }
    public boolean checkDashboardLabel(){
       return dashboard.isDisplayed();
    }
    public String checkUserName(){
       return userName.getText();
    }
    public void clickOnNewMoveDashBoard(){
        Actions actions = new Actions(driver);
        actions.moveToElement(moveDashboard).build().perform();
        moveDashboardTesting.click();
    }

}
