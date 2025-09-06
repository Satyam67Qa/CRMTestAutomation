package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePageGetStartedPage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.listener.ScreenShotCustomListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

// step 12 crte test class for pge
@Listeners(ScreenShotCustomListener.class)
public class LoginPageTest extends TestBase {

    // step 15.b
    HomePageGetStartedPage homePageGetStarted;
    LoginPage loginPage;

    // Steps 13 crte cont of current class and add super
    public LoginPageTest() {
        // need to call parent class cont
        // so that initialization method m jo file h o null na issue de
        super();
    }

    // step 14
    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
    }

    @Test(priority = 1)
    public void loginPageTitleTest() {
        String title = loginPage.validateLoginPageTitle();
        Assert.assertEquals(title, "Login - VWO");
    }

    @Test(priority = 2)
    public void CRMLOGOImageTest() {
        boolean flag = loginPage.validateCRMLOGOImage();
        Assert.assertTrue(flag, "got the image successfully ");
    }

    @Test(priority = 3)
    public void loginTest() throws InterruptedException {
        // step 15.a
        homePageGetStarted = loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
        Thread.sleep(4000);
    }
    @Test(priority = 4)
    public void invalidLoginTest() throws InterruptedException {
         loginPage.unableTologin(properties.getProperty("invalid_username"), properties.getProperty("invalid_password"));
         Thread.sleep(3000);
        String unableToLoginErrorMessage = loginPage.errorMessageAfterInvalidLogin();
        System.out.println(unableToLoginErrorMessage);
         Assert.assertEquals(unableToLoginErrorMessage,properties.getProperty("expected_error"),"Error message not matched");
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }

}
