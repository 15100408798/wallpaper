package com.yushang.wallpaper.controller.user;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.model.enums.LogEnum;
import com.yushang.wallpaper.model.user.RoleInsertModel;
import com.yushang.wallpaper.model.user.RoleQueryModel;
import com.yushang.wallpaper.model.user.RoleUpdateModel;
import com.yushang.wallpaper.service.user.RoleService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "角色模块")
@RestController
@RequestMapping(value = "layer/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @PermissionName(value = "查询角色列表")
    @RequiresPermissions(value = {"role:selectList"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "查询角色列表", tabName = "tb_role", operateType = 1, logEnum = LogEnum.ROLE_LIST)
    @RequestMapping(value = {"selectList"})
    public ResultFul selectList(RoleQueryModel roleQueryModel) {
        return roleService.selectList(roleQueryModel);
    }

    @PermissionName(value = "删除角色信息")
    @RequiresPermissions(value = {"role:deleteRole"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "删除角色信息", tabName = "tb_role", operateType = 2, logEnum = LogEnum.DEL_ROLE)
    @RequestMapping(value = {"deleteRole"})
    public ResultFul deleteRole(RoleUpdateModel roleUpdateModel) {
        roleUpdateModel.setDeleteFlag(1);
        return roleService.updateRoleInfo(roleUpdateModel);
    }

    @PermissionName(value = "添加角色信息")
    @RequiresPermissions(value = {"role:insertRole"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "添加角色信息", tabName = "tb_role", operateType = 11, logEnum = LogEnum.ADD_ROLE)
    @RequestMapping(value = {"insertRole"})
    public ResultFul insertRole(RoleInsertModel roleInsertModel) {
        return roleService.insertRole(roleInsertModel);
    }

}
