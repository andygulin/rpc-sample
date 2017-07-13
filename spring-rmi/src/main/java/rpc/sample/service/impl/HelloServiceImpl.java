package rpc.sample.service.impl;

import org.springframework.stereotype.Service;
import rpc.sample.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {
        return "Hello";
    }

    @Override
    public String sayHello(String name) {
        return "Hello : " + name;
    }

}