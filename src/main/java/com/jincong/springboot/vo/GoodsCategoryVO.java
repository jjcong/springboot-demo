package com.jincong.springboot.vo;

import com.jincong.springboot.domain.GoodsCategory;
import lombok.Data;

import java.util.List;

/**
 * GoodsCategoryVO
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/12/16
 */
@Data
public class GoodsCategoryVO extends GoodsCategory {

    /**
     * 子类别树
     */
    List<GoodsCategoryVO> childrenCategoryList;
}
