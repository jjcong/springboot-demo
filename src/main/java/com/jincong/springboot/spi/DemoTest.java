package com.jincong.springboot.spi;

import java.util.ServiceLoader;

/**
 * DemoTest
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/7/25
 */
public class DemoTest {

    public static void main(String[] args) {
        ServiceLoader<UploadCDN> serviceLoader = ServiceLoader.load(UploadCDN.class);
        serviceLoader.forEach(loader -> loader.upload("myFile"));
    }



}
