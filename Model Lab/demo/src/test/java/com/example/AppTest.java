package com.example;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest 
{
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
    @Test
    public void test2() throws Exception
    {
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter("C:\\Users\\demo\\que11\\que11\\que11\\labmodel\\report.html");
        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);
        test=extent.createTest("test1","test case 1");
        FileInputStream fs=new FileInputStream("C:\\Users\\demo\\que11\\que11\\que11\\labmodel\\que11.xlsx");
        XSSFWorkbook wb=new XSSFWorkbook(fs);
        XSSFSheet sheet=wb.getSheetAt(0);
        String row=sheet.getRow(0).getCell(0).getStringCellValue();
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://groww.in/");
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,4000)","");
        Thread.sleep(3000);
        driver.findElement(By.linkText("Calculators")).click();
        Thread.sleep(3000);
        WebElement cal=driver.findElement(By.xpath("//*[@id='root']/div[2]/h1"));
        String text=cal.getText();
        if(text.contains("Calculators"))
        System.out.println("Calculators found");
        else
        System.out.println("Calculators not found");
        File ss=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String spath="C:\\Users\\demo\\que11\\que11\\que11\\labmodel\\cal.png";
        FileUtils.copyFile(ss, new File(spath));
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0,1000)","");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='root']/div[2]/div[2]/a[15]/div/p[1]")).click();
        Thread.sleep(3000);
        WebElement loan=driver.findElement(By.xpath("//*[@id='LOAN_AMOUNT']"));
        loan.clear();
        loan.sendKeys(row);
        WebElement inte =driver.findElement(By.xpath("//*[@id='RATE_OF_INTEREST']"));
        inte.clear();
        inte.sendKeys("8");
        WebElement ten=driver.findElement(By.xpath("//*[@id='LOAN_TENURE']"));
        ten.clear();
        ten.sendKeys("25");
        wb.close();
    }
    @AfterMethod
    public void tearDown(ITestResult result) throws Exception
    { 
        if(result.getStatus()==ITestResult.FAILURE)
        {
            test.log(Status.FAIL, "Testcase Failed : "+result.getName());
            test.log(Status.FAIL, "Testcase Failed Reason: "+result.getThrowable());

            
            test.addScreenCaptureFromPath("C:\\Users\\demo\\que11\\que11\\que11\\labmodel\\cal.png");
        }
      else if (result.getStatus()==ITestResult.SUCCESS)
        { test.log(Status.PASS, "Test Case Passed: "+result.getName());
        }
      else if (result.getStatus()==ITestResult.SKIP)
        { test.log(Status.SKIP, "Test Case Skipped: "+result.getName());
        }
    }
}
