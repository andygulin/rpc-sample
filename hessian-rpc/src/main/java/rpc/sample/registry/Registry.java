package rpc.sample.registry;

import org.apache.log4j.Logger;
import rpc.sample.route.Route;

import java.util.List;

public interface Registry {

    Logger logger = Logger.getLogger(Registry.class);

    void register(List<String> urls);

    List<String> getAll();

    default String get(Route rule) {
        String url = rule.select(getAll());
        logger.info("rule : " + rule + " , url : " + url);
        return url;
    }
}