package com.yushang.wallpaper.service.store;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.model.store.ShopQueryModel;
import com.yushang.wallpaper.model.store.ShopUpdateModel;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * 商户管理模块
 */
public interface ShopService {

    /**
     * 查询商户信息列表
     *
     * @param shopQueryModel 查询参数
     * @return 商户信息列表
     */
    @NonNull
    ResultFul selectStoreList(@NonNull ShopQueryModel shopQueryModel);

    /**
     * 更新商户信息列表
     *
     * @param shopUpdateModel 商户信息列表
     * @return 受影响条数
     */
    @NonNull
    ResultFul updateShopInfo(@NonNull ShopUpdateModel shopUpdateModel);

    List<Long> selectShopIdList();

    void saveShopOneWeek();
}
