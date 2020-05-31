package com.jincong.springboot.config;

/**
 * 性别枚举类
 *
 * @author  j_cong
 * @date    2020/05/21
 * @version V1.0
 */
public enum SexEnum {
    /**
     * 女
     */
    FEMAN(0),
    /**
     * 男
     */
    MAN(1),
    /**
     * 未知
     */
    UNKONWN(2);


    private int code;

    SexEnum(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

    public static SexEnum find(int code) {

       for (SexEnum item : SexEnum.values()) {
           if (item.code == code) {
               return item;
           }
       }

       return null;
    }


}
