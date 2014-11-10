package com.ash.statuscheck.log4j.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Category;
import org.apache.log4j.PropertyConfigurator;


public class LoadLog4J {
	
	static final Category logger = Category.getInstance(LoadLog4J.class);
	
//	private void initializeLogger()
//	  {
//	    Properties logProperties = new Properties();
//	    final String LOG_PROPERTIES_FILE = "resource/log4j.properties";
//	    
//	    try
//	    {
//	      // load our log4j properties / configuration file
//	      logProperties.load(new FileInputStream(LOG_PROPERTIES_FILE));
//	      PropertyConfigurator.configure(logProperties);
//	      logger.info("Logging initialized.");
//	    }
//	    catch(IOException e)
//	    {
//	      throw new RuntimeException("Unable to load logging property " + LOG_PROPERTIES_FILE);
//	    }
//	  }

}
