package com.yushang.wallpaper.layer.router.user;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.model.user.RoleInsertModel;
import com.yushang.wallpaper.model.user.RoleQueryModel;
import com.yushang.wallpaper.model.user.RoleUpdateModel;
import org.springframework.lang.NonNull;

/**
 * 角色模块
 */
public interface RoleService {

    /**
     * 查询角色信息列表
     *
     * @param roleQueryModel 查询参数
     * @return 角色信息列表
     */
    @NonNull
    ResultFul selectList(@NonNull RoleQueryModel roleQueryModel);

    /**
     * 更新角色信息
     *
     * @param roleUpdateModel 角色信息
     * @return 受影响条数
     */
    @NonNull
    ResultFul updateRoleInfo(@NonNull RoleUpdateModel roleUpdateModel);

    /**
     * 添加角色信息
     *
     * @param roleInsertModel 角色信息
     * @return 受影响条数
     */
    @NonNull
    ResultFul insertRole(@NonNull RoleInsertModel roleInsertModel);
}
