package com.jincong.springboot.spi;

import lombok.extern.slf4j.Slf4j;

/**
 * QiyiCDN
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/7/25
 */
@Slf4j
public class QiyiCDN implements UploadCDN {
    @Override
    public void upload(String url) {
        log.info("Upload to Qiyi Cdn , {}" , url);
    }
}
