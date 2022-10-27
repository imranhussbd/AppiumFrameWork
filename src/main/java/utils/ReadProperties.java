package utils;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

	private String path;
	private Properties properties;
	
	public ReadProperties(String constantType) {
		path = constantType;
		loadProperties();
	}
	
	private void loadProperties() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getKey(String key) {
		return properties.getProperty(key);
	}
}
