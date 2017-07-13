package rpc.sample.service;

import rpc.sample.bean.User;

import java.util.List;

public interface UserService {

    List<User> all();

    User get(Integer id);
}
