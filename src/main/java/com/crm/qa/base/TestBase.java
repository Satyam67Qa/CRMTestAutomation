package com.crm.qa.base;

import com.crm.qa.util.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

// step 1
public class TestBase {

    // step 3 created static so we can use inside the same class and child class (public) use any whwre
    // and (static) one copy only

    public static WebDriver driver;
    public static Properties properties;




    // step 2 create cont and in cont add file
    public TestBase() {
        try {
            properties = new Properties();
            System.out.println(System.getProperty("user.dir"));
            String fileName = System.getProperty("user.dir") + "/src/main/java/com/crm/qa/config/config.properties";
            System.out.println(fileName);
            properties.load(new FileInputStream(fileName));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    // step 4 create the initialization method , for browser
    public static void initialization() {
        //comming from the properties
        String browserName = properties.getProperty("browser");
        // launch the browser
        if (browserName.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        }


        // Now create object of EventListerHandler to register it with EventFiringWebDriver


        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
      //  driver.manage().timeouts().pageLoadTimeout(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.get(properties.getProperty("url"));

        // step 6 testbase completed

    }


}
