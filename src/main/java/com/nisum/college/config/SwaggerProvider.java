package com.nisum.college.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.nisum.college.bean.CollegeConstants.*;

@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {

    private static Logger logger = LoggerFactory.getLogger(RegisterResource.class);

    @Resource
    private InMemorySwaggerResourcesProvider inMemorySwaggerResourcesProvider;

    public List<SwaggerResource> get() {
        logger.debug("Swagger Created");
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setLocation(SWAGGER_JSON_LOCATION);
        swaggerResource.setSwaggerVersion(SWAGGER_VERSION);
        swaggerResource.setName(JAX_RS);

        return Stream.concat(Stream.of(swaggerResource),
                inMemorySwaggerResourcesProvider.get().stream()).collect(Collectors.toList());
    }
}
