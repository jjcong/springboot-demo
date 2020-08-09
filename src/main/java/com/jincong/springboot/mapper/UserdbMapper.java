package com.jincong.springboot.mapper;

import com.jincong.springboot.pojo.Userdb;
import com.jincong.springboot.pojo.UserdbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserdbMapper {
    int countByExample(UserdbExample example);

    int deleteByExample(UserdbExample example);

    int insert(Userdb record);

    int insertSelective(Userdb record);

    List<Userdb> selectByExampleWithBLOBs(UserdbExample example);

    List<Userdb> selectByExample(UserdbExample example);

    int updateByExampleSelective(@Param("record") Userdb record, @Param("example") UserdbExample example);

    int updateByExampleWithBLOBs(@Param("record") Userdb record, @Param("example") UserdbExample example);

    int updateByExample(@Param("record") Userdb record, @Param("example") UserdbExample example);
}