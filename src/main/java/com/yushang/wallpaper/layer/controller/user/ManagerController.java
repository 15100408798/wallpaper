package com.yushang.wallpaper.layer.controller.user;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.config.exception.ValidException;
import com.yushang.wallpaper.layer.model.enums.user.UserLoginLogEnum;
import com.yushang.wallpaper.layer.model.user.ManagerInsertModel;
import com.yushang.wallpaper.layer.model.user.ManagerQueryModel;
import com.yushang.wallpaper.layer.model.user.ManagerUpdateModel;
import com.yushang.wallpaper.layer.service.user.ManagerService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "管理员模块")
@RestController
@RequestMapping(value = "layer/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PermissionName(value = "查询管理员集合")
    @RequiresPermissions(value = {"manager:selectList"})
    @RequiresRoles(value = {"1"})
    @Log(title = "查询管理员集合", tabName = "tb_manager", operateType = 1, logEnum = UserLoginLogEnum.MANAGER_LIST)
    @RequestMapping(value = {"selectList"})
    public ResultFul selectList(ManagerQueryModel managerQueryModel) {
        return managerService.selectList(managerQueryModel);
    }

    @Deprecated
    @PermissionName(value = "删除管理员")
    @RequiresPermissions(value = {"manager:delete"})
    @RequiresRoles(value = {"1"})
    @Log(title = "删除管理员", tabName = "tb_manager", operateType = 2, logEnum = UserLoginLogEnum.DEL_MANAGER)
    @RequestMapping(value = {"deleteManager"})
    public ResultFul deleteManager(ManagerUpdateModel managerUpdateModel) {
        managerUpdateModel.setDeleteFlag(1);
        return managerService.updateTbManager(managerUpdateModel);
    }

    @PermissionName(value = "开启管理员")
    @RequiresPermissions(value = {"manager:openManager"})
    @RequiresRoles(value = {"1"})
    @Log(title = "开启管理员", tabName = "tb_manager", operateType = 3, logEnum = UserLoginLogEnum.OPEN_MANAGER)
    @RequestMapping(value = {"openManager"})
    public ResultFul openManager(ManagerUpdateModel managerUpdateModel) {
        managerUpdateModel.setIsUseStatus(NumberUtils.INTEGER_ZERO);
        return managerService.updateTbManager(managerUpdateModel);
    }

    @PermissionName(value = "禁用管理员")
    @RequiresPermissions(value = {"manager:disabledManager"})
    @RequiresRoles(value = {"1"})
    @Log(title = "禁用管理员", tabName = "tb_manager", operateType = 4, logEnum = UserLoginLogEnum.DISABLED_MANAGER)
    @RequestMapping(value = {"disabledManager"})
    public ResultFul disabledManager(ManagerUpdateModel managerUpdateModel) {
        managerUpdateModel.setIsUseStatus(NumberUtils.INTEGER_ONE);
        return managerService.updateTbManager(managerUpdateModel);
    }

    @PermissionName(value = "添加管理员")
    @RequiresPermissions(value = {"manager:insertManager"})
    @RequiresRoles(value = {"1"})
    @Log(title = "添加管理员", tabName = "tb_manager", operateType = 11, logEnum = UserLoginLogEnum.INSERT_MANAGER)
    @RequestMapping(value = {"insertManager"})
    public ResultFul insertManager(ManagerInsertModel managerInsertModel) {
        return managerService.insertManager(managerInsertModel);
    }

    @PermissionName(value = "查询管理员信息")
    @RequiresPermissions(value = {"manager:selectInfo"})
    @RequiresRoles(value = {"1"})
    @Log(title = "查询管理员信息", tabName = "tb_manager", operateType = 1, logEnum = UserLoginLogEnum.MANAGER_INFO)
    @RequestMapping(value = {"selectInfo"})
    public ResultFul selectInfo(ManagerQueryModel managerQueryModel) throws ValidException {
        managerQueryModel.setDeleteFlag(0);
        return managerService.selectInfo(managerQueryModel);
    }

}
