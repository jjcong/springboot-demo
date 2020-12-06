package com.jincong.springboot.test.aop.cglib;

import com.jincong.springboot.test.aop.basic.Player;
import com.jincong.springboot.test.aop.jdk.Partner;
import com.jincong.springboot.test.aop.jdk.PartnerPlatform;

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
        // 此处的Partner是basic包下的普通类，不是接口
        Partner partner = PartnerPlatform.getPartner(50);

        partner.receiveMoney(20);
        partner.playWith(player);

        partner.receiveMoney(200);
        partner.playWith(player);
    }


}
