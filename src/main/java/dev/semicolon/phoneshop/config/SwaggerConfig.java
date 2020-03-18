package dev.semicolon.phoneshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
// TODO: Get values from configuration file(s) and POM
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("dev.semicolon.phoneshop"))
                    .paths(PathSelectors.any())
                    .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("João Dias", null, "jp.mm.dias@gmail.com");
        return new ApiInfo("Phone Shop", "Online shop selling smartphones", "0.1.0-SNAPSHOT",
                null, contact, null, null, Collections.emptyList());
    }

}
