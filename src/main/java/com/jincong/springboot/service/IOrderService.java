package com.jincong.springboot.service;

import cn.hutool.core.date.DateTime;
import com.jincong.springboot.pojo.OrderDTO;
import com.jincong.springboot.pojo.TOrder;

import java.util.List;

public interface IOrderService {

    List<OrderDTO> listOrdersByScrollingPagination(long lastBatchMaxId, int limit, DateTime startTime, DateTime endTime);


    int insertOrder(TOrder order);
}
