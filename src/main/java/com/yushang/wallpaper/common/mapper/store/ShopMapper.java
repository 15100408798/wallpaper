package com.yushang.wallpaper.common.mapper.store;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.store.TbShop;
import com.yushang.wallpaper.layer.model.store.ShopQueryModel;
import com.yushang.wallpaper.layer.model.store.ShopUpdateModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopMapper {

    /**
     * 查询商户信息列表
     *
     * @param shopQueryModel 查询参数
     * @return 商户信息列表
     */
    Page<TbShop> selectStoreList(ShopQueryModel shopQueryModel);

    List<Map<String, Object>> getWXBaoYang();

    String getShopPhoneById(@Param("shopId") Integer shopId);

    List<Long> selectShopIdList();

    /**
     * 更新商户信息列表
     *
     * @param shopUpdateModel 商户信息列表
     * @return 受影响条数
     */
    int updateShopInfo(ShopUpdateModel shopUpdateModel);
}
