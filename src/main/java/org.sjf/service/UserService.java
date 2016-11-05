package org.sjf.service;

/**
 * Created by SJF on 2016/10/31.
 */
public interface UserService {
    boolean isPassword(String username, String password);
    int getId(String username);
}
