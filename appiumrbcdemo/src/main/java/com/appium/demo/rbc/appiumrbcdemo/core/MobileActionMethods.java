/*Author :  Venkatramana Reddy Araveeti
 * 
 */

package com.appium.demo.rbc.appiumrbcdemo.core;
import java.io.IOException;

import java.util.HashMap;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.MobileElement;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MobileActionMethods {
	
	//Creating the Generic Driver Instance.

	 private AppiumDriver<?> driver;
	 
	 protected static Logger log = Logger.getLogger(Log.class.getName());
	 public CustomAssersion assersion;

	
	 public MobileActionMethods(AppiumDriver<?> driver) {
	        this.driver = driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	         assersion = new CustomAssersion(driver);
	    }

	private static final TimeUnit SECONDS = null;
	
	
		public boolean click(MobileElement objElement)
			throws InterruptedException, IOException, NoSuchElementException {
			
		boolean isVerify = false;
		try {
				waitForElementClickable(objElement);
				objElement.click();
				log.info("The element" + objElement + " Has been clicked successfully");
				isVerify = true;
		} catch (IllegalArgumentException e) {
			log.error(" Exception is thrown at run time");

		} catch (NoSuchElementException n) {
			log.error("No such Element Exception");

		} catch (Exception e) {
			log.error("An Exception is thrown at run time ");


		}
		return isVerify;
	}

	

	
	
	public boolean  sendKeys(MobileElement objElement, String sTextToSend)
			throws InterruptedException, IOException, TimeoutException {
		boolean isVerify = false;
		try {
			waitForElementClickable(objElement);
			objElement.clear();
			objElement.sendKeys(sTextToSend);
			log.info("The Text is entered Successfully");
			isVerify = true;
			
		} catch (IllegalArgumentException e) {
			log.error("An Exception is thrown at run time ");

			throw e;
		} catch (TimeoutException e) {
			throw e;
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return isVerify;
	}

	
	
	//Get the screen size
	 
	public int[] getScreenSize() throws IOException {
		try {
			Dimension size = ((AppiumDriver) driver).manage().window().getSize();
			int x = size.width;
			int y = size.height;
			int[] returnVal = { x, y };
			return returnVal;
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	
	//Closing  the App
	public boolean  closeApp() throws IOException {
		boolean isVerify = false;
		try {
			((AppiumDriver) driver).closeApp();
			log.info("Successfully closed the App ");
			isVerify = true;
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return isVerify;
	}

	
	
	
	//Getting the Page Source
	
public void getPageSource() throws InterruptedException {
	System.out.println(driver.getPageSource());
	
}

//Swipe using Java Script Executor

public void swipeJavaScript(MobileElement mobile) {
	JavascriptExecutor js= (JavascriptExecutor)driver;
	HashMap<String, String> swipeObject=new HashMap<String, String>();
	Dimension size;
	size = ((AppiumDriver) driver).manage().window().getSize();
	int starty = (int) (size.height * 0.80); 
	int endy = (int) (size.height * 0.20);
	int startx = size.width / 2;
	int endx=0;
	swipeObject.put("startX", String.valueOf(startx));
	swipeObject.put("startY", String.valueOf(starty));
	swipeObject.put("endX", String.valueOf(starty));
	swipeObject.put("endY", String.valueOf(endy));
	js.executeScript("mobile: scroll", swipeObject);
	
}

public boolean waitForElementClickable(MobileElement objElement) {
	boolean isVerify =  false;
	log.info("Waiting for the element  ::" + objElement);
	try {
	WebDriverWait wait = new WebDriverWait((AppiumDriver) driver, 10L);
	wait.until(ExpectedConditions.elementToBeClickable(objElement));
	isVerify = true;
	}
	catch(Exception e) {
		
		isVerify = false;
	}
	return isVerify;
	
	}

}
