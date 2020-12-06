package com.jincong.springboot.test.aop.basic;

/**
 * 陪玩类
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/12/6
 */
public class Partner {

    private String name;

    public Partner() {
    }

    public Partner(String name) {
        this.name = name;
    }


    /**
     * 陪玩行为-收钱
     * @param money
     */
    public void receiveMoney(int money) {
        System.out.println(name + "收到佣金：" + money + "元 ~ ");
    }


    /**
     * 陪玩
     * @param player
     */
    public void playWith(Player player) {
        System.out.println(name + "与" + player.getName() + "一起愉快地玩耍 ~ ");
    }
}
