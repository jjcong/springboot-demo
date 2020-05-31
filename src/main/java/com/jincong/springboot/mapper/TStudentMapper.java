package com.jincong.springboot.mapper;

import com.jincong.springboot.pojo.TStudent;
import com.jincong.springboot.pojo.TStudentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TStudentMapper {
    int countByExample(TStudentExample example);

    int deleteByExample(TStudentExample example);

    int insert(TStudent record);

    int insertSelective(TStudent record);

    List<TStudent> selectByExample(TStudentExample example);

    int updateByExampleSelective(@Param("record") TStudent record, @Param("example") TStudentExample example);

    int updateByExample(@Param("record") TStudent record, @Param("example") TStudentExample example);
}