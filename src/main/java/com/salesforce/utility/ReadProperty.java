package com.salesforce.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty {

	public static String readProperty(String pname) {
		File file = new File(Constants.PROP_PATH);
		Properties p = new Properties();
		FileInputStream fs;
		try {
			fs = new FileInputStream(file);
			p.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(pname);
	}
}
