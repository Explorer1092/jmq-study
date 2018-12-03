package com.jimingqiang.study.canal.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by wanglei on 28/03/2017.
 */
public class ConfigUtils {

    public static Properties prop = null;
    static {
        InputStream in = null;
        try {
            Resource resource = new ClassPathResource("./conf.properties");
            in = resource.getInputStream();
            prop = new Properties();
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProp(String key) {
        return prop.getProperty(key);
    }
}

