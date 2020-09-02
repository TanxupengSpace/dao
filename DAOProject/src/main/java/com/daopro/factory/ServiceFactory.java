package com.daopro.factory;

import com.daopro.service.IMemberService;
import com.daopro.service.impl.MemberServiceImpl;
import com.daopro.util.ServiceProxy;

public class ServiceFactory {
    private ServiceFactory(){}
    public static IMemberService getMemberServiceInstance(){
        return (IMemberService) new ServiceProxy().bind(new MemberServiceImpl());
    }
}
