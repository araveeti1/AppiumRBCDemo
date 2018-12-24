/*Author :  Venkatramana Reddy Araveeti
 * 
 */
package com.appium.demo.rbc.appiumrbcdemo.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.appium.demo.rbc.appiumrbcdemo.core.CustomAssersion;
import com.appium.demo.rbc.appiumrbcdemo.core.MobileActionMethods;

public class ShopByCategory extends MobileActionMethods{
	
	 private AppiumDriver<?> driver;
	 public  CustomAssersion assersion;
	 
	    
	    //Initilize the Elements using Page Factory
	 
		//Creating the Generic Driver Instance.

	    
	    public ShopByCategory(AppiumDriver<?> driver) {
	    	super(driver);
	        this.driver = driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	         assersion = new CustomAssersion(driver);

	    }
	    
	    @AndroidFindBy(xpath = "//android.view.View[@text='Kindle E-Readers & eBooks']")
		@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")

	    private AndroidElement btnKindle;
	    
	   
	    @AndroidFindBy(xpath = "//android.view.View[@text='Kindle E-Readers']")
		@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")

	    private AndroidElement btnEreader;
	    
	    @AndroidFindBy(xpath = "//android.view.View[@text='Results in Kindle E-readers']")
		@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")

	    private AndroidElement labelResults;
	    
	    @AndroidFindBy(xpath = "//android.view.View[contains(@text, 'Display')]")
		@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")

	    private String itemsKindle;
	    
	    
	    
	    public ShopByCategory navigateToShopByKindle() {
	    	
	    	boolean blnVerify = false;
	    	try {
	    		click(btnKindle);
	    		click(btnEreader);
	    		log.info("Successfully Navigated to the Shop by Kindle Items");
	    		blnVerify = true;
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		assersion.takeScreenShot();

	    		
	    	}
	    	return this;
	    }
	    
	    
	    
	
	    public ShopByCategory verifyKindleItems() {
	    	
	    	boolean blnVerify = false;
	    	try {
	    		assersion.assertNotEquals(driver.findElements(By.xpath("//android.view.View[contains(@text, 'Display')]")).size(), 0, "The Kindle Items are not present in the List");
	    		log.info("Successfully Verified the presence of the Kindle Items on the amazon app and the test case is successful");
	    		closeApp();
	    		blnVerify = true;
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		assersion.takeScreenShot();
	    		log.error("Failed to verify the Kindle Items on the Amazon App and the Test Fails");
	    	}
	    	return this;
	    }
 
	   
}

