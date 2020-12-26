package com.yushang.wallpaper.layer.router.user;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.user.custom.CustomInsertModel;
import com.yushang.wallpaper.layer.model.user.custom.CustomQueryModel;
import com.yushang.wallpaper.layer.model.user.custom.CustomUpdateModel;
import org.springframework.lang.NonNull;

/**
 * 客服模块
 */
public interface CustomService {

    /**
     * 查询客户客服列表
     *
     * @param customQueryModel 查询参数
     * @return 客户客服列表
     */
    @NonNull
    ResultFul selectList(@NonNull CustomQueryModel customQueryModel);

    /**
     * 更新客户客服信息
     *
     * @param customUpdateModel 客户客服信息
     * @return 受影响条数
     */
    @NonNull
    ResultFul updateCustomInfo(@NonNull CustomUpdateModel customUpdateModel);

    /**
     * 新增客户客服信息
     *
     * @param customInsertModel 客户客服信息
     * @return 受影响条数
     */
    @NonNull
    ResultFul insertCustom(@NonNull CustomInsertModel customInsertModel);
}
