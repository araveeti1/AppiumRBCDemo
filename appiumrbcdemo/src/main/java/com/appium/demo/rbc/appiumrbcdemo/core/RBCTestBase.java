/*Author :  Venkatramana Reddy Araveeti
 * 
 */
package com.appium.demo.rbc.appiumrbcdemo.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.appium.demo.rbc.appiumrbcdemo.core.CustomAssersion;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class RBCTestBase {
	
	protected static AppiumDriver<?> driver;
	
	public static String outPutDirectory = null;
	
	public CustomAssersion assersion;
	
	// ATUTestRecorder recorder;

	
	//Change the Default Output Folder - The Output folder will be created here....
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
	
	//Make sure this always runs successfully

	@BeforeTest(alwaysRun = true)
    @Parameters( { "platform", "udid", "platformVersion"})
	public void beforeTest(String platform, String udid, String platformVersion) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);


        switch (platform.toLowerCase()) {
        
            case "ios":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability(MobileCapabilityType.APP, new File("Path to the ipa file").getAbsolutePath());

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
        		assersion = new CustomAssersion(driver);

                break;

            default:
                throw new Exception("unknown platform name is Given and the test Fails.Please check the platform name in the testng.xml file for the same.");
        }
    }

	
	//Quit the Appium After the Test
	@AfterTest
	 public void End() throws IOException, ATUTestRecorderException {
	  driver.quit();
	  // Stop appium server when test Is ended.
	  
	  // Stop video recording.
	//  recorder.stop();
	 }


}
