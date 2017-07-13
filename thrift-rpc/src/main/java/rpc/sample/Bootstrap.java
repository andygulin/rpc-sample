package rpc.sample;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import rpc.sample.service.HelloService;
import rpc.sample.service.UserService;

import java.util.logging.Logger;

public class Bootstrap {

    private static final Logger logger = Logger.getLogger(Bootstrap.class.getName());

    private static final Injector injector;

    static {
        injector = Guice.createInjector(new ServiceModule());
    }

    private TServer server;
    private int port;

    public Bootstrap(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        try {
            new Bootstrap(Constants.PORT).start();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    private void start() throws TTransportException {
        this.stop();

        TServerSocket transport = new TServerSocket(this.port);

        TMultiplexedProcessor processor = new TMultiplexedProcessor();
        processor.registerProcessor(HelloService.class.getSimpleName(), new HelloService.Processor<>(injector.getInstance(HelloService.Iface.class)));
        processor.registerProcessor(UserService.class.getSimpleName(), new UserService.Processor<>(injector.getInstance(UserService.Iface.class)));

        TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(transport).processor(processor).protocolFactory(new TBinaryProtocol.Factory());

        this.server = new TThreadPoolServer(tArgs);
        new Thread(() -> {
            logger.info("Server Start Success , Port -> " + this.port);
            Bootstrap.this.server.serve();
        }).start();
    }

    private void stop() {
        if (this.server != null && this.server.isServing()) {
            this.server.stop();
        }
    }
}
