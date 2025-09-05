package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.listener.ScreenShotCustomListener;
import com.crm.qa.pages.DashboardPage;
import com.crm.qa.pages.HomePageGetStartedPage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(ScreenShotCustomListener.class)
public class DashBoardTest extends TestBase {
    LoginPage loginPage;
    HomePageGetStartedPage homePageGetStarted;
    DashboardPage dashboardPage;

    String sheetName= "RegTestData";
    public DashBoardTest(){

        super();
    }
    @BeforeMethod
    public void setUp() throws InterruptedException {
        initialization();
        loginPage = new LoginPage();
        dashboardPage= new DashboardPage();
        homePageGetStarted= new HomePageGetStartedPage();

        homePageGetStarted = loginPage.login(properties.getProperty("username"), properties.getProperty("password"));

       dashboardPage = homePageGetStarted.clickOnDashboard();
    }

    @Test
    public void verifyTheDashboardLabelTest(){
        System.out.println("HIIIIIIIIIIIIIIIIIIIIIIIIIIII");
        boolean flag = dashboardPage.checkDashboardLabel();
        Assert.assertTrue(flag);
    }
    @Test
    public void verifyTheUserName(){
        String daseBoardUsername = dashboardPage.checkUserName();
        Assert.assertEquals(daseBoardUsername,"sksksk ssss","username is not matched");
    }
    @Test
    public void verifyTheMoveToDashBoreMenu(){
        dashboardPage.clickOnNewMoveDashBoard();
    }

//    @DataProvider
//    public Object[][] getCRMTestDATA(){
//      Object  data [][] = TestUtil.getTestData(sheetName);
//      return data;
//    }
//    @Test(dataProvider = "getCRMTestDATA")
//        public void data(String xyz,String abc){
//        dashboardPage.checkUserName(xyz,abc);
//    }



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }






}
