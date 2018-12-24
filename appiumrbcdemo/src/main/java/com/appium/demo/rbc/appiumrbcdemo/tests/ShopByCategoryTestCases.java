/*Author :  Venkatramana Reddy Araveeti
 * 
 */
package com.appium.demo.rbc.appiumrbcdemo.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.appium.demo.rbc.appiumrbcdemo.pages.HomePage;
import com.appium.demo.rbc.appiumrbcdemo.pages.ShopByCategory;
import com.appium.demo.rbc.appiumrbcdemo.pages.SignUpPage;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class ShopByCategoryTestCases {
	
	private static AppiumDriver driver;
	
	public static String outPutDirectory = null;
	
	// ATUTestRecorder recorder;

	
	@BeforeTest
	public void setup(ITestContext ctx) {
		DOMConfigurator.configure("log4j.xml");
		String baseDir = System.getProperty("user.dir");
		 Date date= new Date();
		 outPutDirectory = baseDir+File.separator+"testresults"+File.separator+ new Timestamp(date.getTime());
	    TestRunner runner = (TestRunner) ctx;
	    System.out.println("The output Directory is Given By ::" +outPutDirectory);
	    runner.setOutputDirectory(outPutDirectory);
	    //DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
	    //Date date1 = new Date();
	    // Created object of ATUTestRecorder.
	    // Provide path to store videos and file name format.
	    
	}

	
	@BeforeClass
	public void init() {
		//DOMConfigurator.configure("log4j.xml");
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/Apps/Amazon/");
		File app = new File(appDir, "Amazon India Online Shopping and Payments_v16.21.0.300_apkpure.com.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "Galaxy");
		capabilities.setCapability("platformVersion", "7.1.1");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		  

	}
	
	@Test
	public void verifyKindleItems() {
		
		SignUpPage signup = new SignUpPage(driver);
		HomePage homePage  = new HomePage(driver);
		ShopByCategory shopBy = new ShopByCategory(driver);
		
		signup.skipSignin();
		homePage.navigateToShopByCategory();
		shopBy.navigateToShopByKindle();
		shopBy.verifyKindleItems();	
		
	}

	@AfterTest
	 public void End() throws IOException, ATUTestRecorderException {
	  driver.quit();
	  // Stop appium server when test Is ended.
	  
	  // Stop video recording.
	//  recorder.stop();
	 }


}
