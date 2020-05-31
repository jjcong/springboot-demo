package com.jincong.springboot.handler;

import com.jincong.springboot.domain.User;
import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/04/06
 */
public class MyEvent extends ApplicationEvent {

    private User user;

    public MyEvent(Object source, User user) {

        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
