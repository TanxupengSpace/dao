package com.daopro.service.impl;

import com.daopro.dao.IMemberDAO;
import com.daopro.dbc.DatabaseConnection;
import com.daopro.factory.DAOFactory;
import com.daopro.service.IMemberService;
import com.daopro.vo.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MemberServiceImpl implements IMemberService{
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean add(Member vo) {
        boolean flag = false;
        try{
            if(vo.getAge() > 250 || vo.getAge() < 0){
                return false;
            }
            if(!(vo.getSex().equals("男") || vo.getSex().equals("女"))){
                return false;
            }
            this.dbc.getConn().setAutoCommit(false);
            IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance(this.dbc.getConn());
            if(memberDAO.findById(vo.getMid()) == null){
                if(memberDAO.findByPhone(vo.getPhone()) == null){
                    flag = memberDAO.doCreate(vo);
                }
            }
            this.dbc.getConn().commit();
            return flag;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            this.dbc.close();
        }
        return flag;
    }

    @Override
    public boolean edit(Member vo) {
        boolean flag = false;
        try{
            if(vo.getAge() > 250 || vo.getAge() < 0){
                return false;
            }
            if(!(vo.getSex().equals("男") || vo.getSex().equals("女"))){
                return false;
            }
            this.dbc.getConn().setAutoCommit(false);
            IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance(this.dbc.getConn());
            flag = memberDAO.doEdit(vo);
            this.dbc.getConn().commit();
            return flag;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }
        return flag;
    }

    @Override
    public boolean removeById(Set<String> ids) {
        boolean flag = false;
        try{
            if(ids.size() == 0){
                return flag;
            }
            this.dbc.getConn().setAutoCommit(false);
            IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance(this.dbc.getConn());
            flag = memberDAO.doRemove(ids);
            this.dbc.getConn().commit();
            return flag;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.dbc.close();
        }
        return flag;
    }

    @Override
    public Member get(String id) {
        try{
            if(id != null || !("".equals(id))){
                IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance(this.dbc.getConn());
                return memberDAO.findById(id);
            }
            return null;
        }catch(Exception e){
            return null;
        }finally{
            this.dbc.close();
        }
    }

    @Override
    public List<Member> list() {
        try{
            IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance(this.dbc.getConn());
            return memberDAO.findAll();
        }catch(Exception e){
            return null;
        }finally{
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> split(int currentPage, int lineSize, String column, String keyWord) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance(this.dbc.getConn());
            if(column == null || "".equals(column) || keyWord == null || "".equals(keyWord)){
                map.put("allMembers", memberDAO.findSplit(currentPage, lineSize));
                map.put("allMembersCount", memberDAO.countMembers());
            }else{
                map.put("allMembers", memberDAO.findSplit(currentPage, lineSize, column, keyWord));
                map.put("allMembersCount", memberDAO.countMembers(column, keyWord));
            }
            return map;
        }catch(Exception e){
            return null;
        }finally{
            this.dbc.close();
        }
    }
}
