package com.jimingqiang.study.Bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by QDHL on 2018/9/25.
 *
 * @author mingqiang ji
 */
@Data
@ApiModel(value="user对象",description="用户对象user")
public class User implements Serializable {
    @ApiModelProperty(value="用户名",name="username",required = true,example="xingguo")
    private String name;

    @ApiModelProperty(value="用户年龄",name="age",required = true,example="24")
    private int age;

}
