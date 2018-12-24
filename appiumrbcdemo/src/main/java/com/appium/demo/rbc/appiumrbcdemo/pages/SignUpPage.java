/*Author :  Venkatramana Reddy Araveeti
 * 
 */
package com.appium.demo.rbc.appiumrbcdemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.appium.demo.rbc.appiumrbcdemo.core.CustomAssersion;
import com.appium.demo.rbc.appiumrbcdemo.core.MobileActionMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SignUpPage extends MobileActionMethods{
	
	//Creating the Generic Driver Instance.

	
	 private AppiumDriver<?> driver;
	 public CustomAssersion assersion;
	 
	 

	 public SignUpPage(AppiumDriver<?> driver) {
	    	super(driver);
	        this.driver = driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	         assersion = new CustomAssersion(driver);
	    }
	  
	 
	@AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/skip_sign_in_button")
	@iOSFindBy(xpath = "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")
	private MobileElement btnSkipSignin;
	 
	 
	 public boolean skipSignin() {
		 boolean blnVerify = false;
		 try {
			 click(btnSkipSignin);
			 log.info("Successfully clicked on the Skip Sign in Button");
		    assersion.takeScreenShot();

			 blnVerify = true;
		 }
		 catch(Exception e) {
			 e.printStackTrace();
			 log.error("Failed to click the Skip Sign in button");
	    	assersion.takeScreenShot();

		 }
		 return blnVerify;
	  }

}
