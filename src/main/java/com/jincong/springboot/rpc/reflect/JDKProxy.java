package com.jincong.springboot.rpc.reflect;

import com.jincong.springboot.rpc.network.msg.Request;
import com.jincong.springboot.utils.ClassLoaderUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥
 */
public class JDKProxy {

    public static <T> T getProxy(Class<T> interfaceClass, Request request) {
        InvocationHandler handler = new JDKInvocationHandler(request);
        ClassLoader classLoader = ClassLoaderUtils.getCurrentClassLoader();
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{interfaceClass}, handler);
    }

}
