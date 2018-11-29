package com.jimingqiang.study.elasticsearch.enums;

/**
 * Es属性类型
 * Created by libo on 2017/4/4.
 */
public enum EsFieldTypeEnum {
    String("string"),
    Date("date"),
    Integer("integer"),
    Double("double");
    private String value;

    EsFieldTypeEnum(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
