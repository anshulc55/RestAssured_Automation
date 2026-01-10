package tests;

import java.util.UUID;

import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

// Foundation Layer of All Test Classes
public class BaseTest {
	
	@BeforeClass
	public void beforeAllTests() {
        // Future-ready hooks:
        // - Logging
        // - Reporting
        // - AI context setup
		System.out.println("=== Test Execution Started ===");
	}
	
	@AfterClass
	public void afterAllTests() {
		System.out.println("=== Test Execution Finished ===");
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		
		// Generate unique Correlation ID per test
		String correlationId = UUID.randomUUID().toString();
		ThreadContext.put("correlationId", correlationId);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		
		// Clear context to avoid leakage in parallel runs
		ThreadContext.clearAll();
	}

}
