package com.jincong.springboot.service;

/**
 * 账户接口类
 *
 * @author  j_cong
 * @date    2020/05/16
 * @version V1.0
 */
public interface AccoutService {

    int transferFrom(int userId, int money);

    int transferTo(int userId, int money);

}
