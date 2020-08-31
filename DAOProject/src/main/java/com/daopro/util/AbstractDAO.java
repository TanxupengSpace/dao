package com.daopro.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class AbstractDAO {
    protected PreparedStatement pstmt;
    protected Connection conn;
    public AbstractDAO(Connection conn){
        this.conn = conn;
    }
}
