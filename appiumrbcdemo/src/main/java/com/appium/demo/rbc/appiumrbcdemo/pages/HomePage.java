package com.appium.demo.rbc.appiumrbcdemo.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	 private AppiumDriver<AndroidElement> driver;
	 
	    
	    //Initilize the Elements using Page Factory
	    
	    public HomePage(AndroidDriver<AndroidElement> driver) {
	        this.driver = driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	    }
	    
	    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/action_bar_burger_icon")
	    private AndroidElement btnHamburger;
	    
	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Shop by Category']")
	    private AndroidElement btnShopBy;
	    
	   
	    
	    public boolean navigateToShopByCategory() {
	    	
	    	boolean blnVerify = false;
	    	try {
	    		btnHamburger.click();
	    		btnShopBy.click();
	    		blnVerify = true;
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		
	    	}
	    	return blnVerify;
	    }
	    
	
	    public boolean waitForElement() {
	    	boolean isVerify  = false;
	    	try {
	    	WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOf(btnShopBy));
	        isVerify =  true;
	    	}
	    	catch(Exception e) {
	    		e.getMessage();
	    		
	    	}
	    	return isVerify;
	    }
	   
}

