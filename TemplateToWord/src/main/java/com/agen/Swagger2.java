package com.agen;

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

/**
 * 
 * <label>
 *		api配置类.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName Swagger2
 * @author tgj  
 * @date 2017年10月30日 下午1:13:11 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.agen.ttw.controller")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring Boot中使用Swagger2构建RESTful APIs")
				.description("目前仅用来测试")
				.contact(new Contact("TGJ", "https://www.baidu.com/", "tonggangjin2@163.com")).version("1.0")
				.build();
	}

}
