package com.politrip.tests.extent_reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExtentTest {


    ExtentReports report;

    ExtentHtmlReporter htmlReporter;

    com.aventstack.extentreports.ExtentTest extentLogger;

    @BeforeMethod
    public void setup(){

        report = new ExtentReports();


        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/test-output/report.html";


        htmlReporter = new ExtentHtmlReporter(path);


        report.attachReporter(htmlReporter);


        htmlReporter.config().setReportName("Politrip Smoke Test");


        report.setSystemInfo("Environment","QA");

        report.setSystemInfo("OS",System.getProperty("os.name"));

    }

    @Test
    public void test1(){
        //give name to current test
        extentLogger= report.createTest("TC123 Login as user");

        //test steps
        extentLogger.info("Open Chrome Browser");

        extentLogger.info("Go to this URL");

        extentLogger.info("Enter username password");

        extentLogger.info("Click Login");

        extentLogger.info("Verify logged in");

        extentLogger.pass("TC123 is passed");

    }


    @AfterMethod
    public void teardown(){
        //this is when the report is actually created
        report.flush();

    }
}
