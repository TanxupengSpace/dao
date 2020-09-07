package com.daopro.util;

public class StringUtil {
    private StringUtil(){}
    public static String stringcap(String param){
        if(param == null){
            return null;
        }
        return param.substring(0, 1).toUpperCase() + param.substring(1);
    }
}
