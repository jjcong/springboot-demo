package com.jincong.springboot.domain;

import lombok.Builder;
import lombok.Data;

/**
 * Food
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/1/9
 */

@Data
@Builder
//@ConfigurationProperties(prefix = "food")
public class Food {

    private String name;

    private Integer price;

}
