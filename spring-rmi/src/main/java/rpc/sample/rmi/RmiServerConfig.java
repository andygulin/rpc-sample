package rpc.sample.rmi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import rpc.sample.service.HelloService;
import rpc.sample.service.UserService;
import rpc.sample.service.impl.HelloServiceImpl;
import rpc.sample.service.impl.UserServiceImpl;

import java.rmi.registry.Registry;

@Configuration
public class RmiServerConfig {

    @Bean
    public RmiServiceExporter userServiceExporter() {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName(UserService.class.getSimpleName());
        exporter.setService(userService());
        exporter.setServiceInterface(UserService.class);
        exporter.setRegistryPort(Registry.REGISTRY_PORT);
        return exporter;
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public RmiServiceExporter helloServiceExporter() {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName(HelloService.class.getSimpleName());
        exporter.setService(helloService());
        exporter.setServiceInterface(HelloService.class);
        exporter.setRegistryPort(Registry.REGISTRY_PORT);
        return exporter;
    }

    @Bean
    public HelloService helloService() {
        return new HelloServiceImpl();
    }
}