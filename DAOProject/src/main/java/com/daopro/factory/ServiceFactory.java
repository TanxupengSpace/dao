package com.daopro.factory;

import com.daopro.service.IMemberService;
import com.daopro.service.impl.MemberServiceImpl;

public class ServiceFactory {
    private ServiceFactory(){}
    public static IMemberService getMemberServiceInstance(){
        return new MemberServiceImpl();
    }
}
