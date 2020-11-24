package com.jincong.springboot.test.programmatic.bean;

public class Dog extends Animal {

    @Override
    public String toString() {
        return "Dog{" +
                "person=" + person +
                ", name='" + name + '\'' +
                '}';
    }
}
