package com.jimingqiang.study.canal.utils;

/**
 * Created by wanglei on 28/03/2017.
 */
public class Constant {
    public static final String MAIL_ADDRESS = ConfigUtils.getProp("mail_address");
    public static final String MAIL_SERVER_HOST = ConfigUtils.getProp("mail_server_host");
    public static final String MAIL_USERNAME = ConfigUtils.getProp("mail_username");
    public static final String MAIL_PASSWORD = ConfigUtils.getProp("mail_password");
    public static final String MAIL_FROM_ADDRESS = ConfigUtils.getProp("mail_from_address");
    public static final String MAIL_ENVIROMENT = ConfigUtils.getProp("mail_enviroment");
    public static String tables = ConfigUtils.getProp("canal.table");

}
