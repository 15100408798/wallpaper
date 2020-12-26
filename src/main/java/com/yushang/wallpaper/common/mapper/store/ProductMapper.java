package com.yushang.wallpaper.common.mapper.store;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.store.TbProduct;
import com.yushang.wallpaper.layer.model.store.product.ProductInsertModel;
import com.yushang.wallpaper.layer.model.store.product.ProductQueryModel;
import com.yushang.wallpaper.layer.model.store.product.ProductUpdateModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ProductMapper {
    /**
     * 查询商品信息列表
     *
     * @param reqModel 请求参数
     * @return 商品信息列表
     */
    Page<TbProduct> selectList(ProductQueryModel reqModel);

    List<HashMap<String, Object>> shopList(@Param("type") Integer type);

    /**
     * 更新商品信息
     *
     * @param productUpdateModel 商品信息
     * @return 手影响条数
     */
    int updateProductInfo(ProductUpdateModel productUpdateModel);

    /**
     * 新增商品信息
     *
     * @param productInsertModel 商品信息
     * @return 受影响条数
     */
    int insertProductInfo(ProductInsertModel productInsertModel);
}
