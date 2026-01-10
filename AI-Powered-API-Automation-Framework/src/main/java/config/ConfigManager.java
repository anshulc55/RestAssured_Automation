package config;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigManager {
	private static ConfigManager instance;
	private Properties properties;

	private static final String CONFIG_PATH = "src/test/resources/config/";

	private ConfigManager() {
		loadProperties();
	}

	public static ConfigManager getInstance() {
		if (instance == null) {
			synchronized (ConfigManager.class) {
				if (instance == null) {
					instance = new ConfigManager();
				}
			}
		}
		return instance;
	}

	private void loadProperties() {
		properties = new Properties();

		String env = System.getProperty("env", "dev");
		String filePath = CONFIG_PATH + env + ".properties";

		try (FileInputStream fis = new FileInputStream(filePath)) {
			properties.load(fis);
			System.out.println("Loaded config for environment: " + env);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load configuration file: " + filePath, e);
		}

	}

	public String getBaseUrl() {
		return properties.getProperty("base.url");
	}

	public int getTimeout() {
		return Integer.parseInt(properties.getProperty("timeout"));
	}

}
