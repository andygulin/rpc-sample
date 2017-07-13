package rpc.sample;

import com.google.inject.AbstractModule;
import rpc.sample.service.HelloService;
import rpc.sample.service.UserService;
import rpc.sample.service.handler.HelloServiceHandler;
import rpc.sample.service.handler.UserServiceHandler;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HelloService.Iface.class).to(HelloServiceHandler.class);
        bind(UserService.Iface.class).to(UserServiceHandler.class);
    }
}