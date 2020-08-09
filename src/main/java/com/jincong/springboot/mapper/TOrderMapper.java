package com.jincong.springboot.mapper;

import com.jincong.springboot.pojo.OrderDTO;
import com.jincong.springboot.pojo.TOrder;
import com.jincong.springboot.pojo.TOrderExample;
import com.jincong.springboot.vo.ExportConditionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TOrderMapper {
    int countByExample(TOrderExample example);

    int deleteByExample(TOrderExample example);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    List<TOrder> selectByExample(TOrderExample example);

    int updateByExampleSelective(@Param("record") TOrder record, @Param("example") TOrderExample example);

    int updateByExample(@Param("record") TOrder record, @Param("example") TOrderExample example);



    List<OrderDTO> listOrdersByScrollingPagination(@Param("conditionVO")ExportConditionVO conditionVO);


}