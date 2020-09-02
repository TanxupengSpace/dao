package com.daopro.service.impl;

import com.daopro.dao.IMemberDAO;
import com.daopro.factory.ObjectFactory;
import com.daopro.service.IMemberService;
import com.daopro.vo.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MemberServiceImpl implements IMemberService{
    @Override
    public boolean add(Member vo) throws Exception {
        boolean flag = false;
        if(vo.getAge() > 250 || vo.getAge() < 0){
            return false;
        }
        if(!(vo.getSex().equals("男") || vo.getSex().equals("女"))){
            return false;
        }
        IMemberDAO memberDAO = ObjectFactory.getDAOInstance("member.dao", IMemberDAO.class);
        if(memberDAO.findById(vo.getMid()) == null){
            if(memberDAO.findByPhone(vo.getPhone()) == null){
                flag = memberDAO.doCreate(vo);
            }
        }
        return flag;
    }

    @Override
    public boolean edit(Member vo) throws Exception {
        boolean flag = false;
        if(vo.getAge() > 250 || vo.getAge() < 0){
            return false;
        }
        if(!(vo.getSex().equals("男") || vo.getSex().equals("女"))){
            return false;
        }
        IMemberDAO memberDAO = ObjectFactory.getDAOInstance("member.dao", IMemberDAO.class);
        flag = memberDAO.doEdit(vo);
        return flag;
    }

    @Override
    public boolean removeById(Set<String> ids) throws Exception {
        boolean flag = false;
        if(ids.size() == 0){
            return flag;
        }
        IMemberDAO memberDAO = ObjectFactory.getDAOInstance("member.dao", IMemberDAO.class);
        flag = memberDAO.doRemove(ids);
        return flag;
    }

    @Override
    public Member get(String id) throws Exception {
        if(id != null || !("".equals(id))) {
            IMemberDAO memberDAO = ObjectFactory.getDAOInstance("member.dao", IMemberDAO.class);
            return memberDAO.findById(id);
        }
        return null;
    }

    @Override
    public List<Member> list() throws Exception {
        return ObjectFactory.getDAOInstance("member.dao", IMemberDAO.class).findAll();
    }

    @Override
    public Map<String, Object> split(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        IMemberDAO memberDAO = ObjectFactory.getDAOInstance("member.dao", IMemberDAO.class);
        if(column == null || "".equals(column) || keyWord == null || "".equals(keyWord)){
            map.put("allMembers", memberDAO.findSplit(currentPage, lineSize));
            map.put("allMembersCount", memberDAO.countMembers());
        }else{
            map.put("allMembers", memberDAO.findSplit(currentPage, lineSize, column, keyWord));
            map.put("allMembersCount", memberDAO.countMembers(column, keyWord));
        }
        return map;
    }
}
