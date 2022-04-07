package com.jincong.springboot.config;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


/**
 * @author j_cong
 * @version V1.0
 * @date 2022/3/31
 */
public class WebServiceClientSingleton {

    private WebServiceClientSingleton() {
    }


    private static class InstanceHolder {
        //实例化JaxWsDynamicClientFactory
        private static final JaxWsDynamicClientFactory CLIENT_FACTORY = JaxWsDynamicClientFactory.newInstance();
        private static final Client CLIENT = CLIENT_FACTORY.createClient("wsdlUrl");
    }


    private static Client getClientInstance() {
        return InstanceHolder.CLIENT;
    }
}
