package com.daopro.test.jdbc;

import com.daopro.dbc.DatabaseConnection;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class DatabaseMetaDataDemo {
    public static void main(String[] args) throws Exception {
        DatabaseMetaData datamd = DatabaseConnection.getConn().getMetaData();
        // 取得数据库主版本号
        System.out.println(datamd.getDatabaseMajorVersion());
        // 取得数据库子版本号
        System.out.println(datamd.getDatabaseMinorVersion());
        // 取得产品名称
        System.out.println(datamd.getDatabaseProductName());
        // 取得产品版本
        System.out.println(datamd.getDatabaseProductVersion());
        // 获取数据表信息
        ResultSet rs = datamd.getPrimaryKeys(null, "C##TANXUPENG", "MEMBER");
        while(rs.next()){
            System.out.println("Catalog:" + rs.getString(1));
            System.out.println("用户名:" + rs.getString(2));
            System.out.println("表名称:" + rs.getString(3));
            System.out.println("表的主键字段:" + rs.getString(4));
        }
    }
}
