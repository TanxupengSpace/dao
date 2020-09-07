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
    private static final String DBPASSWORD = "password_pwd";
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();
    /**
     * 初始化数据库连接驱动服务
     */
    private DatabaseConnection(){}
    public static void rebuildThreadLocal(){
        try{
            Class.forName(DBDRIVER);
            Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            CONNECTION_THREAD_LOCAL.set(conn);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 获取数据库的连接对象
     * @return
     */
    public static Connection getConn(){
        Connection conn = CONNECTION_THREAD_LOCAL.get();
        if(conn == null){
            rebuildThreadLocal();
            conn = CONNECTION_THREAD_LOCAL.get();
        }
        return conn;
    }

    /**
     * 负责关闭数据库
     */
    public static void close(){
        try{
            Connection conn = CONNECTION_THREAD_LOCAL.get();
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
                CONNECTION_THREAD_LOCAL.remove();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
