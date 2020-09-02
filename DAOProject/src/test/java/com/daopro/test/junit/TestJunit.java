package com.daopro.test.junit;

import com.daopro.factory.ServiceFactory;
import com.daopro.service.IMemberService;
import com.daopro.vo.Member;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

public class TestJunit {
    private String mid;
    private String email;
    private String phone;

    public void set_info(){
        Random ran = new Random();
        int id = ran.nextInt() * 10;
        String mids = String.valueOf(id);
        this.mid = mids.substring(4);
        String base64Msg = Base64.getEncoder().encodeToString(mids.getBytes());
        base64Msg = base64Msg.substring(10);
        this.email = base64Msg + "@163.com";
        this.phone = mids;
    }
    @Test
    public void testAdd(){
        this.set_info();
        Member member = new Member();
        member.setMid(this.mid);
        member.setName("谭旭鹏");
        member.setAge(20);
        member.setSex("男");
        member.setNote("一个帅气的男人");
        member.setEmail(this.email);
        member.setBirthday(new Date());
        member.setPhone(this.phone);
        IMemberService memberService = ServiceFactory.getMemberServiceInstance();
        TestCase.assertTrue(memberService.add(member));
    }
    @Test
    public void testEdit(){
        this.set_info();
        Member member = new Member();
        member.setMid("1231");
        member.setName("周杰伦");
        member.setAge(20);
        member.setSex("男");
        member.setNote("一个帅气的男人");
        member.setEmail(this.email);
        member.setBirthday(new Date());
        member.setPhone(this.phone);
        IMemberService memberService = ServiceFactory.getMemberServiceInstance();
        TestCase.assertTrue(memberService.edit(member));
    }
    @Test
    public void testRemove(){
        Set<String> ids = new HashSet<>();
        ids.add("1231");
        IMemberService memberService = ServiceFactory.getMemberServiceInstance();
        TestCase.assertTrue(memberService.removeById(ids));
    }
    @Test
    public void testGet(){
        IMemberService memberService = ServiceFactory.getMemberServiceInstance();
        TestCase.assertNotNull(memberService.get("1231"));
    }
    @Test
    public void testList(){
        IMemberService memberService = ServiceFactory.getMemberServiceInstance();
        TestCase.assertTrue(memberService.list().size() > 0);
    }
    @Test
    public void testSplit(){
        IMemberService memberService = ServiceFactory.getMemberServiceInstance();
        Map<String, Object> resultMap = memberService.split(1, 10, null, null);
        List<Member> list = (List<Member>) resultMap.get("allMembers");
        long len = (Long) resultMap.get("allMembersCount");
        TestCase.assertTrue(list.size() > 0 && len != 0);
    }
}
