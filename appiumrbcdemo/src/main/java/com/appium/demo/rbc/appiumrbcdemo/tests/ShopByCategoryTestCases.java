/*Author :  Venkatramana Reddy Araveeti
 * 
 */
package com.appium.demo.rbc.appiumrbcdemo.tests;

import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.appium.demo.rbc.appiumrbcdemo.pages.HomePage;
import com.appium.demo.rbc.appiumrbcdemo.pages.ShopByCategory;
import com.appium.demo.rbc.appiumrbcdemo.pages.SignUpPage;

public class ShopByCategoryTestCases {
	
	private static AndroidDriver driver;

	
	@BeforeClass
	public void init() {
		DOMConfigurator.configure("log4j.xml");
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


}
