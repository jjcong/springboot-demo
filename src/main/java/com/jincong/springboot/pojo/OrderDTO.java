package com.jincong.springboot.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDTO {

    @ExcelIgnore
    private Long id;

    private Byte deleted;
    @ExcelProperty(value = "订单号", order = 1)
    private String orderId;

    @ExcelProperty(value = "金额", order = 2)
    private BigDecimal amount;

    @ExcelProperty(value = "支付时间", order = 3)
    private Date paymentTime;

    @ExcelProperty(value = "订单状态", order = 4)
    private Byte orderStatus;


}