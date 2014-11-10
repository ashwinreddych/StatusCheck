package com.ash.statuscheck.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.log4j.Category;
import org.apache.log4j.PropertyConfigurator;

import sun.util.logging.resources.logging;

import com.ash.statuscheck.checkstatus.dao.RegisterStatus;
import com.ash.statuscheck.mail.SendMail;
import com.ash.statuscheck.pojo.ProfileDetails;

public class StatusDriver {

	  static final Category logger = Category.getInstance(StatusDriver.class);
	  
	  
	
	
	
	public static void main(String[] args) {
		
		int x = 0;
		while (x==0) {
			x=1;
			
			// Step1 Check Registration Status.
			logger.info("Status Driver Started");
			
			RegisterStatus registerStatus = new RegisterStatus();
			ProfileDetails profileDetails = new ProfileDetails();
			
			registerStatus.sendConfirmationEmail(profileDetails);
			
			// Step 2 check for Registered Users.
			
		}
		
	}

}
