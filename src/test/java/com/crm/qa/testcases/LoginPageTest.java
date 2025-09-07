package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePageGetStartedPage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.listener.ScreenShotCustomListener;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(LoginPageTest.class);


    // Steps 13 crte cont of current class and add super
    public LoginPageTest() {
        // need to call parent class cont
        // so that initialization method m jo file h o null na issue de

        super();
        logger.info("loginfo>>It will go to the testBase");
        logger.info("loginfo>>back to login page");
    }

    // step 14
    @BeforeMethod
    public void setUp() {
        logger.info("loginfo>>>start BeforeMethod");
        logger.info("loginfo>>>>>>>>>>>>>>>>>>>>>>>Start Driver <<<<<<<<<<<<<<<<<<<<<<");
        initialization();
        logger.info("loginfo>>launch the browser");
        logger.info("loginfo>>entering application URL");
        loginPage = new LoginPage();
        logger.info("loginfo>>start login into the application");
    }

    @Test(priority = 1,description = "TestNG-Verifying the login page title")
    // coming form allure  @Story,@Description
    @Description("Allure-Test Case Description : Verify the login page title on login page")
    @Story("Allure-US ID (1) : To Check login page Title ")
    public void loginPageTitleTest() {
        logger.info("loginfo>>********************************* TestCase1 :: loginPageTitleTest {start}***************************");
        logger.info("loginfo>>********************************* loginPageTitleTest ***************************");
        String title = loginPage.validateLoginPageTitle();
        logger.info("loginfo>>loginPageTitle is >>>> " +title);
        Assert.assertEquals(title, "Login - VWO","Title is not matched");
        logger.info("loginfo>>********************************* TestCase1 :: loginPageTitleTest ***************************");
        logger.info("loginfo>>********************************* loginPageTitleTest {end}***************************");

    }

    @Test(priority = 2,description = "TestNG-Verifying the login page CRMLOGO")
    // coming form allure  @Story,@Description
    @Description("Allure-Test Case Description : Verify the login page CRMLogo is displayed on login page")
    @Story("Allure-US ID (2) : To Check login page CRMLOGO ")
    public void CRMLOGOImageTest() {
        logger.info("loginfo>>********************************* TestCase2 :: CRMLOGOImageTest {start}***************************");
        logger.info("loginfo>>********************************* CRMLOGOImageTest ***************************");
        boolean flag = loginPage.validateCRMLOGOImage();
        logger.info("loginfo>>loginPageLOGO is >>>> " +flag);
        Assert.assertTrue(flag, "got the image successfully ");
        logger.info("loginfo>>********************************* TestCase2 :: CRMLOGOImageTest ***************************");
        logger.info("loginfo>>********************************* CRMLOGOImageTest {end}***************************");
    }

    @Test(priority = 3,description = "TestNG-Verifying the login page validlogin")
    // coming form allure  @Story,@Description
    @Description("Allure-Test Case Description : Verify the login page userlogin with valid username,password on login page")
    @Story("Allure-US ID (3) : To Check login page user able to login ")
    public void validLoginTest() throws InterruptedException {
        logger.info("loginfo>>********************************* TestCase3 :: validLoginTest {start}***************************");
        logger.info("loginfo>>********************************* validLoginTest ***************************");
        // step 15.a
        logger.info("loginfo>>loginPage login with valid username and password is >>>> " +properties.getProperty("username"),properties.getProperty("password"));
        homePageGetStarted = loginPage.validateLogin(properties.getProperty("username"), properties.getProperty("password"));

        logger.info("loginfo>>********************************* TestCase3 :: validLoginTest {end}***************************");
        logger.info("loginfo>>********************************* validLoginTest ***************************");
        Thread.sleep(4000);
    }
    @Test(priority = 4,description = "TestNG-Verifying the login page invalidlogin")
    // coming form allure  @Story,@Description
    @Description("Allure-Test Case Description : Verify the login page userlogin with invalid username,password on login page")
    @Story("Allure-US ID (4) : To Check login page user unable to login ")
    public void invalidLoginTest() throws InterruptedException {
        logger.info("loginfo>>********************************* TestCase4 :: invalidLoginTest {start}***************************");
        logger.info("loginfo>>********************************* invalidLoginTest ************************************************");
        logger.info("loginfo>>loginPage login with invalid username and password is >>>> " +properties.getProperty("invalid_username"),properties.getProperty("invalid_password"));
        loginPage.validateInvalidLogin(properties.getProperty("invalid_username"), properties.getProperty("invalid_password"));
         Thread.sleep(3000);
        String unableToLoginErrorMessage = loginPage.validateErrorMessageAfterInvalidLogin();
        logger.info("loginfo>> checking the error message");
        System.out.println(unableToLoginErrorMessage);
        logger.info("loginfo>> found the error message"+unableToLoginErrorMessage);
         Assert.assertEquals(unableToLoginErrorMessage,properties.getProperty("expected_error"),"Error message not matched");

    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
        logger.info("loginfo>>>>>>>>>>>>>>>>>>>>>>>End Driver <<<<<<<<<<<<<<<<<<<<<<");
        logger.info("TearDown");
    }

}
