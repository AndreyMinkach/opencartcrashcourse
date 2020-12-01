package com.opencart.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {

    protected static Properties properties;

    public static void loadProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\opencart.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}
