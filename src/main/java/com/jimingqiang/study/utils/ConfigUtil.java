package com.jimingqiang.study.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Description：
 * Created by Mars on 2017/4/11.
 */
public class ConfigUtil {
    public static Properties prop = null;

    public static final String CONFIGUSERID = "1099100700000000002";
    static {
        try {
            Resource resource = new ClassPathResource("/conf.properties");

            InputStream in = resource.getInputStream();
            prop = new Properties();
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProp(String key,String defaultValue) {
        return prop.getProperty(key) == null ? defaultValue : prop.getProperty(key);
    }
}
