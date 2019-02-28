package com.itkang.blog.utils;

import java.util.List;

/**
 * Created by mis on 2018/12/18.
 */
public class ParamCheckUtils {
    /**Long 判空**/
    public static boolean isBlank(Long param){
        return param == null || param <= 0;
    }
    /**Long 判空**/
    public static boolean isBlank(Integer param){
        return param == null || param <= 0;
    }
    /**判断list**/
    public  static  boolean isBlank(List list){
        return  list == null || list.isEmpty();
    }

    public  static  boolean isBlank(String param){
        return  param == null || param.isEmpty() || param.equals("");
    }

    /**Short 判空**/
    public static boolean isBlank(Short param){
        return param == null || param < 0;
    }

    public static boolean isBlank(Byte param) {
        return param == null || param == 0;
    }
}
