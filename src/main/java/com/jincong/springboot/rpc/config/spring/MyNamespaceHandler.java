package com.jincong.springboot.rpc.config.spring;

import com.jincong.springboot.rpc.config.spring.bean.ConsumerBean;
import com.jincong.springboot.rpc.config.spring.bean.ProviderBean;
import com.jincong.springboot.rpc.config.spring.bean.ServerBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("server", new MyBeanDefinitionParser(ServerBean.class));
        registerBeanDefinitionParser("provider", new MyBeanDefinitionParser(ProviderBean.class));
        registerBeanDefinitionParser("consumer", new MyBeanDefinitionParser(ConsumerBean.class));
    }

}
