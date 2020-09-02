package com.daopro.dao;

import com.daopro.util.IBaseDAO;
import com.daopro.vo.Member;

import java.sql.SQLException;

/**
 * 数据层操作标准
 */
public interface IMemberDAO extends IBaseDAO<String, Member> {
    /**
     * 根据电话号码查询用户数据
     * @param phone 表示要查询用户数据的号码
     * @return 如果数据存在，则返回查询出来的结果，如果数据不存在，则返回nuLl
     * @throws SQLException
     */
    Member findByPhone(String phone) throws SQLException;
}
