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

    int addUser(User user);

    int delBatchUser(int[] ids);

    @Select("SELECT * FROM t_user WHERE user_code = #{userCode, jdbcType=VARCHAR}")
    UserVO annotationFindUserByUserCode(@Param("userCode")String userCode);


    /**
     * 根据用户名称和用户id查询数据
     * @param userName 用户名称
     * @param userIds  用户Id
     * @return
     */
    List<User> findUserByUserNameAndIds( String userName,List<Integer> userIds);

}
