package rpc.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import rpc.sample.registry.Registry;
import rpc.sample.registry.impl.FileRegistry;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@PropertySource("classpath:application.properties")
public class ApplicationStartupRunner implements CommandLineRunner {

    @Autowired
    private Environment env;
    @Autowired
    private ServletRegistrationBean servletRegistrationBean;

    @Override
    public void run(String... args) throws Exception {
        String port = env.getProperty("server.port");
        String contextPath = env.getProperty("server.context-path");
        String ip = InetAddress.getLocalHost().getHostAddress();
        final String serverUrlTemplate = "http://%s:%s%s%s";
        Collection<String> urlMappings = servletRegistrationBean.getUrlMappings();
        List<String> urls = new ArrayList<>();
        for (String urlMapping : urlMappings) {
            String url = String.format(serverUrlTemplate, ip, port, contextPath, urlMapping);
            urls.add(url);
        }
        Registry registry = new FileRegistry();
        registry.register(urls);
    }
}