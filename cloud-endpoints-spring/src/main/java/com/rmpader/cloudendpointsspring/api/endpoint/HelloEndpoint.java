package com.rmpader.cloudendpointsspring.api.endpoint;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.rmpader.cloudendpointsspring.api.resource.HelloResponse;
import com.rmpader.cloudendpointsspring.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Api(name = "sample",
     version = "1")
public class HelloEndpoint {

    @Autowired
    private HelloService helloService;

    @ApiMethod(name = "hello",
               path = "hello/{name}",
               httpMethod = ApiMethod.HttpMethod.GET)
    public HelloResponse hello(@Named("name") String name) {
        return helloService.sayHello(name);
    }

}
