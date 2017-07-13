package rpc.sample.service.handler;

import com.google.inject.Singleton;
import org.apache.thrift.TException;
import rpc.sample.service.User;
import rpc.sample.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class UserServiceHandler implements UserService.Iface {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "aa", 11, "shanghai", new Date().getTime()));
        users.add(new User(2, "bb", 12, "beijing", new Date().getTime()));
        users.add(new User(3, "cc", 13, "nanjing", new Date().getTime()));
        users.add(new User(4, "dd", 14, "xianggang", new Date().getTime()));
        users.add(new User(5, "ee", 15, "xiamen", new Date().getTime()));
    }

    @Override
    public User getUser(int id) throws TException {
        User user = null;
        for (User u : users) {
            if (u.getId() == id) {
                user = u;
                break;
            }
        }
        return user;
    }

    @Override
    public List<User> getAll() throws TException {
        return users;
    }
}