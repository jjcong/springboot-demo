package com.jincong.springboot.test.programmatic.bean;

public abstract class Animal {

    protected Person person;

    protected String name;


    public Animal() {
        System.out.println("Animal constructor run ......");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "person=" + person +
                ", name='" + name + '\'' +
                '}';
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
