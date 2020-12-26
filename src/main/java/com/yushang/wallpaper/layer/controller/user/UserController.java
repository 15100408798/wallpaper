package com.yushang.wallpaper.layer.controller.user;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.enums.StatusEnum;
import com.yushang.wallpaper.common.pojo.user.TbManager;
import com.yushang.wallpaper.layer.model.enums.LogEnum;
import com.yushang.wallpaper.layer.model.user.user.UserInsertModel;
import com.yushang.wallpaper.layer.model.user.user.UserQueryModel;
import com.yushang.wallpaper.layer.model.user.user.UserUpdateModel;
import com.yushang.wallpaper.layer.router.user.UserService;
import io.swagger.annotations.Api;
import org.apache.http.HttpStatus;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        userUpdateModel.setDeleteFlag(StatusEnum.DELETE_YES.getCode());
        return userService.updateUserInfo(userUpdateModel);
    }

    @PermissionName(value = "开启用户")
    @RequiresPermissions(value = {"user:openUser"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "开启用户", tabName = "tb_user", operateType = 3, logEnum = LogEnum.OPEN_USER)
    @RequestMapping(value = {"openUser"})
    public ResultFul openUser(UserUpdateModel userUpdateModel) {
        userUpdateModel.setIsUse(StatusEnum.AVAILABLE.getCode());
        return userService.updateUserInfo(userUpdateModel);
    }

    @PermissionName(value = "禁用用户")
    @RequiresPermissions(value = {"user:disabledUser"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "禁用用户", tabName = "tb_user", operateType = 4, logEnum = LogEnum.DISABLED_USER)
    @RequestMapping(value = {"disabledUser"})
    public ResultFul disabledUser(UserUpdateModel userUpdateModel) {
        userUpdateModel.setIsUse(StatusEnum.FORBIDDEN.getCode());
        return userService.updateUserInfo(userUpdateModel);
    }

    @PermissionName(value = "添加用户")
    @RequiresPermissions(value = {"user:insertUserInfo"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "添加用户", tabName = "tb_user", operateType = 11, logEnum = LogEnum.ADD_USER)
    @RequestMapping(value = {"insertUserInfo"})
    public ResultFul insertUserInfo(UserInsertModel userInsertModel) {
        return userService.insertUserInfo(userInsertModel);
    }

    @PermissionName(value = "检查账号是否可用")
    @RequiresPermissions(value = {"user:checkUsername"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "检查账号是否可用", tabName = "tb_user", operateType = 1, logEnum = LogEnum.USER_LIST)
    @RequestMapping(value = {"checkUsername"})
    public ResultFul checkUsername(UserQueryModel userQueryModel) {
        userQueryModel.setDeleteFlag(StatusEnum.DELETE_NO.getCode());
        ResultFul resultFul = userService.selectTbUserList(userQueryModel);
        if (resultFul.getStatus() != 200) {
            return resultFul;
        }
        List<TbManager> tbManagerList = (List<TbManager>) resultFul.getRows();
        if (CollectionUtils.isEmpty(tbManagerList)) {
            resultFul.setMessage("账号可用");
        } else {
            resultFul.setMessage("账号已存在");
            resultFul.setStatus(HttpStatus.SC_BAD_REQUEST);
        }
        return resultFul;
    }

    @PermissionName(value = "更新用户")
    @RequiresPermissions(value = {"user:updateUser"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "更新用户", tabName = "tb_user", operateType = 13, logEnum = LogEnum.UPDATE_USER)
    @RequestMapping(value = {"updateUser"})
    public ResultFul updateUser(UserUpdateModel userUpdateModel) {
        return userService.updateUserInfo(userUpdateModel);
    }

}
