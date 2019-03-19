package com.jimingqiang.study;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

/**
 * Created by QDHL on 2018/10/9.
 *
 * @author mingqiang ji
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig  {

    /**
     如果项目配置tomcat访问路径，例如qdp-wain-web这样，需要配置下面的pathProvider方法，
     未配置访问路径，则忽略pathProvider方法和HOST配置yyyyyyyyy
     **/
    @Value("${spring.swagger.host}")
    private String Host;

    @Bean
    public Docket swaggerSpringMvcPlugin(ServletContext servletContext) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//生成文档的api对象定义
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jimingqiang.study"))//扫描生成文档的包路径
                .paths(PathSelectors.ant("/*Api/*"))//生成文档的类访问路径，就是controller类里@RequestMapping("orderApi")
                .paths(PathSelectors.any())
                .build();
                //.host(Host);//配置swagger前缀
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("JMQ-WEB构建restful接口api")//文档标题
                .description("此API提供接口调用")//文档说明
                .version("2.0").build();//版本号
    }



}
