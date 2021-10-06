package com.jincong.springboot.service.impl;

import com.jincong.springboot.mapper.AccoutMapper;
import com.jincong.springboot.service.AccoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccoutServiceImpl implements AccoutService {


    @Autowired
    private AccoutMapper accoutMapper;
    @Override
    //@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int transferFrom(int userId, int money) {
        return accoutMapper.transferFrom(userId, money);
    }


    @Override
    //@Transactional(propagation = Propagation.SUPPORTS)
    public int  transferTo(int userId, int money) {


        int i1 = accoutMapper.transferTo(userId, money);
        int i = 10 /0;
        return i1;


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean transferMoney() {

        int fromUser = 1001;
        int toUser = 1002;
        int amount = 10;

        int fromResult = transferFrom(fromUser, amount);
        int toResult = transferTo(toUser, amount);

        return fromResult > 0 && toResult > 0;
    }
}
