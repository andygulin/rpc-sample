package rpc.sample;

import com.caucho.hessian.client.HessianProxyFactory;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import rpc.sample.registry.Registry;
import rpc.sample.registry.impl.FileRegistry;
import rpc.sample.route.impl.RandomRoute;
import rpc.sample.service.UserService;

import java.net.MalformedURLException;

public class HessianTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    private static Logger logger = Logger.getLogger(HessianTest.class);
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    private UserService userService;

    @Before
    public void init() throws MalformedURLException {
        Registry registry = new FileRegistry();
        String url = registry.get(new RandomRoute());
        HessianProxyFactory factory = new HessianProxyFactory();
        factory.setOverloadEnabled(true);
        factory.setConnectTimeout(5000L);
        userService = (UserService) factory.create(UserService.class, url);
    }

    @Repeat(10)
    @Test
    public void all() {
        logger.info(userService.all());
    }

    @Repeat(10)
    @Test
    public void get() {
        logger.info(userService.get(1));
    }
}