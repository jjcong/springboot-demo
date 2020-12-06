package com.jincong.springboot.test.aop.jdk;

import com.jincong.springboot.test.aop.basic.Player;

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
        Partner partner = PartnerPlatform.getPartner(50);

        partner.receiveMoney(20);
        partner.playWith(player);

        partner.receiveMoney(200);
        partner.playWith(player);
    }


}
