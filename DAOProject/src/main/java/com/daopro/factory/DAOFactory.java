package com.daopro.factory;

import com.daopro.dao.IMemberDAO;
import com.daopro.dao.impl.MemberDAOImpl;

public class DAOFactory {
    private DAOFactory(){}
    public static IMemberDAO getIMemberDAOInstance(){
        return new MemberDAOImpl();
    }
}
