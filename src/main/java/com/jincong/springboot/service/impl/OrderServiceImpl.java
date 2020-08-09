package com.jincong.springboot.service.impl;

import cn.hutool.core.date.DateTime;
import com.jincong.springboot.mapper.TOrderMapper;
import com.jincong.springboot.pojo.OrderDTO;
import com.jincong.springboot.pojo.TOrder;
import com.jincong.springboot.service.IOrderService;
import com.jincong.springboot.vo.ExportConditionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    TOrderMapper orderMapper;

    @Override
    public List<OrderDTO> listOrdersByScrollingPagination(long lastBatchMaxId, int limit, DateTime startTime, DateTime endTime) {
        ExportConditionVO conditionVO = new ExportConditionVO();

        conditionVO.setLastBatchMaxId(lastBatchMaxId);
        conditionVO.setLimit(limit);
        conditionVO.setStartTime(startTime);
        conditionVO.setEndTime(endTime);


        return orderMapper.listOrdersByScrollingPagination(conditionVO);
    }

    @Override
    public int insertOrder(TOrder order) {

        return orderMapper.insertSelective(order);
    }
}
