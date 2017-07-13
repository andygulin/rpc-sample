package rpc.sample.service.handler;

import com.google.inject.Singleton;
import org.apache.thrift.TException;
import rpc.sample.service.HelloService;

@Singleton
public class HelloServiceHandler implements HelloService.Iface {

    @Override
    public String hello() throws TException {
        return "hello world";
    }

    @Override
    public String sayHello(String name) throws TException {
        return "hello : " + name;
    }
}