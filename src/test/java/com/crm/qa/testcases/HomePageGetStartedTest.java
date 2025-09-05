package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.listener.ScreenShotCustomListener;
import com.crm.qa.pages.DashboardPage;
import com.crm.qa.pages.HomePageGetStartedPage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenShotCustomListener.class)
public class HomePageGetStartedTest extends TestBase {
    LoginPage loginPage;
    HomePageGetStartedPage homePageGetStarted;
    DashboardPage dashboardPage;
    TestUtil testUtil;
    public HomePageGetStartedTest() {
        super();
    }
    @BeforeMethod
    public void setUp() throws InterruptedException {
        initialization();
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        homePageGetStarted = loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
    }

    @Test(priority = 1)
    public void verifyHomePageGetStartedTitleTest() {
        String homeTitle = homePageGetStarted.verifyHomePageTitle();
        Assert.assertEquals(homeTitle, "Dashboard", "Home page title not  matched");
    }

    @Test(priority = 2)
    public void verifyPageLabelTest() {
        boolean flag = homePageGetStarted.verifyHomePageLabel();
        Assert.assertTrue(flag);
    }

    @Test(priority = 3)
    public void verifyTheMenu() {
        homePageGetStarted.verifyAllMenuTabs();
    }

    @Test(priority = 4)
    public void verifyToClickOnDashBoard() throws InterruptedException {
        dashboardPage = homePageGetStarted.clickOnDashboard();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
