package com.yaroslav.crm;

import com.yaroslav.crm.dto.converter.CompanyToCompanyDtoConverter;
import com.yaroslav.crm.dto.converter.CompanyDtoToCompanyConverter;
import com.yaroslav.crm.dto.converter.EmployeeDtoToEmployeeConverter;
import com.yaroslav.crm.dto.converter.EmployeeToEmployeeDtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class CrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new CompanyDtoToCompanyConverter());
        modelMapper.addConverter(new CompanyToCompanyDtoConverter());
        modelMapper.addConverter(new EmployeeDtoToEmployeeConverter());
        modelMapper.addConverter(new EmployeeToEmployeeDtoConverter());

        return modelMapper;
    }

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfo("Mini-CRM REST API",
                "API created as test task to NGNWare company",
                "1.0", "",
                new Contact("Yaroslav Piskachov ", "", "yaroslav.piskachov@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo);
    }

}
