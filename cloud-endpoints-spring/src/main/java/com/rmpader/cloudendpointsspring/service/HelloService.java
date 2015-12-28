package com.rmpader.cloudendpointsspring.service;

import com.rmpader.cloudendpointsspring.api.resource.HelloResponse;
import org.springframework.stereotype.Service;

/**
 * @author RMPader
 */
@Service
public class HelloService {

    public HelloResponse sayHello(String name) {
        HelloResponse response = new HelloResponse();
        response.setName(name);
        return response;
    }

}
