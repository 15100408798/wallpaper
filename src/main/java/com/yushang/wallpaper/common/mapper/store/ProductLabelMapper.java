package com.yushang.wallpaper.common.mapper.store;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.store.TbProductLabel;
import com.yushang.wallpaper.model.store.LabelQueryModel;
import com.yushang.wallpaper.model.store.LabelUpdateModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 商品类别管理
 */
@Mapper
public interface ProductLabelMapper {

    /**
     * 查询商品类别信息列表
     *
     * @param reqModel
     * @return
     */
    Page<TbProductLabel> selectLabelList(LabelQueryModel reqModel);

    /**
     * 更新商品类别信息
     *
     * @param labelUpdateModel 商品类别信息
     * @return 受影响条数
     */
    int updateProductLabel(LabelUpdateModel labelUpdateModel);

    List<HashMap<String, Object>> shopLabel(Byte typeId);

    /**
     * 新增商品类别
     *
     * @param labelUpdateModel 商品类别信息
     * @return 受影响条数
     */
    int insertProductLabel(LabelUpdateModel labelUpdateModel);
}
