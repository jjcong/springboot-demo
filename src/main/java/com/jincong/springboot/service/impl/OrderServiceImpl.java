package com.jincong.springboot.service.impl;

import cn.hutool.core.date.DateTime;
import com.jincong.springboot.mapper.OrderMapper;
import com.jincong.springboot.mapper.TOrderMapper;
import com.jincong.springboot.pojo.OrderDTO;
import com.jincong.springboot.pojo.TOrder;
import com.jincong.springboot.service.IOrderService;
import com.jincong.springboot.service.RedisTemplateService;
import com.jincong.springboot.vo.ExportConditionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    @Autowired
    TOrderMapper orderMapper;
    @Autowired
    private OrderMapper newOrderMapper;

    @Autowired
    RedisTemplateService redisTemplateService;

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

    @Override
    public int getStockFromDB(Integer id) {
        Weekend<TOrder> weekend = Weekend.of(TOrder.class);
        weekend.weekendCriteria().andEqualTo(TOrder::getId, id);
        TOrder order = newOrderMapper.selectOneByExample(weekend);

        return order.getAmount().intValue();
    }

    @Override
    public int getStockFromRedis(Integer id) {

        String key = "stock::" + id;

        Integer stock = redisTemplateService.get(key, Integer.class);
        if (stock == null) {
            log.info("缓存未命中，访问数据库获取库存");
            stock = getStockFromDB(id);
            writeRedis(key, stock);
        }
        log.info("缓存命中，当前商品为【{}】， 库存为【{}】", id, stock);
        return stock;
    }

    @Override
    public boolean writeRedis(String key, Integer value) {
        String id = key.split("::")[1];
        return redisTemplateService.set(key, value);
    }
}
