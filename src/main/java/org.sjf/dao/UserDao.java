package org.sjf.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by SJF on 2016/10/31.
 */
public interface UserDao {

    int selctIdByUsername(@Param("username") String username);

    String selectPasswordByUsername(@Param("username") String username);
}
