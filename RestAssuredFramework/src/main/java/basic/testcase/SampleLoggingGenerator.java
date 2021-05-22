package basic.testcase;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import utility.RestFWLogger;

public class SampleLoggingGenerator {
	
	@Test
	public void REST_TEST01() {
		
		PropertyConfigurator.configure("log4j.properties");
		RestFWLogger.startTestCase("REST_TEST01");
		RestFWLogger.info("Creating REST Assured Object");
		RestFWLogger.info("Excel sheet opened");
		RestFWLogger.info("New Object instantiated");
		RestFWLogger.info("Implicit wait applied on the Test for 10 seconds");
		RestFWLogger.info("URI of application launched");
		RestFWLogger.info("Login Successfully, now it is the time to Log Off buddy.");
		RestFWLogger.info("Action is perfomred on Log Out link");
		RestFWLogger.info("Test Case Closed");
		RestFWLogger.endTestCase();
	}
	
	@Test
	public void REST_TEST02() {
		PropertyConfigurator.configure("log4j.properties");
		RestFWLogger.startTestCase("REST_TEST02");
		RestFWLogger.info("Creating REST Assured Object");
		RestFWLogger.info("Excel sheet opened");
		RestFWLogger.info("New Object instantiated");
		RestFWLogger.info("Implicit wait applied on the Test for 10 seconds");
		RestFWLogger.info("URI of application launched");
		RestFWLogger.endTestCase();
	}

}
