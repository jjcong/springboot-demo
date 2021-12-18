package com.jincong.springboot.service.impl;

import com.jincong.springboot.service.Parent;
import org.springframework.stereotype.Service;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/12/14
 */
@Service
public class Son extends Parent {
    @Override
    protected String hello() {
     return "I am son";
    }

    @Override
    public String getType() {
        return "son";
    }
}
