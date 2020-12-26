package com.yushang.wallpaper.common.mapper.store;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.store.TbProductSku;
import com.yushang.wallpaper.layer.model.store.productSku.ProductSkuInsertModel;
import com.yushang.wallpaper.layer.model.store.productSku.ProductSkuQueryModel;
import com.yushang.wallpaper.layer.model.store.productSku.ProductSkuUpdateModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductSkuMapper {

    /**
     * 查询商品sku集合
     *
     * @param productSkuQueryModel 查询参数
     * @return 商品sku信息列表
     */
    Page<TbProductSku> selectSkuList(ProductSkuQueryModel productSkuQueryModel);

    /**
     * 更新商品sku信息
     *
     * @param productSkuUpdateModel 商品sku
     * @return 受影响条数
     */
    int updateProductSkuInfo(ProductSkuUpdateModel productSkuUpdateModel);

    /**
     * 新增商品sku信息
     *
     * @param productSkuInsertModel 商品sku
     * @return 受影响条数
     */
    int insertSkuProduct(ProductSkuInsertModel productSkuInsertModel);
}
