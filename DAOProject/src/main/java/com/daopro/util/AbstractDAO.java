package com.daopro.util;

import com.daopro.dbc.DatabaseConnection;

import java.sql.*;
import java.util.Map;

public abstract class AbstractDAO {
    protected PreparedStatement pstmt;
    protected Connection conn;
    public AbstractDAO(){
        this.conn = DatabaseConnection.getConn();
    }

    /**
     * 数据的统计操作
     * @param tabName
     * @return
     * @throws SQLException
     */
    protected Long getAllCount(String tabName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tabName;
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        if(rs.next()){
            return rs.getLong(1);
        }
        return 0L;
    }

    /**
     * 数据的更新操作
     * @param sql
     * @param vo
     * @return
     * @throws SQLException
     */
    public boolean handleUpdate(String sql, Object vo) throws SQLException{
        SQLSyncParse parse = new SQLSyncParse(sql, vo);
        this.pstmt = this.conn.prepareStatement(parse.getExecuteSql()); // 进行SQL预处理操作
        Map<Integer, SQLSyncParse.Entry> paramValues = parse.getAttributeSequece();
        for(int i = 1 ; i <= paramValues.size() ; i ++){
            this.setPreparedStatmentValue(i, paramValues.get(i).getValue());
        }
        return this.pstmt.executeUpdate() > 0;
    }
    private void setPreparedStatmentValue(Integer seq, Object value) throws SQLException{
        if(value == null){
            this.pstmt.setNull(seq, Types.NULL);
        }else{
            if("String".equals(value.getClass().getSimpleName())){
                this.pstmt.setString(seq, (String) value);
            }else if("Long".equals(value.getClass().getSimpleName()) || "long".equals(value.getClass().getSimpleName())){
                this.pstmt.setLong(seq, (Long) value);
            }else if("Integer".equals(value.getClass().getSimpleName()) || "int".equals(value.getClass().getSimpleName())){
                this.pstmt.setInt(seq, (Integer) value);
            }else if("Double".equals(value.getClass().getSimpleName()) || "double".equals(value.getClass().getSimpleName())){
                this.pstmt.setDouble(seq, (Double) value);
            }else if("Date".equals(value.getClass().getSimpleName())){
                this.pstmt.setDate(seq, new java.sql.Date(((java.util.Date) value).getTime()));
            }
        }
    }
}
