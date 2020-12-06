package com.jincong.springboot.test.aop.basic;

/**
 * Client客户端
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/12/6
 */
public class Client {

    public static void main(String[] args) {
        Player player = new Player("郝武辽");
        Partner partner = new Partner("肖洁洁");
        partner.receiveMoney(100);
        partner.playWith(player);
    }


}
