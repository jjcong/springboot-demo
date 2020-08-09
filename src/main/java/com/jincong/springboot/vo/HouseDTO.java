package com.jincong.springboot.vo;

import lombok.Data;

import java.util.List;

@Data
public class HouseDTO {

    private String name;
    private List<CatDTO> cats;
    private Integer age;

}
