package com.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {

    public Properties getProperties(String propsPath) throws IOException {
        File propsFile = new File(propsPath);
        FileInputStream inputStream = new FileInputStream(propsFile);
        Properties props = new Properties();
        props.load(inputStream);
        inputStream.close();
        return props;
    }

    public String getProperty(String key) throws IOException {
        String propsPath = System.getProperty("user.dir") + File.separator + "Properties" + File.separator + "url.properties";
        return getProperties(propsPath).getProperty(key);
    }

    public void setProperty(String key, String value) throws IOException {
        String propsPath = System.getProperty("user.dir") + File.separator + "Properties" + File.separator + "url.properties";
        getProperties(propsPath).setProperty(key, value);
    }

    public String getProperty(String propsPath, String key) throws IOException {
        return getProperties(propsPath).getProperty(key);
    }

}
