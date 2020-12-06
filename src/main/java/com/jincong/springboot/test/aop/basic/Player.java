package com.jincong.springboot.test.aop.basic;

/**
 * Player 玩家
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/12/6
 */
public class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
