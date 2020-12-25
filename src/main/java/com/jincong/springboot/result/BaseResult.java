package com.jincong.springboot.result;

import lombok.Data;

/**
 * 封装结果类
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/04/05
 */
@Data
public class BaseResult<T> {

    /**
     * 后台数据
     */
    private T data;
    /**
     * 状态码， 0-成功
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;

    /**
     * 若没有数据返回，默认状态码为200， 提示成功
     */
    public BaseResult() {
        this.code = 200;
        this.msg = "成功";
    }

    /**
     * 若没数据返回，自定义状态码和提示信息
     *
     * @param code 后台数据
     * @param msg  提示信息
     */
    public BaseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 有数据返回，状态码为0，默认提示成功
     *
     * @param data 后台返回数据
     */
    public BaseResult(T data) {
        this.data = data;
        this.code = 200;
        this.msg = "成功";
    }

    /**
     * 有数据返回，状态码为0， 自定义提示信息
     *
     * @param data 后台数据
     * @param msg  自定义返回信息
     */
    public BaseResult(T data, String msg) {
        this.data = data;
        this.code = 200;
        this.msg = msg;
    }


}
