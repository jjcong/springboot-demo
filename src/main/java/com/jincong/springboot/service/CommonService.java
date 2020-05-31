package com.jincong.springboot.service;
/**
 * 通用服务接口
 *
 * @author  j_cong
 * @date    2020/05/16
 * @version V1.0
 */
public interface CommonService {

    boolean transfer(int fromUser, int toUser, int amount);
}
