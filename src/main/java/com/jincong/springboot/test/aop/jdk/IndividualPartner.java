package com.jincong.springboot.test.aop.jdk;

import com.jincong.springboot.test.aop.basic.Player;

/**
 * IndividualPartner 个人陪玩者
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/12/6
 */
public class IndividualPartner implements Partner {
    private String name;

    public IndividualPartner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void receiveMoney(int money) {
        System.out.println(name + "收到佣金：" + money + "元 ~ ");
    }

    @Override
    public void playWith(Player player) {
        System.out.println(name + "与" + player.getName() + "一起愉快地玩耍 ~ ");
    }
}
