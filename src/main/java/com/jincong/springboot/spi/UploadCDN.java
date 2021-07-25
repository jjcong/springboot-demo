package com.jincong.springboot.spi;

/**
 * UploadCDN
 * 使用SPI扩展接口
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/7/25
 */
public interface UploadCDN {

    /**
     * 上传接口
     * @param url
     */
    void upload(String url);
}
