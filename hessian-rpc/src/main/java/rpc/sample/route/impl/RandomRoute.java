package rpc.sample.route.impl;

import org.apache.commons.lang3.RandomUtils;
import rpc.sample.route.Route;

import java.util.List;

public class RandomRoute implements Route {

    @Override
    public String select(List<String> urls) {
        int idx = RandomUtils.nextInt(0, urls.size() - 1);
        String url = urls.get(idx);
        return url;
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}