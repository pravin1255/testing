package com.demo.cucumberTest;

import java.io.File;
import java.io.IOException;

import com.demo.Utility.UIMapper;
import cucumber.api.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;


import cucumber.api.junit.Cucumber;
/*
 * This Cucumber options generates the report in target/cucumber-reports/report.html folder
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features="Feature/LogIn_Test.feature",
		glue={"com.demo.stepDefinition"},
		format={"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html", "pretty"},
		monochrome=true,
		tags={"@Policy","~@changePolicyAmt","~@RegressionTest","~@BuyNow","~@Gmailapproval","~@BuyNow","~@FileUpload"}
		)
public class TestRunner {	
	
	@AfterClass
	public static void writeExtentReport() throws IOException
	{
		
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir")
				+ UIMapper.getValue("reportConfigPath2")));

		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", "Windows 10" + "32 Bit");
		Reporter.setSystemInfo("Selenium", "3.7.0");
		Reporter.setSystemInfo("Maven", "3.5.2");	
		Reporter.setSystemInfo("Java Version", "1.8.0_151");
		Reporter.setTestRunnerOutput("Cucumber JUnit Test Runner");
	}
}