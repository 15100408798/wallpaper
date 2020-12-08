package com.yushang.wallpaper.service.user;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.config.exception.ValidException;
import com.yushang.wallpaper.model.user.ManagerInsertModel;
import com.yushang.wallpaper.model.user.ManagerQueryModel;
import com.yushang.wallpaper.model.user.ManagerUpdateModel;
import org.springframework.lang.NonNull;

/**
 * 管理员模块
 */
public interface ManagerService {

    /**
     * 查询管理员集合
     *
     * @param managerQueryModel 查询参数
     * @return 管理员信息列表
     */
    @NonNull
    ResultFul selectList(@NonNull ManagerQueryModel managerQueryModel);

    /**
     * 更新管理员信息
     *
     * @param managerUpdateModel 更新参数
     * @return 受影响条数
     */
    @NonNull
    ResultFul updateTbManager(@NonNull ManagerUpdateModel managerUpdateModel);

    /**
     * 新增管理员信息
     *
     * @param managerInsertModel 管理员信息
     * @return 受影响条数
     */
    @NonNull
    ResultFul insertManager(@NonNull ManagerInsertModel managerInsertModel);

    /**
     * 查询管理员信息
     *
     * @param managerQueryModel 查询参数
     * @return 管理员信息
     */
    @NonNull
    ResultFul selectInfo(@NonNull ManagerQueryModel managerQueryModel) throws ValidException;

    /**
     * 更新管理员信息
     *
     * @param managerUpdateModel 管理员信息
     * @return 受影响条数
     */
    @NonNull
    ResultFul updateInfo(@NonNull ManagerUpdateModel managerUpdateModel);
}
