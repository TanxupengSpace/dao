package com.daopro.util;

import com.daopro.dbc.DatabaseConnection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    public <T> List<T> handleSigleObjectList(String sql, Map<String, Object> map, Class<?> clazz) throws SQLException{
        SQLSyncParse syncParse = new SQLSyncParse(sql, map);
        this.pstmt = this.conn.prepareStatement(syncParse.getExecuteSql());
        Map<Integer, SQLSyncParse.Entry> paramValues = syncParse.getAttributeSequece();
        for(int i = 1 ; i <= paramValues.size() ; i ++){
            this.setPreparedStatmentValue(i, paramValues.get(i).getValue());
        }
        ResultSet rs = this.pstmt.executeQuery();
        List<T> lists = new ArrayList<>();
        while(rs.next()){
            try{
                lists.add((T) this.createSingleVO(rs, clazz));
            }catch(Exception e){}
        }
        return lists;
    }

    /**
     * 数据查询操作
     * @param sql
     * @param map
     * @param clazz
     * @param <T>
     * @return
     * @throws SQLException
     */
    public <T> T handleSingleObject(String sql, Map<String, Object> map, Class<?> clazz) throws SQLException{
        SQLSyncParse parse = new SQLSyncParse(sql, map);
        this.pstmt = this.conn.prepareStatement(parse.getExecuteSql());
        Map<Integer, SQLSyncParse.Entry> paramValues = parse.getAttributeSequece();
        for(int i = 1 ; i <= paramValues.size() ; i ++){
            this.setPreparedStatmentValue(i, paramValues.get(i).getValue());
        }
        ResultSet rs = this.pstmt.executeQuery();
        if(rs.next()){
            try{
                return (T) this.createSingleVO(rs, clazz);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 数据的查询操作
     */
    public Object createSingleVO(ResultSet rs, Class<?> clazz) throws Exception{
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        Object obj = clazz.getDeclaredConstructor().newInstance();
        for(int i = 1 ; i < resultSetMetaData.getColumnCount() ; i ++){
            String fieldName = resultSetMetaData.getColumnName(i).toLowerCase();
            Field field = clazz.getDeclaredField(fieldName);
            Method setMethod = clazz.getDeclaredMethod("set" + StringUtil.stringcap(fieldName), field.getType());
            setMethod.invoke(obj, this.handleResultValue(rs, field.getType().getSimpleName(), fieldName));
        }
        return obj;
    }
    private Object handleResultValue(ResultSet rs, String type, String columnName) throws SQLException{
        if("String".equals(type)){
            return rs.getString(columnName);
        }else if("Long".equals(type) || "long".equals(type)){
            return rs.getLong(columnName);
        }else if("Integer".equals(type) || "int".equals(type)){
            return rs.getInt(columnName);
        }else if("Double".equals(type) || "double".equals(type)){
            return rs.getDouble(columnName);
        }else if("Date".equals(type)){
            return rs.getDate(columnName);
        }else{
            return null;
        }
    }
}
