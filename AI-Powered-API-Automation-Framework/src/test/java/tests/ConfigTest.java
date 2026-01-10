package tests;

import org.testng.annotations.Test;

import config.ConfigManager;

public class ConfigTest extends BaseTest {

	@Test
	public void verifyConfigurationLoaded() {
		ConfigManager config = ConfigManager.getInstance();

		System.out.println("Base URL: " + config.getBaseUrl());
		System.out.println("Timeout: " + config.getTimeout());
	}

}
