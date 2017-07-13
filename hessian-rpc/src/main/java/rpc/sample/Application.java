package rpc.sample;

import com.caucho.hessian.server.HessianServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import rpc.sample.service.UserService;
import rpc.sample.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        int URL_MAPPING_SIZE = 10;
        List<String> urlMappings = new ArrayList<>(URL_MAPPING_SIZE);
        for (int i = 1; i <= URL_MAPPING_SIZE; i++) {
            urlMappings.add("/hessian" + i);
        }
        ServletRegistrationBean bean = new ServletRegistrationBean(new HessianServlet(),
                urlMappings.toArray(new String[urlMappings.size()]));
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("home-api", UserService.class.getName());
        initParameters.put("home-class", UserServiceImpl.class.getName());
        bean.setInitParameters(initParameters);
        bean.setLoadOnStartup(1);
        return bean;
    }
}