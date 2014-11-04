package com.ash.statuscheck.driver;

import com.ash.statuscheck.checkstatus.dao.RegisterStatus;
import com.ash.statuscheck.mail.SendMail;
import com.ash.statuscheck.pojo.ProfileDetails;

public class StatusDriver {

	public static void main(String[] args) {
		int x = 0;
		while (x==0) {
			
			// Step1 Check Registration Status.
			System.out.println("Status Driver Started");
			
			
			RegisterStatus registerStatus = new RegisterStatus();
			ProfileDetails profileDetails = new ProfileDetails();
			
			registerStatus.registerStatusDetails(profileDetails);
			
		}
		
	}

}
