package org.sjf.service.impl;

import org.sjf.dao.UserDao;
import org.sjf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SJF on 2016/10/31.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean isPassword(String username, String password) {
        String realPassword = userDao.selectPasswordByUsername(username);
        return password.equals(realPassword);
    }

    @Override
    public int getId(String username) {
        return userDao.selctIdByUsername(username);
    }
}
