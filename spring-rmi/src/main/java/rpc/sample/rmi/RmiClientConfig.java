package rpc.sample.rmi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import rpc.sample.service.HelloService;
import rpc.sample.service.UserService;

import java.rmi.registry.Registry;

@Configuration
public class RmiClientConfig {

    @Bean
    public RmiProxyFactoryBean userProxyFactoryBean() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceUrl("rmi://localhost:" + Registry.REGISTRY_PORT + "/" + UserService.class.getSimpleName());
        bean.setServiceInterface(UserService.class);
        return bean;
    }

    @Bean
    public RmiProxyFactoryBean helloProxyFactoryBean() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceUrl("rmi://localhost:" + Registry.REGISTRY_PORT + "/" + HelloService.class.getSimpleName());
        bean.setServiceInterface(HelloService.class);
        return bean;
    }
}
