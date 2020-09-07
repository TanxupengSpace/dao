package com.daopro.dao.impl;

import com.daopro.dao.IMemberDAO;
import com.daopro.util.AbstractDAO;
import com.daopro.vo.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MemberDAOImpl extends AbstractDAO implements IMemberDAO {
    @Override
    public boolean doCreate(Member vo) throws SQLException {
        String sql = "INSERT INTO member(mid, name, age, email, sex, birthday, note, phone) VALUES (#{mid}, #{name}, #{age}, #{email}, #{sex}, #{birthday}, #{note}, #{phone})";
        return super.handleUpdate(sql, vo);
    }

    @Override
    public boolean doEdit(Member vo) throws SQLException {
        String sql = "UPDATE member SET name=?, age=?, email=?, sex=?, birthday=?, note=?, phone=? WHERE mid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,vo.getName());
        super.pstmt.setInt(2,vo.getAge());
        super.pstmt.setString(3,vo.getEmail());
        super.pstmt.setString(4,vo.getSex());
        super.pstmt.setDate(5,new java.sql.Date(vo.getBirthday().getTime()));
        super.pstmt.setString(6,vo.getNote());
        super.pstmt.setString(7, vo.getPhone());
        super.pstmt.setString(8, vo.getMid());
        return super.pstmt.executeUpdate() > 0;
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
        String sql = "SELECT mid, name, age, email, sex, birthday, note, phone FROM member WHERE mid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        ResultSet rs = super.pstmt.executeQuery();
        Member member = null;
        if(rs.next()){
            member = new Member();
            member.setMid(rs.getString(1));
            member.setName(rs.getString(2));
            member.setAge(rs.getInt(3));
            member.setEmail(rs.getString(4));
            member.setSex(rs.getString(5));
            member.setBirthday(rs.getDate(6));
            member.setNote(rs.getString(7));
            member.setPhone(rs.getString(8));
        }
        return member;
    }

    @Override
    public Member findByPhone(String phone) throws SQLException {
        String sql = "SELECT mid, name, age, email, sex, birthday, note, phone FROM member WHERE phone=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, phone);
        ResultSet rs = super.pstmt.executeQuery();
        Member member = null;
        if(rs.next()){
            member = new Member();
            member.setMid(rs.getString(1));
            member.setName(rs.getString(2));
            member.setAge(rs.getInt(3));
            member.setEmail(rs.getString(4));
            member.setSex(rs.getString(5));
            member.setBirthday(rs.getDate(6));
            member.setNote(rs.getString(7));
            member.setPhone(rs.getString(8));
        }
        return member;
    }

    @Override
    public List<Member> findAll() throws SQLException {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT mid, name, age, email, sex, birthday, note, phone FROM member";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        while(rs.next()){
            Member member = new Member();
            member.setMid(rs.getString(1));
            member.setName(rs.getString(2));
            member.setAge(rs.getInt(3));
            member.setEmail(rs.getString(4));
            member.setSex(rs.getString(5));
            member.setBirthday(rs.getDate(6));
            member.setNote(rs.getString(7));
            member.setPhone(rs.getString(8));
            memberList.add(member);
        }
        return memberList;
    }

    @Override
    public List<Member> findSplit(Integer currentPage, Integer lineSize) throws SQLException {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM (" +
                " SELECT mid, name, age, email, sex, birthday, note, phone, ROWNUM rn FROM member WHERE ROWNUM <=?)" +
                " temp WHERE temp.rn >=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1, currentPage * lineSize);
        super.pstmt.setInt(2, (currentPage - 1) * lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while(rs.next()){
            Member member = new Member();
            member.setMid(rs.getString(1));
            member.setName(rs.getString(2));
            member.setAge(rs.getInt(3));
            member.setEmail(rs.getString(4));
            member.setSex(rs.getString(5));
            member.setBirthday(rs.getDate(6));
            member.setNote(rs.getString(7));
            member.setPhone(rs.getString(8));
            memberList.add(member);
        }
        return memberList;
    }

    @Override
    public List<Member> findSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM (" +
                " SELECT mid, name, age, email, sex, birthday, note, phone, ROWNUM rn FROM member WHERE " +
                column + " LIKE ? AND ROWNUM <=?)" +
                " temp WHERE temp.rn >=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setInt(2, currentPage * lineSize);
        super.pstmt.setInt(3, (currentPage - 1) * lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while(rs.next()){
            Member member = new Member();
            member.setMid(rs.getString(1));
            member.setName(rs.getString(2));
            member.setAge(rs.getInt(3));
            member.setEmail(rs.getString(4));
            member.setSex(rs.getString(5));
            member.setBirthday(rs.getDate(6));
            member.setNote(rs.getString(7));
            member.setPhone(rs.getString(8));
            memberList.add(member);
        }
        return memberList;
    }

    @Override
    public Long countMembers() throws SQLException {
        return super.getAllCount("member");
    }

    @Override
    public Long countMembers(String column, String keyWord) throws SQLException{
        String sql = "SELECT COUNT(*) FROM member WHERE " + column + " LIKE ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%" + keyWord + "%");
        ResultSet rs = super.pstmt.executeQuery();
        if(rs.next()){
            return rs.getLong(1);
        }
        return 0L;
    }
}
