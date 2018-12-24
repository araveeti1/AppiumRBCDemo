/*Author :  Venkatramana Reddy Araveeti
 * 
 */
package com.appium.demo.rbc.appiumrbcdemo.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

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
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
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

	
	@BeforeSuite
	public void setup(ITestContext ctx) {
		DOMConfigurator.configure("log4j.xml");
		String baseDir = System.getProperty("user.dir");
		 Date date= new Date();
		 outPutDirectory = baseDir+File.separator+"testresults"+File.separator+ new Timestamp(date.getTime());
	    TestRunner runner = (TestRunner) ctx;
	    System.out.println("The output Directory is Given By ::" +outPutDirectory);
	    runner.setOutputDirectory(outPutDirectory);
	}

	@BeforeTest(alwaysRun = true)
    @Parameters( { "platform", "udid", "platformVersion"})
	public void beforeTest(String platform, String udid, String platformVersion) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);


        switch (platform.toLowerCase()) {
        
            case "ios":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);

                // if iOS 9+ use XCUITest
                    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                 capabilities.setCapability(MobileCapabilityType.APP, new File("Pathto the ipa file").getAbsolutePath());

                driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

                break;

            case "android":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                File classpathRoot = new File(System.getProperty("user.dir"));
        		File appDir = new File(classpathRoot, "/Apps/Amazon/");
        		File app = new File(appDir, "Amazon India Online Shopping and Payments_v16.21.0.300_apkpure.com.apk");
        		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
                capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

                driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

                break;

            default:
                throw new Exception("unknown platform name is Given and the test Fails.Please check the platform name in the testng.xml file for the same.");
        }
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
