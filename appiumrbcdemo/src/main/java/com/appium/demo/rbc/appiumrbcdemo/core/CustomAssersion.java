package com.appium.demo.rbc.appiumrbcdemo.core;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//Designing the CustomAssersion by extending the Assersion for readability

public class CustomAssersion extends Assertion{
	
	 private AppiumDriver<?> driver;
	 
	    
	    //Initilize the Elements using Page Factory
	    
	    public CustomAssersion(AppiumDriver<?> driver) {
	        this.driver = driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	    }
	
		
		@Override
		public void onBeforeAssert(IAssert a) {
		System.out.println("The Expected Messsage is:"+a.getExpected());
		}

		
		//On Assert Failure it will take the screen shot
		@Override
		public void onAssertFailure(IAssert assertCommand) {
			System.out.println("The Expected Messsage is:"+assertCommand.getExpected()+" anf the Actual Message is +" +assertCommand.getActual());			
			takeScreenShot();
		}

		@Override
		public void onAssertSuccess(IAssert a) {
			System.out.println("The Expected Messsage is:"+a.getExpected()+" anf the Actual Message is +" +a.getActual());

	}
		
	
		public void takeScreenShot() {
			  String destDir = "screenshots";
			  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			SimpleDateFormat  dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
			  new File(destDir).mkdirs();
			  String destFile = dateFormat.format(new Date(0)) + ".png";
			  try {
			   FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
		}

}
