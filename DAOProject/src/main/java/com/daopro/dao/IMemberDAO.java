package com.daopro.dao;

import com.daopro.vo.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * 数据层操作标准
 */
public interface IMemberDAO {
    /**
     * 数据的增加操作
     * @param vo 要增加数据的vo对象
     * @return 数据增加成功返回true，否则返回false
     * @throws SQLException
     */
    boolean doCreate(Member vo) throws SQLException;

    /**
     * 数据修改操作
     * @param vo 要修改数据的vo对象
     * @return 数据修改成功返回true，否则返回false
     * @throws SQLException
     */
    boolean doEdit(Member vo) throws SQLException;

    /**
     * 数据删除操作
     * @param ids 表示要删除数据的id
     * @return 数据删除成功返回true，否则返回false
     * @throws SQLException
     */
    boolean doRemove(Set<String> ids) throws SQLException;

    /**
     * 根据id查询出相关数据
     * @param mid 表示要查询数据的id
     * @return 如果数据存在，返回查询出的数据，如果数据不存在，则返回空
     * @throws SQLException
     */
    Member findById(String mid) throws SQLException;

    /**
     * 根据电话号码查询用户数据
     * @param phone 表示要查询用户数据的号码
     * @return 如果数据存在，则返回查询出来的结果，如果数据不存在，则返回nuLl
     * @throws SQLException
     */
    Member findByPhone(String phone) throws SQLException;

    /**
     * 查询全部数据
     * @return 如果数据存在，则返回全部的数据信息，否则，返回null
     * @throws SQLException
     */
    List<Member> findAll() throws SQLException;

    /**
     * 分页查询数据信息
     * @param currentPage 表示当前所在的页
     * @param lineSize 表示每页显示的行
     * @return 如果数据存在，返回查询的结果，否则返回null
     * @throws SQLException
     */
    List<Member> findSplit(Integer currentPage, Integer lineSize) throws SQLException;

    /**
     * 模糊分页查询数据信息
     * @param currentPage 表示当前所在的页
     * @param lineSize 表示每页显示的行数
     * @param column 表示要进行模糊查询的数据列
     * @param keyWord 表示要进行模糊查询的关键字
     * @return 返回查询的数据结果，没有则返回null
     * @throws SQLException
     */
    List<Member> findSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws SQLException;

    /**
     * 统计分页查询数据量
     * @return 返回统计的结果
     * @throws SQLException
     */
    Long countMembers() throws SQLException;

    /**
     * 统计模糊查询的数据量
     * @param column 表示要进行模糊查询的数据列
     * @param keyWord 表示要进行模糊查询的关键字
     * @return 返回统计结果
     */
    Long countMembers(String column, String keyWord) throws SQLException;
}
