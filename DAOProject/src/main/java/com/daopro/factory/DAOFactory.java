package com.daopro.factory;

import com.daopro.dao.IMemberDAO;
import com.daopro.dao.impl.MemberDAOImpl;

import java.sql.Connection;

public class DAOFactory {
    private DAOFactory(){}
    public static IMemberDAO getIMemberDAOInstance(Connection conn){
        return new MemberDAOImpl(conn);
    }
}
