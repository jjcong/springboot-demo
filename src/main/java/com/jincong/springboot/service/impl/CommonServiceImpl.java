package com.jincong.springboot.service.impl;

import com.jincong.springboot.service.AccoutService;
import com.jincong.springboot.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 通用接口实现类
 *
 * @author  j_cong
 * @date    2020/05/16
 * @version V1.0
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private AccoutService accoutService;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean transfer(int fromUser, int toUser, int amount) {

        int fromResult = accoutService.transferFrom(fromUser, amount);
        int toResult = accoutService.transferTo(toUser, amount);
        return fromResult > 0 && toResult > 0;
    }
}
