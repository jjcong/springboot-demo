package com.jincong.springboot.mapper;

import com.jincong.springboot.pojo.Accout;
import com.jincong.springboot.pojo.AccoutExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccoutMapper {
    int countByExample(AccoutExample example);

    int deleteByExample(AccoutExample example);

    int insert(Accout record);

    int insertSelective(Accout record);

    List<Accout> selectByExample(AccoutExample example);

    int updateByExampleSelective(@Param("record") Accout record, @Param("example") AccoutExample example);

    int updateByExample(@Param("record") Accout record, @Param("example") AccoutExample example);
}