package com.yushang.wallpaper.common.config.swagger2;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration   //通过@Configruation注解，让spring来加载该类的配置
//@EnableSwagger2  //通过@EnableSwagger2注解来启用swagger2.
public class SwaggerConfig {

    @Bean
    public Docket userDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("个人中心")       //团队名称
                .apiInfo(apiInfo()) //通过createRestApi函数创建Dock的bean之后，apiInfo（）用来创建该Api的基本信息（这些基本信息会展现    在文档页面中）
                .select()  //Select（）函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给swagger展现
                //设置basePackage会将包下的所有类的所有方法作为api
                //.api(RequestHandlerSelectos.basePackaage("com.example.demo2.controller")
                //只有标记了@ApiOperation才会暴露出给Swagger
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.regex("/weixin/user/.*"))   //过滤的接口
                .build();
                
                
    }

    @Bean
    public Docket shopDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("商城")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.regex("/weixin/shop/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        		.title("API接口文档")  //大标题
                .description("个人中心全部接口") //小标题
                .termsOfServiceUrl("http://127.0.0.1:8081/bicycle/swagger-ui.html") // 将“url”换成自己的ip:port
                .version("1.0.0") //版本
                .contact(new Contact("15100408798","https://app.swaggerhub.com/apis/mac15100408798", "15100408798@163.com"))  //作者 、网站链接、邮箱
                .build();
    }

}
