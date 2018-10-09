package com.jimingqiang.study.controller;

import com.jimingqiang.study.Bean.User;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by QDHL on 2018/10/9.
 * dev
 * @author mingqiang ji
 */
@RestController
@RequestMapping("/swaggerApi")
@Api(value = "SwaggerValue", tags={"SwaggerController"},description = "swagger应用",  produces = MediaType.APPLICATION_JSON_VALUE)
public class SwaggerController {






    @RequestMapping(value="/test", method= RequestMethod.GET)
    @ApiOperation(value="获取swagger信息",httpMethod = "GET",notes="注意问题点",produces = MediaType.APPLICATION_JSON_VALUE)
    public String swaggerTest(@ApiParam(name="id",value="用户id",required=true) Long id){

        return "swagger"+id;

    }

    @RequestMapping(value="/apiModeltest", method= RequestMethod.GET)
    @ApiOperation(value="获取ApiModel信息",httpMethod = "GET",notes="ApiModel注意问题点",produces = MediaType.APPLICATION_JSON_VALUE)
    public String swaggerTest1(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) User user ){

        return user.toString();

    }

    @RequestMapping(value="/apiImplicitParamsTest", method= RequestMethod.GET)
    @ApiOperation(value="获取apiImplicitParams信息",httpMethod = "GET",notes="apiImplicitParams注意问题点",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名字", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "用户id", required = false, dataType = "long", paramType = "query")})
    public String apiImplicitParamsTest(Long id,String name ){

        return name;

    }

}
