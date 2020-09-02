package com.daopro.util;

import com.daopro.dbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO {
    protected PreparedStatement pstmt;
    protected Connection conn;
    public AbstractDAO(){
        this.conn = DatabaseConnection.getConn();
    }

    protected Long getAllCount(String tabName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tabName;
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        if(rs.next()){
            return rs.getLong(1);
        }
        return 0L;
    }
}
