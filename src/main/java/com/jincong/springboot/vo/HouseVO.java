package com.jincong.springboot.vo;

import lombok.Data;

import java.util.List;

@Data
public class HouseVO {

    private String name;
    private List<CatVO> cats;
    private Integer age;

}
