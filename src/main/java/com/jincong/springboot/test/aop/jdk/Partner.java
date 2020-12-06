package com.jincong.springboot.test.aop.jdk;

import com.jincong.springboot.test.aop.basic.Player;

/**
 * Partner
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/12/6
 */
public interface Partner {
    /**
     * 收钱
     * @param money
     */
    void receiveMoney(int money);

    /**
     * 陪玩
     * @param player
     */
    void playWith(Player player);
}
