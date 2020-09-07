package com.daopro.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 负责SQL解析操作
 */
public class SQLSyncParse {
     public static class Entry { // 内部类
        private String name; // 存放属性的名称
        private Object value; // 存放属性的值
         public String getName() {
             return name;
         }
         public Object getValue() {
             return value;
         }
    }
    private Map<Integer, Entry> fieldSeq = new HashMap<>();
    private String pstmtSQL; // 带有 ？ 的sql
    private IMatchBoundary boundary;

    /**
     * 构造方法实现解析
     * @param sql
     * @param vo
     */
    public SQLSyncParse(String sql, Object vo){
        this.boundary = IMatchBoundary.getDefault(); // 设定边界
        this.parseSQL(sql);
        if("HashMap".equals(vo.getClass().getSimpleName())){
            this.handleMapValue(sql, vo);
        }else{
            this.handleObjectValue(sql, vo);
        }
    }

    /**
     * 将传入的sql按照格式进行解析
     * @param sql
     */
    public void parseSQL(String sql){
        String regex = this.boundary.prefix() + "\\w+" + this.boundary.suffix();
        this.pstmtSQL = sql.replaceAll(regex, "?");
    }

    private void handleMapValue(String sql, Object vo){
        String regex = this.boundary.prefix() + "\\w+" + this.boundary.suffix();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sql);
        int foot = 1;
        while(matcher.find()){
            Entry entry = new Entry();
            entry.name = matcher.group(0).replaceAll(this.boundary.prefix() + "|" + this.boundary.suffix(), "");
            try{
                Method getMethod = vo.getClass().getDeclaredMethod("get", Object.class);
                entry.value = getMethod.invoke(vo, entry.name);
            }catch(Exception e){}
            this.fieldSeq.put(foot ++, entry);
        }
    }

    /**
     * 生成SQL语句
     * @param sql
     * @param vo
     */
    private void handleObjectValue(String sql, Object vo){
        String regex = this.boundary.prefix() + "\\w+" + this.boundary.suffix();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sql);
        int foot = 1; // 定义索引
        while(matcher.find()){
            Entry entry = new Entry();
            entry.name = matcher.group(0).replaceAll(this.boundary.prefix() + "|" + this.boundary.suffix(), "");
            try{
                Method getMethod = vo.getClass().getDeclaredMethod("get" + StringUtil.stringcap(entry.name));
                entry.value = getMethod.invoke(vo);
            }catch(Exception e){}
            this.fieldSeq.put(foot ++, entry);
        }
    }
    // 获取执行的sql的参数
    public Map<Integer, Entry> getAttributeSequece(){
        return this.fieldSeq;
    }
    // 获取可执行的sql
    public String getExecuteSql(){
        return this.pstmtSQL;
    }
}
