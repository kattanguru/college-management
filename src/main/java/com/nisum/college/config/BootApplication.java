package com.nisum.college.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.nisum.college.bean.CollegeConstants.BASE_PACKAGE;

@SpringBootApplication
@ComponentScan(BASE_PACKAGE)
@EnableSwagger2
@EnableCaching
@EnableScheduling
public class BootApplication extends SpringBootServletInitializer {

    private static Logger logger = LoggerFactory.getLogger(BootApplication.class);

    public static void main(String[] args) {
        logger.debug("College Management Spring Boot Application Started");
        SpringApplication.run(BootApplication.class, args);
    }

    @Bean
    public MapperFacade mapperFacade() {
        logger.debug("Orika mapper instantiated");
        return new DefaultMapperFactory.Builder().build().getMapperFacade();
    }
}