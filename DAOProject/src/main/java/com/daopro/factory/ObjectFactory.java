package com.daopro.factory;

import com.daopro.util.ServiceProxy;

import java.util.ResourceBundle;

public class ObjectFactory {
    private static final ResourceBundle DAO_RESOURCE = ResourceBundle.getBundle("dao");
    private static final ResourceBundle SERVICE_RESOURCE = ResourceBundle.getBundle("service");
    private ObjectFactory(){}

    /**
     * 业务层实例化工厂类
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getServiceInstance(String key, Class<T> ... clazz){
        String className = null;
        try{
            className = SERVICE_RESOURCE.getString(key);
        }catch(Exception e){
            if(className == null || "".equals(className)){
                return null;
            }
        }
        try {
            return (T) new ServiceProxy().bind(Class.forName(className).getDeclaredConstructor().newInstance());
        }catch (Exception e){
            return null;
        }
    }
    /**
     * 数据层实例化工厂类
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getDAOInstance(String key, Class<T> ... clazz){
        String className = null;
        try{
            className = DAO_RESOURCE.getString(key);
        }catch(Exception e){
            if(className == null || "".equals(className)){
                return null;
            }
        }
        try{
            return (T) Class.forName(className).getDeclaredConstructor().newInstance();
        }catch(Exception e){
            return null;
        }
    }
}
