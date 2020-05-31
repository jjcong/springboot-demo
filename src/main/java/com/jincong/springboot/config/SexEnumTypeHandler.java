package com.jincong.springboot.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 性别枚举类对应的TypeHandler，mybatis会自动转换
 *
 * @author  j_cong
 * @date    2020/05/21
 * @version V1.0
 */

public class SexEnumTypeHandler extends BaseTypeHandler<SexEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        // 获取枚举的code值，并设置到PreparedStatement中
        ps.setInt(i, parameter.code());
    }

    @Override
    public SexEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 从ResultSet中取出code
        int code = rs.getInt(columnName);
        // 解析code并返回结果
        return SexEnum.find(code);
    }

    @Override
    public SexEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);

        return SexEnum.find(code);
    }

    @Override
    public SexEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {

        int code = cs.getInt(columnIndex);
        // 解析code并返回结果
        return SexEnum.find(code);
    }
}
