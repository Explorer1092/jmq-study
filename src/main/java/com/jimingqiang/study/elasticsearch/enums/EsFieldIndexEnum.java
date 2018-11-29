package com.jimingqiang.study.elasticsearch.enums;

/**
 * Es index
 * Created by libo on 2017/4/4.
 */
public enum EsFieldIndexEnum {
    NOT_ANALYZED("not_analyzed"),
    ANALYZED("analyzed"),
    NO("no");
    private String value;

    EsFieldIndexEnum(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
