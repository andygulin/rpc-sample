package rpc.sample;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import rpc.sample.rmi.RmiClientConfig;
import rpc.sample.service.HelloService;
import rpc.sample.service.UserService;

@ContextConfiguration(classes = RmiClientConfig.class)
public class Client extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserService userService;
    @Autowired
    private HelloService helloService;

    @Test
    public void request() {
        System.out.println("userService.all() -> " + userService.all());
        System.out.println("userService.get(1) -> " + userService.get(1));
        System.out.println("helloService.hello() -> " + helloService.hello());
        System.out.println("helloService.sayHello() -> " + helloService.sayHello("Andy"));
    }
}
