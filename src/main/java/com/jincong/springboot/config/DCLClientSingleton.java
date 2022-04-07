package com.jincong.springboot.config;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


/**
 * @author j_cong
 * @version V1.0
 * @date 2022/3/31
 */
public class DCLClientSingleton {

    private DCLClientSingleton() {
    }

    private static volatile Client client;


    public static Client getClient() {
        if (client == null) {
            synchronized (DCLClientSingleton.class) {
                if (client == null) {
                    JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
                    client  = factory.createClient("wsdlUrl");
                }
            }
        }
        return client;
    }

}
