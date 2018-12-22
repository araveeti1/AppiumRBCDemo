package com.appium.demo.rbc.appiumrbcdemo.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ShopByCategory {
	
	 private AppiumDriver<AndroidElement> driver;
	 
	    
	    //Initilize the Elements using Page Factory
	    
	    public ShopByCategory(AndroidDriver<AndroidElement> driver) {
	        this.driver = driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	    }
	    
	    @AndroidFindBy(xpath = "//android.view.View[@text='Kindle E-Readers & eBooks']")
	    private AndroidElement btnKindle;
	    
	   
	    @AndroidFindBy(xpath = "//android.view.View[@text='Kindle E-Readers']")
	    private AndroidElement btnEreader;
	    
	    @AndroidFindBy(xpath = "//android.view.View[@text='Results in Kindle E-readers']")
	    private AndroidElement labelResults;
	    
	    @AndroidFindBy(xpath = "//android.view.View[contains(@text, 'Display')]")
	    private String itemsKindle;
	    
	    
	    
	    public boolean navigateToShopByKindle() {
	    	
	    	boolean blnVerify = false;
	    	try {
	    		btnKindle.click();
	    		btnEreader.click();
	    		blnVerify = true;
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		
	    	}
	    	return blnVerify;
	    }
	    
	    
	    
	
	    public boolean verifyKindleItems() {
	    	
	    	boolean blnVerify = false;
	    	try {
	    		Assert.assertNotEquals(driver.findElements(By.xpath("//android.view.View[contains(@text, 'Display')]")).size(), 0, "The Kindle Items are not present in the List");
	    		blnVerify = true;
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		
	    	}
	    	return blnVerify;
	    }
 
	   
}

