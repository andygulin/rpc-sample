package rpc.sample.service.impl;

import org.apache.commons.lang3.RandomUtils;
import rpc.sample.bean.User;
import rpc.sample.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private static final List<User> users = new ArrayList<>();

    static {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setId(i + 1);
            user.setName(UUID.randomUUID().toString().split("-")[0]);
            user.setAge(RandomUtils.nextInt(10, 100));
            user.setAddress(UUID.randomUUID().toString().replace("-", ""));
            user.setCreatedAt(new Date());
            users.add(user);
        }
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
