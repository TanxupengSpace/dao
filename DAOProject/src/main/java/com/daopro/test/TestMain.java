package com.daopro.test;

import com.daopro.factory.ServiceFactory;
import com.daopro.service.IMemberService;
import com.daopro.vo.Member;

import java.util.Date;

public class TestMain {
    public static void main(String[] args) {
        Member member = new Member();
        member.setMid("1231");
        member.setName("谭旭鹏");
        member.setAge(20);
        member.setSex("男");
        member.setNote("一个帅气的男人");
        member.setEmail("2961363297@qq.com");
        member.setBirthday(new Date());
        IMemberService memberService = ServiceFactory.getMemberServiceInstance();
        System.out.println(memberService.add(member));
    }
}
