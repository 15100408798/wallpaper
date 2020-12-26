package com.yushang.wallpaper.layer.router.store;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.store.productLabel.LabelQueryModel;
import com.yushang.wallpaper.layer.model.store.productLabel.LabelUpdateModel;
import org.springframework.lang.NonNull;

/**
 * 商品类别模块
 */
public interface ProductLabelService {

    /**
     * 查询商品类别信息列表
     *
     * @param queryModel 筛选参数
     * @return 商品类别信息列表
     */
    @NonNull
    ResultFul selectLabelList(@NonNull LabelQueryModel queryModel);

    /**
     * 更新商品类别信息
     *
     * @param labelUpdateModel 商品类别信息
     * @return 受影响条数
     */
    @NonNull
    ResultFul updateProductLabel(@NonNull LabelUpdateModel labelUpdateModel);

    /**
     * 新增商品类别
     *
     * @param labelUpdateModel 商品类别信息
     * @return 受影响条数
     */
    @NonNull
    ResultFul insertProductLabel(@NonNull LabelUpdateModel labelUpdateModel);
}
