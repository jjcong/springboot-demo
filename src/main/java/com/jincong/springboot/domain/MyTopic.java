package com.jincong.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/12/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyTopic {


    /**
     * topic名称
     */
    private String topicName;

    /**
     * 分区数
     */
    private int partition;

    /**
     * 副本数
     */
    private int replication;
}
