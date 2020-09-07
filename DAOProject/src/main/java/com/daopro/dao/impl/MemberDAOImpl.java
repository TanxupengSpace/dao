package com.daopro.dao.impl;

import com.daopro.dao.IMemberDAO;
import com.daopro.util.AbstractDAO;
import com.daopro.vo.Member;

import java.sql.SQLException;
import java.util.*;

public class MemberDAOImpl extends AbstractDAO implements IMemberDAO {
    @Override
    public boolean doCreate(Member vo) throws SQLException {
        String sql = "INSERT INTO member(mid, name, age, email, sex, birthday, note, phone) VALUES (#{mid}, #{name}, #{age}, #{email}, #{sex}, #{birthday}, #{note}, #{phone})";
        return super.handleUpdate(sql, vo);
    }

    @Override
    public boolean doEdit(Member vo) throws SQLException {
        String sql = "UPDATE member SET name=#{name}, age=#{age}, email=#{email}, sex=#{sex}, birthday=#{birthday}, note=#{note}, phone=#{phone} WHERE mid=#{mid}";
        return super.handleUpdate(sql, vo);
    }

    @Override
    public boolean doRemove(Set<String> ids) throws SQLException {
        StringBuffer sql = new StringBuffer(30);
        sql.append("DELETE FROM member WHERE mid IN ( ");
        ids.forEach((id) -> {
            sql.append("?,");
        });
        sql.delete(sql.length() - 1, sql.length()).append(")");
        super.pstmt = super.conn.prepareStatement(sql.toString());
        Iterator<String> iter = ids.iterator();
        int foot = 1;
        while(iter.hasNext()){
            super.pstmt.setString(foot ++, iter.next());
        }
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public Member findById(String mid) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("mid", mid);
        String sql = "SELECT mid, name, age, email, sex, birthday, note, phone FROM member WHERE mid=#{mid}";
        return super.handleSingleObject(sql, params, Member.class);
    }

    @Override
    public Member findByPhone(String phone) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("phone", phone);
        String sql = "SELECT mid, name, age, email, sex, birthday, note, phone FROM member WHERE phone=#{phone}";
        return super.handleSingleObject(sql, params, Member.class);
    }

    @Override
    public List<Member> findAll() throws SQLException {
        Map<String, Object> params = new HashMap<>();
        String sql = "SELECT mid, name, age, email, sex, birthday, note, phone FROM member";
        return super.handleSigleObjectList(sql, params, Member.class);
    }

    @Override
    public List<Member> findSplit(Integer currentPage, Integer lineSize) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("line", currentPage * lineSize);
        params.put("star", (currentPage - 1) * lineSize);
        String sql = "SELECT * FROM (" +
                " SELECT mid, name, age, email, sex, birthday, note, phone, ROWNUM rn FROM member WHERE ROWNUM <=#{line})" +
                " temp WHERE temp.rn >=#{star}";
        return super.handleSigleObjectList(sql, params, Member.class);
    }

    @Override
    public List<Member> findSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", "%" + keyWord + "%");
        params.put("line", currentPage * lineSize);
        params.put("star", (currentPage - 1) * lineSize);
        String sql = "SELECT * FROM (" +
                " SELECT mid, name, age, email, sex, birthday, note, phone, ROWNUM rn FROM member WHERE " +
                column + " LIKE #{keyword} AND ROWNUM <=#{line})" +
                " temp WHERE temp.rn >=#{star}";
        return super.handleSigleObjectList(sql, params, Member.class);
    }

    @Override
    public Long countMembers() throws SQLException {
        return super.getAllCount("member");
    }

    @Override
    public Long countMembers(String column, String keyWord) throws SQLException{
        Map<String, Object> params = new HashMap<>();
        params.put("keyWord", "%" + keyWord + "%");
        String sql = "SELECT COUNT(*) FROM member WHERE " + column + " LIKE #{keyWord}";
        return super.handleSingleObject(sql, params, Member.class);
    }
}
