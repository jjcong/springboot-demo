package com.jincong.springboot.mapper;

import com.jincong.springboot.pojo.TGoodsCategory;
import com.jincong.springboot.pojo.TGoodsCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TGoodsCategoryMapper {
    int countByExample(TGoodsCategoryExample example);

    int deleteByExample(TGoodsCategoryExample example);

    int insert(TGoodsCategory record);

    int insertSelective(TGoodsCategory record);

    List<TGoodsCategory> selectByExample(TGoodsCategoryExample example);

    int updateByExampleSelective(@Param("record") TGoodsCategory record, @Param("example") TGoodsCategoryExample example);

    int updateByExample(@Param("record") TGoodsCategory record, @Param("example") TGoodsCategoryExample example);
}