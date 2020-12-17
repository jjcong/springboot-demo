package com.jincong.springboot.mapper;


import com.jincong.springboot.vo.GoodsCategoryVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;
public interface TGoodsCategoryDao {

    @Select("select id, name, parent_id parentId, cat_level catLevel, create_time createTime, last_update_time lastUpdateTime from t_goods_category")
    List<GoodsCategoryVO> listAll();
}