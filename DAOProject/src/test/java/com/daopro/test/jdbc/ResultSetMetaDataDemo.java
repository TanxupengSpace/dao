package com.daopro.test.jdbc;

import com.daopro.dbc.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;

public class ResultSetMetaDataDemo {
    public static void main(String[] args) throws Exception{
        String sql = "SELECT mid, name, age, email, sex, birthday, note FROM member";
        PreparedStatement pstmt = DatabaseConnection.getConn().prepareStatement(sql);
        ResultSetMetaData rsmd = pstmt.getMetaData();
        System.out.println("数据表列数:" + rsmd.getColumnCount());
        for(int x = 1 ; x < rsmd.getColumnCount() ; x ++){
            System.out.println("列名称:" + rsmd.getColumnName(x) + "\t" + "列类型:" + rsmd.getColumnTypeName(x));
        }
    }
}
