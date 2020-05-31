package com.jincong.springboot.mapper;

import com.jincong.springboot.domain.Accout;
import com.jincong.springboot.pojo.AccoutExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccoutDao {
    long countByExample(AccoutExample example);

    int deleteByExample(AccoutExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Accout record);

    int insertSelective(Accout record);

    List<Accout> selectByExample(AccoutExample example);

    Accout selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Accout record, @Param("example") AccoutExample example);

    int updateByExample(@Param("record") Accout record, @Param("example") AccoutExample example);

    int updateByPrimaryKeySelective(Accout record);

    int updateByPrimaryKey(Accout record);

    int transferFrom(@Param("userId") int userId, @Param("amount")int amount);

    int transferTo(@Param("userId") int userId, @Param("amount")int amount);

}