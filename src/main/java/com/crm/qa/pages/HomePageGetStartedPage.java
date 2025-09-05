package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePageGetStartedPage extends TestBase {

    // after login we are opeing this page (in loginpage we have added (return new HomePageGetStarted();)

    @FindBy(xpath = "//a[@aria-label=\"Get started with VWO\"]")
    WebElement getStartedLabel;

    @FindBy(xpath = "//ul[@class=\"tabs-menu\"]//a")
    List<WebElement> listMenu;

    @FindBy(xpath = "//a[@aria-label=\"View campaigns overview\"]")
    WebElement ViewCampaignsOverview;

    @FindBy(xpath = "//a[@aria-label=\"View dashboard\"]")
    WebElement ViewDashboard;


    public HomePageGetStartedPage(){
        PageFactory.initElements(driver,this);
    }

    public String verifyHomePageTitle(){
        return driver.getTitle();
    }
    public void verifyAllMenuTabs() {
        System.out.println("hellooooooooo" +listMenu.toString());
        for (WebElement menu : listMenu) {
            System.out.println("web ele text >>>>>>>"+menu.getText());
//            if(menu.getText().equals(ViewDashboard)){
//                menu.click();
//            }
        }
//        return new DashboardPage();
    }

    public boolean verifyHomePageLabel(){
        return getStartedLabel.isDisplayed();
    }
    public CampaignsOverviewPage clickOnCampaignsOverview(){
        ViewCampaignsOverview.click();
        return new CampaignsOverviewPage();
    }
    public DashboardPage clickOnDashboard() throws InterruptedException {
        Thread.sleep(3000);
        ViewDashboard.click();
        return new DashboardPage();
    }





}
