package rpc.sample;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rpc.sample.service.HelloService;
import rpc.sample.service.UserService;

import java.util.logging.Logger;

public class ThriftClient {

    private static final Logger logger = Logger.getLogger(ThriftClient.class.getName());

    private TTransport transport;
    private TProtocol protocol;

    @Before
    public void init() throws TTransportException {
        transport = new TSocket("localhost", Constants.PORT);
        transport.open();

        protocol = new TBinaryProtocol(transport);
    }

    @Test
    public void hello() throws TException {
        HelloService.Client helloService = new HelloService.Client(new TMultiplexedProtocol(protocol, HelloService.class.getSimpleName()));
        logger.info(helloService.hello());
        logger.info(helloService.sayHello("andy"));
    }

    @Test
    public void user() throws TException {
        UserService.Client userService = new UserService.Client(new TMultiplexedProtocol(protocol, UserService.class.getSimpleName()));
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().equals("__isset_bitfield");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();
        logger.info(gson.toJson(userService.getAll()));
        logger.info(gson.toJson(userService.getUser(2)));
    }

    @After
    public void close() {
        transport.close();
    }
}