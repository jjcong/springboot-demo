package com.jincong.springboot.service;

import java.sql.*;

/**
 * JDBC连接测试
 *
 * @author j_cong
 * @version V1.0
 * @date 2019/07/29
 */
public class JDBCTest {

    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC";
        String userName = "root";
        String password = "jincong";

        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            stmt = con.createStatement();
            String sql = "select * from t_user";
            res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println(res.getInt("id") + "=" + res.getString("user_name"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
