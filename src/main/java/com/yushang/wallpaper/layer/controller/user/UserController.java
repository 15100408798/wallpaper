package com.yushang.wallpaper.layer.controller.user;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.enums.user.UserLoginLogEnum;
import com.yushang.wallpaper.layer.model.user.UserQueryModel;
import com.yushang.wallpaper.layer.model.user.UserUpdateModel;
import com.yushang.wallpaper.layer.service.user.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "用户模块")
@RestController
@RequestMapping(value = "layer/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PermissionName(value = "查询用户列表")
    @RequiresPermissions(value = {"user:selectList"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "查询用户列表", tabName = "tb_user", operateType = 1, logEnum = UserLoginLogEnum.USER_LIST)
    @RequestMapping(value = {"selectList"})
    public ResultFul selectList(UserQueryModel reqModel) {
        return userService.selectTbUserList(reqModel);
    }

    @PermissionName(value = "删除用户")
    @RequiresPermissions(value = {"user:deleteUser"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "删除用户", tabName = "tb_user", operateType = 2, logEnum = UserLoginLogEnum.DEL_USER)
    @RequestMapping(value = {"deleteUser"})
    public ResultFul deleteUser(UserUpdateModel userUpdateModel) {
        userUpdateModel.setDeleteFlag(1);
        return userService.updateUserInfo(userUpdateModel);
    }

    @PermissionName(value = "开启用户")
    @RequiresPermissions(value = {"user:openUser"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "开启用户", tabName = "tb_user", operateType = 3, logEnum = UserLoginLogEnum.OPEN_USER)
    @RequestMapping(value = {"openUser"})
    public ResultFul openUser(UserUpdateModel userUpdateModel) {
        userUpdateModel.setIsUse(NumberUtils.INTEGER_ZERO);
        return userService.updateUserInfo(userUpdateModel);
    }

    @PermissionName(value = "禁用用户")
    @RequiresPermissions(value = {"user:disabledUser"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "禁用用户", tabName = "tb_user", operateType = 4, logEnum = UserLoginLogEnum.DISABLED_USER)
    @RequestMapping(value = {"disabledUser"})
    public ResultFul disabledUser(UserUpdateModel userUpdateModel) {
        userUpdateModel.setIsUse(NumberUtils.INTEGER_ONE);
        return userService.updateUserInfo(userUpdateModel);
    }


    @PermissionName(value = "查询客户客服列表")
    @RequiresPermissions(value = {"userService:selectCSList"})
    @RequiresRoles(value = {"1"})
    @Log(title = "查询客户客服列表", tabName = "tb_user_service", operateType = 1)
    @RequestMapping("selectCSList")
    public Map<String, Object> selectCSList(
            @RequestParam("page") int page, @RequestParam("size") int size) {
        return userService.selectCSList(page, size);
    }

    @PermissionName(value = "删除客户客服")
    @RequiresPermissions(value = {"userService:deleteSC"})
    @RequiresRoles(value = {"1"})
    @Log(title = "删除客户客服", tabName = "tb_user_service", operateType = 2)
    @RequestMapping("deleteSC")
    public Map<String, Object> deleteSC(String ids) {
        return userService.deleteSC(ids);
    }

    @PermissionName(value = "推荐客户客服")
    @RequiresPermissions(value = {"userService:openSC"})
    @RequiresRoles(value = {"1"})
    @Log(title = "推荐客户客服", tabName = "tb_user_service", operateType = 7)
    @RequestMapping("openSC")
    public Map<String, Object> openSC(String ids) {
        return userService.openSC(ids);
    }

    @PermissionName(value = "不推荐客户客服")
    @RequiresPermissions(value = {"userService:disabledSC"})
    @RequiresRoles(value = {"1"})
    @Log(title = "不推荐客户客服", tabName = "tb_user_service", operateType = 8)
    @RequestMapping("disabledSC")
    public Map<String, Object> disabledSC(String ids) {
        return userService.disabledSC(ids);
    }


}
