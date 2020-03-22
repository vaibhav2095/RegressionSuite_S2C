package demo.survey;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ManageOR {
	Properties prop;

	public ManageOR() throws IOException {
		prop = new Properties();
		String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\objectRepo.properties";
		FileInputStream fis = new FileInputStream(filepath);
		prop.load(fis);
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}
}
