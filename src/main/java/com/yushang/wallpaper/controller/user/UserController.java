package com.yushang.wallpaper.controller.user;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.model.enums.LogEnum;
import com.yushang.wallpaper.model.user.UserQueryModel;
import com.yushang.wallpaper.model.user.UserUpdateModel;
import com.yushang.wallpaper.service.user.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户模块")
@RestController
@RequestMapping(value = "layer/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PermissionName(value = "查询用户列表")
    @RequiresPermissions(value = {"user:selectList"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "查询用户列表", tabName = "tb_user", operateType = 1, logEnum = LogEnum.USER_LIST)
    @RequestMapping(value = {"selectList"})
    public ResultFul selectList(UserQueryModel reqModel) {
        return userService.selectTbUserList(reqModel);
    }

    @PermissionName(value = "删除用户")
    @RequiresPermissions(value = {"user:deleteUser"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "删除用户", tabName = "tb_user", operateType = 2, logEnum = LogEnum.DEL_USER)
    @RequestMapping(value = {"deleteUser"})
    public ResultFul deleteUser(UserUpdateModel userUpdateModel) {
        userUpdateModel.setDeleteFlag(1);
        return userService.updateUserInfo(userUpdateModel);
    }

    @PermissionName(value = "开启用户")
    @RequiresPermissions(value = {"user:openUser"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "开启用户", tabName = "tb_user", operateType = 3, logEnum = LogEnum.OPEN_USER)
    @RequestMapping(value = {"openUser"})
    public ResultFul openUser(UserUpdateModel userUpdateModel) {
        userUpdateModel.setIsUse(NumberUtils.INTEGER_ZERO);
        return userService.updateUserInfo(userUpdateModel);
    }

    @PermissionName(value = "禁用用户")
    @RequiresPermissions(value = {"user:disabledUser"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "禁用用户", tabName = "tb_user", operateType = 4, logEnum = LogEnum.DISABLED_USER)
    @RequestMapping(value = {"disabledUser"})
    public ResultFul disabledUser(UserUpdateModel userUpdateModel) {
        userUpdateModel.setIsUse(NumberUtils.INTEGER_ONE);
        return userService.updateUserInfo(userUpdateModel);
    }


}
