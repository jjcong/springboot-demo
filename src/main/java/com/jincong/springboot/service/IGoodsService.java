package com.jincong.springboot.service;

import com.jincong.springboot.vo.GoodsCategoryVO;

import java.util.List;

/**
 * IGoodsService
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/12/16
 */
public interface IGoodsService {

    /**
     * 获取商品分类树
     * @return 树结构
     */
    List<GoodsCategoryVO> listTree();
}
