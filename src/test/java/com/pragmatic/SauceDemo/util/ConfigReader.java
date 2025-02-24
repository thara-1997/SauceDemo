package com.pragmatic.SauceDemo.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.pragmatic.SauceDemo.util.LogManager.*;
public class ConfigReader {
    private static Properties properties;


    static {
        try {
               FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
               properties = new Properties();
               properties.load(fileInputStream);

            } catch (IOException e) {
                fatal("Configuration file read failure " + e.getMessage());
                throw new RuntimeException("Critical file loading failure. Aborting tests.");
            }
        }
      public static String getProperty(String key){
            return properties.getProperty(key);
        }
}
