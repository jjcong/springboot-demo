package com.jincong.springboot.test.postprocessor;

import org.springframework.stereotype.Component;

@Component
public class Cat1 {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat{" + "name='" + name + '\'' + '}';
    }
}
