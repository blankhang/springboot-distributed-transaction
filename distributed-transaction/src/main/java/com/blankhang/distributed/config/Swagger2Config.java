package com.blankhang.distributed.config;

import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 配置
 *
 * @author blank
 * @since 2020-3-3 下午 12:15
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "spring.swagger")
@EnableSwagger2
public class Swagger2Config {

    private Boolean enabled;

    private String title;

    private String description;

    private String version;

    private String termsOfServiceUrl;

    private String name;

    private String url;

    private String email;

    @Bean
    public Docket createRestApi() {

        log.info("加载Swagger2");

//        //添加header参数start
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<>();
//        tokenPar.name(tokenHeader).description("token")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .defaultValue(tokenStartWith + " ")
//                .required(true)
//                .build();
//        pars.add(tokenPar.build());
//        //添加header参数end

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()

                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(name, url, email))
                .version(version)
                .build();
    }

}
