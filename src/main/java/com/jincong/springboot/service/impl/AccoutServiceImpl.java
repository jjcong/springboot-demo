package com.jincong.springboot.service.impl;

import com.jincong.springboot.mapper.AccoutDao;
import com.jincong.springboot.service.AccoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccoutServiceImpl implements AccoutService {

    @Autowired
    private AccoutDao accoutDao;
    @Override
    //@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int transferFrom(int userId, int money) {
        return accoutDao.transferFrom(userId, money);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int  transferTo(int userId, int money) {

        //int i = 10 /0;
        return accoutDao.transferTo(userId, money);

    }
}
