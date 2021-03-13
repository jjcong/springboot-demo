package com.jincong.springboot.config;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * 列表TypeHandler，实现将数据库保存的多个值映射为list
 * MappedJdbcTypes  配置数据库中保存的类型
 * MappedTypes      配置映射的Java类型
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/03/13
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(List.class)
public class ListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {

        List<String> hobbyList = Lists.newArrayList();

        if (CollectionUtil.isNotEmpty(parameter)) {
            hobbyList = parameter;
        }

        ps.setString(i, String.join(",", hobbyList));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {

        String listString = rs.getString(columnName);

        return StringUtils.isEmpty(listString) ? Lists.newArrayList() : Arrays.asList(listString.split(","));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String listString = rs.getString(columnIndex);

        return StringUtils.isEmpty(listString) ? Lists.newArrayList() : Arrays.asList(listString.split(","));
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String listString = cs.getString(columnIndex);

        return StringUtils.isEmpty(listString) ? Lists.newArrayList() : Arrays.asList(listString.split(","));
    }
}
