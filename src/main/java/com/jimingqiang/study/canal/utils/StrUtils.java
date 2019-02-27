package com.jimingqiang.study.canal.utils;

/**
 * Created by wanglei on 30/03/2017.
 */
public class StrUtils {
    private final static String PATTERN ="[^0-9a-zA-Z\\u4e00-\\u9fa5.，,。_—？“”@/（）-]+";

    /*public static String replaceSpecialStr(String source){
        if(StringUtils.isEmpty(source)){
            return source;
        }
        return source.replaceAll(PATTERN,"");
    }*/

    /**
     * 转义普通SQL中的特殊字符'''、'\'
     * @return
     */
    public static String escapeSQL(String source){
        return source.replaceAll("'", "''").replace("\\","\\\\");
    }


    public static void main(String[] args) {
        Runtime runtime =  Runtime.getRuntime();
        System.out.println(runtime.freeMemory());
        System.out.println(runtime.maxMemory());
        System.out.println(runtime.totalMemory());
    }

}


