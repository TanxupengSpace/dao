package com.daopro.dbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 用于数据库的连接
 */
public class DatabaseConnection {
    private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
    private static final String DBUSER = "c##tanxupeng";
    private static final String DBPASSWORD = "19930716_Tan";
    private Connection conn; // 数据库连接对象
    /**
     * 初始化数据库连接驱动服务
     */
    public DatabaseConnection(){
        try{
            Class.forName(DBDRIVER);
            this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 获取数据库的连接对象
     * @return
     */
    public Connection getConn(){
        return this.conn;
    }

    /**
     * 负责关闭数据库
     */
    public void close(){
        try{
            if(this.conn != null){
                this.conn.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
