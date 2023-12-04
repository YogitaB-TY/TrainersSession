package genericLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilityMethods {
	
	
	
	public String getPropertyValue(String key) throws IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/config.properties");
		Properties properties=new Properties();
		properties.load(fis);
		/*
		 * getProperty(key) returns value based on the key passed
		 */
		return properties.getProperty(key);
	}

}
