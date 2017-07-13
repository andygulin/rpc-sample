package rpc.sample.service.impl;


import rpc.sample.bean.User;
import rpc.sample.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "小明", 11, "上海", new Date()));
        users.add(new User(2, "小红", 12, "北京", new Date()));
        users.add(new User(3, "小王", 13, "南京", new Date()));
        users.add(new User(4, "小林", 14, "香港", new Date()));
        users.add(new User(5, "小顾", 14, "厦门", new Date()));
        users.add(new User(6, "小做", 14, "广州", new Date()));
        users.add(new User(7, "小额", 14, "美国", new Date()));
        users.add(new User(8, "小哦", 14, "澳门", new Date()));
        users.add(new User(9, "小怕", 14, "广西", new Date()));
        users.add(new User(10, "小分", 14, "日本", new Date()));
    }

    @Override
    public List<User> all() {
        return users;
    }

    @Override
    public User get(Integer id) {
        User user = null;
        for (User u : users) {
            if (u.getId().intValue() == id.intValue()) {
                user = u;
                break;
            }
        }
        return user;
    }
}