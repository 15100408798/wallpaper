package com.yushang.wallpaper.common.mapper.shiro;

import com.yushang.wallpaper.common.pojo.shiro.TbPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface ShiroPermissionMapper {

    /**
     * 根据角色列表查询权限列表
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    Set<String> getPermissionByAccount(@Param("roleId") String roleId);

    /**
     * 根据账号id查询角色
     *
     * @param managerId 管理员ID
     * @return 角色信息列表
     */
    Set<String> getRoleByAccount(@Param("managerId") Short managerId);

    /**
     * 获得权限集合
     */
    List<String> selectPermissionList();

    /**
     * 保存权限
     */
    void insertPermission(TbPermission tbPermission);

    /**
     * 存入权限与角色关系表
     */
    void insertRolePermission(@Param("roleId") String roleVlaue, @Param("permissionId") Short permissionId);

}
