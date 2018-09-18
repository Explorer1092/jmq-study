package com.jimingqiang.study.response;

/**
 * Created by libo on 17-11-9.
 */
public enum HttpCodeEnum {
    SUCCESS(200, "成功"),
    PARAM_ERROR(400, "参数错误"),
    AUTH_ERROR(401, "没有权限"),
    MEDIA_ERROR(415, "权限不予许"),
    SERVER_ERROR(500, "后台错误"),
    UNKOWN_ERROR(700, "未知错误");
    private Integer code;
    private String desc;

    private HttpCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
