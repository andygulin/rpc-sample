package rpc.sample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rpc.sample.rmi.RmiServerConfig;

import java.io.IOException;

public class Server {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RmiServerConfig.class);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        context.close();
    }
}