package com.daopro.util;

import com.daopro.dbc.DatabaseConnection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceProxy implements InvocationHandler {
    private static final String METHOD_NAME_TRASACTION[] = {"add", "create", "edit", "update", "delete", "remove"};
    private Object target;
    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object retObj = null;
        boolean transactionFalg = this.openTrasaction(method.getName()); // 判断是否要紧开启事务处理
        if(transactionFalg) {
            DatabaseConnection.getConn().setAutoCommit(false);
        }
        try{
            retObj = method.invoke(this.target, args);
            if(transactionFalg){
                DatabaseConnection.getConn().commit();
            }
        }catch(Exception e){
            if(transactionFalg){
                DatabaseConnection.getConn().rollback();
            }
            throw e;
        }finally{
            DatabaseConnection.close();
        }
        return retObj;
    }

    /**
     * 判断是否是指定的方法名称
     * @param methodName 要判断的方法名称
     * @return
     */
    public boolean openTrasaction(String methodName){
        for(int x = 0 ; x < METHOD_NAME_TRASACTION.length ; x ++){
            if(methodName.startsWith(METHOD_NAME_TRASACTION[x])){
                return true;
            }
        }
        return false;
    }
}
