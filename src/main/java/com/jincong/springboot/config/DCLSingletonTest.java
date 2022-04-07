package com.jincong.springboot.config;

import com.jincong.springboot.domain.User;


/**
 * @author j_cong
 * @version V1.0
 * @date 2022/3/31
 */
public class DCLSingletonTest {

    private DCLSingletonTest() {
    }

    private static volatile User user;


    public static User getClient() {
        if (user == null) {
            synchronized (DCLSingletonTest.class) {
                if (user == null) {
                    user  = new User();
                }
            }
        }
        return user;
    }

}
