package com.daopro.util;

import com.daopro.dbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class AbstractDAO {
    protected PreparedStatement pstmt;
    protected Connection conn;
    public AbstractDAO(){
        this.conn = DatabaseConnection.getConn();
    }
}
