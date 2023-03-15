package com.heima.reggie.common;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.common
 * @Author: Little Brother
 * @CreateTime: 2023-03-08  11:07
 * @Version: 1.0
 * @Description: TODO
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}

