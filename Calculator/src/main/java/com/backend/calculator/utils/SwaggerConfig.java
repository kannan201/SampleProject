package com.backend.calculator.utils;

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

/*
 * 
 * This class provides the required configurations to enable swagger2 to the application.
 *  Which will generate API documentation for the application.
 * 
 * 	@author  Kannan Maniyan
 * 	@version 1.0
 * 	@since   26-01-2019 
*/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.backend.calculator.controller"))
				.paths(PathSelectors.regex("/.*")).build().apiInfo(apiEndPointsInfo());
	}

	private ApiInfo apiEndPointsInfo() {

		return new ApiInfoBuilder().title("Spring Boot REST API").description("Calculator API")
				.termsOfServiceUrl("Terms of service").license("Licence of the API")
				.contact(new Contact("Kannan Maniyan", "", "kannan.201@gmail.com")).version("1.0.0")
				.build();
	}
}