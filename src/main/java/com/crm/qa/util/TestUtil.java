package com.crm.qa.util;

import com.crm.qa.base.TestBase;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// step 4 crte TestUtil
public class TestUtil extends TestBase {
    // step 5 PAGE_LOAD_TIMEOUT,IMPLICIT_WAIT we can define these two here as well as config.properties
    public static long PAGE_LOAD_TIMEOUT =20;
    public static long IMPLICIT_WAIT =10;

    public static String TESTDATA_SHEET_PATH = "C:/Workspace/CRMTestAutomation/src/main/java/com/crm/qa/testdata/FacebookReg.xlsx";

    static Workbook book;
    static Sheet sheet;
    static JavascriptExecutor js;


    // switch iFrame
    public void switchToFrame(){

        driver.switchTo().frame("_vwo_communication_proxy");
    }

    // code for read the file and used it with @data provider using Object[][]
    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(TESTDATA_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        // System.out.println(sheet.getLastRowNum() + "--------" +
        // sheet.getRow(0).getLastCellNum());
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
                // System.out.println(data[i][k]);
            }
        }
        return data;
    }


    // code for taking ss
    public static void takeScreenShotAtTheEndOfTestFailed(String getMethodName){
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        try {
            File file = new File(currentDir+"/src/main/resources/Screenshots/failed/"+getMethodName+"_"+System.currentTimeMillis()+"_"+".jpg");
            if(file.exists()){
                file.delete();
            }
            FileUtils.copyFile(sourceFile,file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void takeScreenShotAtTheEndOfTestPassed(String getMethodName){
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       String currentDir = System.getProperty("user.dir");
        try {
            File file = new File(currentDir+"/src/main/resources/Screenshots/Passed/"+getMethodName+"_"+System.currentTimeMillis()+"_"+".jpg");
            if(file.exists()){
                file.delete();
            }
            FileUtils.copyFile(sourceFile,file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
