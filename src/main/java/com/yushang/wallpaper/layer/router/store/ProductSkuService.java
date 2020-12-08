package com.yushang.wallpaper.layer.router.store;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.model.store.ProductSkuQueryModel;
import com.yushang.wallpaper.model.store.ProductSkuUpdateModel;
import org.springframework.lang.NonNull;

/**
 * 商品Sku模块
 */
public interface ProductSkuService {

    /**
     * 查询商品sku集合
     *
     * @param productSkuQueryModel 查询参数
     * @return 商品sku信息列表
     */
    @NonNull
    ResultFul selectSkuList(@NonNull ProductSkuQueryModel productSkuQueryModel);

    /**
     * 更新商品sku信息
     *
     * @param productSkuUpdateModel 商品sku
     * @return 受影响条数
     */
    @NonNull
    ResultFul updateProductSkuInfo(@NonNull ProductSkuUpdateModel productSkuUpdateModel);
}
