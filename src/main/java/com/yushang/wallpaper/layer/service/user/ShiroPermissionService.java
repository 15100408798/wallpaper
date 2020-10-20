package com.yushang.wallpaper.layer.service.user;

import com.yushang.wallpaper.common.pojo.shiro.TbPermission;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

/**
 * 角色，权限业务层
 */
public interface ShiroPermissionService {

    /**
     * 根据角色列表查询权限列表
     *
     * @param roleSet 角色列表
     * @return 权限列表
     */
    @Nullable
    Set<String> getPermissionByAccount(@NonNull Set<String> roleSet);

    /**
     * 根据账号id查询角色
     *
     * @param managerId 管理员ID
     * @return 角色信息列表
     */
    @Nullable
    Set<String> getRoleByAccount(@NonNull Short managerId);

    /**
     * 获得权限集合
     */
    @Nullable
    List<String> selectPermissionList();

    /**
     * 保存权限
     */
    void insertPermission(TbPermission tbPermission);


    /**
     * 存入权限与角色关系表
     */
    void insertRolePermission(String[] rolesvlaue, Short permissionId);

}
