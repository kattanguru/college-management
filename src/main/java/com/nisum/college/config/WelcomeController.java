package com.nisum.college.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WelcomeController extends WebMvcConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        logger.debug("Welcome Controller Mapping Completed ");
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
}
