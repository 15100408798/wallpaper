package com.yushang.wallpaper.layer.router.store;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.store.product.ProductInsertModel;
import com.yushang.wallpaper.layer.model.store.product.ProductQueryModel;
import com.yushang.wallpaper.layer.model.store.product.ProductUpdateModel;
import org.springframework.lang.NonNull;

/**
 * 商品管理模块
 */
public interface ProductService {

    /**
     * 查询商品信息列表
     *
     * @param reqModel 请求参数
     * @return 商品信息列表
     */
    @NonNull
    ResultFul selectList(@NonNull ProductQueryModel reqModel);

    /**
     * 更新商品信息
     *
     * @param productUpdateModel 商品信息
     * @return 受影响条数
     */
    @NonNull
    ResultFul updateProductInfo(@NonNull ProductUpdateModel productUpdateModel);

    /**
     * 新增商品信息
     *
     * @param productInsertModel 商品信息
     * @return 受影响条数
     */
    @NonNull
    ResultFul insertProductInfo(@NonNull ProductInsertModel productInsertModel);
}
