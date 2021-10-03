package com.jincong.springboot.vo;

import cn.hutool.core.date.DateTime;
import lombok.Data;

@Data
public class  ExportConditionVO {


    Long lastBatchMaxId;

    Integer limit;

    private DateTime startTime;


    private DateTime endTime;
}
