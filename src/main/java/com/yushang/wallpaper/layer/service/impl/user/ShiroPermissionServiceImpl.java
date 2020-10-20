package com.yushang.wallpaper.layer.service.impl.user;

import com.yushang.wallpaper.common.mapper.shiro.ShiroPermissionMapper;
import com.yushang.wallpaper.common.pojo.shiro.TbPermission;
import com.yushang.wallpaper.layer.service.user.ShiroPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ShiroPermissionServiceImpl implements ShiroPermissionService {

    @Resource
    private ShiroPermissionMapper shiroPermissionMapper;

    /**
     * 根据角色列表查询权限列表
     *
     * @param roleSet 角色列表
     * @return 权限列表
     */
    @Transactional(readOnly = true)
    @Override
    public Set<String> getPermissionByAccount(Set<String> roleSet) {
        /* 校验参数 */
        if (CollectionUtils.isEmpty(roleSet)) {
            throw new NullPointerException();
        }
        // 获得角色
        Set<String> permissionSet = new HashSet<>();
        for (String roleId : roleSet) {
            permissionSet.addAll(shiroPermissionMapper.getPermissionByAccount(roleId));   // 查询权限
        }
        return permissionSet;
    }

    /**
     * 根据账号id查询角色
     *
     * @param managerId 管理员ID
     * @return 角色信息列表
     */
    @Transactional(readOnly = true)
    @Override
    public Set<String> getRoleByAccount(Short managerId) {
        Objects.requireNonNull(managerId);
        return shiroPermissionMapper.getRoleByAccount(managerId);
    }


    /**
     * 获得权限集合
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> selectPermissionList() {
        return shiroPermissionMapper.selectPermissionList();
    }

    /**
     * 保存权限
     */
    @Transactional
    @Override
    public void insertPermission(TbPermission tbPermission) {
        shiroPermissionMapper.insertPermission(tbPermission);
    }

    /**
     * 存入权限与角色关系表
     */
    @Transactional
    @Override
    public void insertRolePermission(String[] rolesvlaue, Short permissionId) {
        for (int i = 0; i < rolesvlaue.length; i++) {
            String roleVlaue = rolesvlaue[i];
            shiroPermissionMapper.insertRolePermission(roleVlaue, permissionId);
        }
    }

}
