package com.jincong.springboot.mapper;

import com.jincong.springboot.domain.User;
import com.jincong.springboot.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    List<User> findAllUser();


    UserVO findUserByUserCode(@Param("userCode") String userCode);

    List<User> findUserByUserName(Map request);



    // 此处无需加@Param注解
    int addUser(User user);


    /**
     * 批量插入用户
     * 注：此处不能加@Param注解，否则会得不到自动生成的主键
     * @param userList
     * @return
     */
    int batchInsertUser(List<User> userList);

    int delBatchUser(int[] ids);

    @Select("SELECT * FROM t_user WHERE user_code = #{userCode, jdbcType=VARCHAR}")
    UserVO annotationFindUserByUserCode(@Param("userCode")String userCode);


    /**
     * 根据用户名称和用户id查询数据
     * @param userName 用户名称
     * @param userIds  用户Id
     * @return   用户列表，如果没有则返回空集合
     */
    List<User> findUserByUserNameAndIds(String userName,List<Integer> userIds);




    List<Integer> existData();

}
