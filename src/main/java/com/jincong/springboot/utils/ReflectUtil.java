package com.jincong.springboot.utils;

import com.jincong.springboot.service.AccoutService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/12/14
 */
public class ReflectUtil {

    //动态获取，根据反射，比如获取xx.xx.xx.xx.Action 这个所有的实现类。 xx.xx.xx.xx 表示包名  Action为接口名或者类名
    public static List<Class<?>> getAllActionSubClass(String classPackageAndName) throws Exception {

        List<Class<?>> allSubclass = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> classOfClassLoader = classLoader.getClass();
        Class<?> cls = Class.forName(classPackageAndName);
        while (classOfClassLoader != ClassLoader.class) {
            classOfClassLoader = classOfClassLoader.getSuperclass();
        }
        Field field = classOfClassLoader.getDeclaredField("classes");
        field.setAccessible(true);
        List<Class<?>> clsList = ( List<Class<?>>) field.get(classLoader);
        for (Class<?> c : clsList) {
            if (cls.isAssignableFrom(c) && !cls.equals(c)) {
                allSubclass.add(c);
            }
        }
        return allSubclass;
    }


    public static void main(String[] args) throws Exception {

        String canonicalName = AccoutService.class.getCanonicalName();

        System.out.println(ReflectUtil.getAllActionSubClass(canonicalName));
    }
}
