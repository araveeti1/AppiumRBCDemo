package com.appium.demo.rbc.appiumrbcdemo.pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignUpPage {
	
	 private AppiumDriver<AndroidElement> driver;

	 public SignUpPage(AndroidDriver<AndroidElement> driver) {
	        this.driver = driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	    }
	  
	 
	 @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/skip_sign_in_button")
	    private AndroidElement btnSkipSignin;
	 
	 
	 public boolean skipSignin() {
		 boolean blnVerify = false;
		 try {
			 btnSkipSignin.click();
			 blnVerify = true;
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 return blnVerify;
	  }

}
