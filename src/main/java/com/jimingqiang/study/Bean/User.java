package com.jimingqiang.study.Bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by QDHL on 2018/9/25.
 *
 * @author mingqiang ji
 */
@Data
public class User implements Serializable {

    private String name;

    private int age;

}
