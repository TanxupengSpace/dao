package com.daopro.test.junit;

import com.daopro.factory.ServiceFactory;
import com.daopro.service.IMemberService;
import com.daopro.vo.Member;
import org.junit.Test;

import java.util.Date;
import java.util.Random;

public class TestJunit {
    private int mid;
     @Test
    public void test(){
        Random ran = new Random();
        System.out.println(ran.nextInt());
        System.out.println(ran.nextInt() + 1);
    }
    @Test
    public void testAdd(){
        Member member = new Member();
        member.setMid("1234");
        member.setName("谭旭鹏");
        member.setAge(20);
        member.setSex("男");
        member.setNote("一个帅气的男人");
        member.setEmail("2961363297@qq.com");
        member.setBirthday(new Date());
        IMemberService memberService = ServiceFactory.getMemberServiceInstance();
        System.out.println(memberService.add(member));
    }
    @Test
    public void testEdit(){

    }
    @Test
    public void testRemove(){

    }
    @Test
    public void testGet(){

    }
    @Test
    public void testList(){

    }
    @Test
    public void testSplit(){

    }
}
