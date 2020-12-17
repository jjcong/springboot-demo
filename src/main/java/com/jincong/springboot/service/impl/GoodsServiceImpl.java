package com.jincong.springboot.service.impl;

import com.jincong.springboot.mapper.TGoodsCategoryDao;
import com.jincong.springboot.service.IGoodsService;
import com.jincong.springboot.vo.GoodsCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * GoodsServiceImpl
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/12/16
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private TGoodsCategoryDao goodsCategoryDao;
    @Override
    public List<GoodsCategoryVO> listTree() {

        return listGoodsTree();
    }


    /**
     * 获取商品树结构
     * @return
     */
    private List<GoodsCategoryVO> listGoodsTree() {
        List<GoodsCategoryVO> allGoodsCategoryVO = goodsCategoryDao.listAll();

        List<GoodsCategoryVO> first = allGoodsCategoryVO.stream().filter(goods -> goods.getParentId() == 0)
                .peek(item -> item.setChildrenCategoryList(getChildren(item, allGoodsCategoryVO))).collect(Collectors.toList());


        return first;
    }


    /**
     * 递归查询子树
     * @param root                 当前节点的父id
     * @param allGoodsCategoryList 所有商品分类
     * @return
     */
    private List<GoodsCategoryVO> getChildren(GoodsCategoryVO root, List<GoodsCategoryVO> allGoodsCategoryList) {

        List<GoodsCategoryVO> childrenList = allGoodsCategoryList.stream().filter(goods -> goods.getParentId().equals(root.getId()))
                .peek(item -> item.setChildrenCategoryList(getChildren(item, allGoodsCategoryList))).collect(Collectors.toList());
        return childrenList;
    }
}
