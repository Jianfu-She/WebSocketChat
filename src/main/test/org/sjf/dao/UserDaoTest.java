package org.sjf.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by SJF on 2016/10/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Test
    public void selectPasswordByUsername() throws Exception {
        String username = "Lilith";
        String password = userDao.selectPasswordByUsername(username);
        if (password != null) {
            System.out.println(password);
        } else {
            System.out.println("NULL");
        }
    }

    @Test
    public void selctIdByUsername() throws Exception {
        String username = "Lilith";
        int id = userDao.selctIdByUsername(username);
        System.out.println("id:" + id);
    }

}