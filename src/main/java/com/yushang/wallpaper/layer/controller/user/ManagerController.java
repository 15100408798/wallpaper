package com.yushang.wallpaper.layer.controller.user;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.config.exception.ValidException;
import com.yushang.wallpaper.common.enums.StatusEnum;
import com.yushang.wallpaper.common.pojo.user.TbManager;
import com.yushang.wallpaper.layer.model.enums.LogEnum;
import com.yushang.wallpaper.layer.model.user.manager.ManagerInsertModel;
import com.yushang.wallpaper.layer.model.user.manager.ManagerQueryModel;
import com.yushang.wallpaper.layer.model.user.manager.ManagerUpdateModel;
import com.yushang.wallpaper.layer.router.user.ManagerService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "管理员模块")
@RestController
@RequestMapping(value = "layer/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PermissionName(value = "查询管理员集合")
    @RequiresPermissions(value = {"manager:selectList"})
    @RequiresRoles(value = {"1"})
    @Log(title = "查询管理员集合", tabName = "tb_manager", operateType = 1, logEnum = LogEnum.MANAGER_LIST)
    @RequestMapping(value = {"selectList"})
    public ResultFul selectList(ManagerQueryModel managerQueryModel) {
        return managerService.selectList(managerQueryModel);
    }

    @Deprecated
    @PermissionName(value = "删除管理员")
    @RequiresPermissions(value = {"manager:delete"})
    @RequiresRoles(value = {"1"})
    @Log(title = "删除管理员", tabName = "tb_manager", operateType = 2, logEnum = LogEnum.DEL_MANAGER)
    @RequestMapping(value = {"deleteManager"})
    public ResultFul deleteManager(ManagerUpdateModel managerUpdateModel) {
        managerUpdateModel.setDeleteFlag(StatusEnum.DELETE_YES.getCode());
        return managerService.updateTbManager(managerUpdateModel);
    }

    @PermissionName(value = "开启管理员")
    @RequiresPermissions(value = {"manager:openManager"})
    @RequiresRoles(value = {"1"})
    @Log(title = "开启管理员", tabName = "tb_manager", operateType = 3, logEnum = LogEnum.OPEN_MANAGER)
    @RequestMapping(value = {"openManager"})
    public ResultFul openManager(ManagerUpdateModel managerUpdateModel) {
        managerUpdateModel.setIsUseStatus(NumberUtils.INTEGER_ZERO);
        return managerService.updateTbManager(managerUpdateModel);
    }

    @PermissionName(value = "禁用管理员")
    @RequiresPermissions(value = {"manager:disabledManager"})
    @RequiresRoles(value = {"1"})
    @Log(title = "禁用管理员", tabName = "tb_manager", operateType = 4, logEnum = LogEnum.DISABLED_MANAGER)
    @RequestMapping(value = {"disabledManager"})
    public ResultFul disabledManager(ManagerUpdateModel managerUpdateModel) {
        managerUpdateModel.setIsUseStatus(NumberUtils.INTEGER_ONE);
        return managerService.updateTbManager(managerUpdateModel);
    }

    @PermissionName(value = "添加管理员")
    @RequiresPermissions(value = {"manager:insertManager"})
    @RequiresRoles(value = {"1"})
    @Log(title = "添加管理员", tabName = "tb_manager", operateType = 11, logEnum = LogEnum.INSERT_MANAGER)
    @RequestMapping(value = {"insertManager"})
    public ResultFul insertManager(ManagerInsertModel managerInsertModel) {
        return managerService.insertManager(managerInsertModel);
    }

    @PermissionName(value = "查询管理员信息")
    @RequiresPermissions(value = {"manager:selectInfo"})
    @RequiresRoles(value = {"1"})
    @Log(title = "查询管理员信息", tabName = "tb_manager", operateType = 1, logEnum = LogEnum.MANAGER_INFO)
    @RequestMapping(value = {"selectInfo"})
    public ResultFul selectInfo(ManagerQueryModel managerQueryModel) throws ValidException {
        managerQueryModel.setDeleteFlag(0);
        return managerService.selectInfo(managerQueryModel);
    }

    @PermissionName(value = "更新管理员信息")
    @RequiresPermissions(value = {"manager:updateInfo"})
    @RequiresRoles(value = {"1"})
    @Log(title = "更新管理员信息", tabName = "tb_manager", operateType = 13, logEnum = LogEnum.UPDATE_MANAGER)
    @RequestMapping(value = {"updateInfo"})
    public ResultFul updateInfo(ManagerUpdateModel managerUpdateModel) throws ValidException {
        return managerService.updateTbManager(managerUpdateModel);
    }

    @PermissionName(value = "检查账号是否可用")
    @RequiresPermissions(value = {"manager:checkUsername"})
    @RequiresRoles(value = {"1"})
    @Log(title = "检查账号是否可用", tabName = "tb_manager", operateType = 1, logEnum = LogEnum.MANAGER_INFO)
    @RequestMapping(value = {"checkUsername"})
    public ResultFul checkUsername(ManagerQueryModel managerQueryModel) {
        managerQueryModel.setDeleteFlag(StatusEnum.DELETE_NO.getCode());
        ResultFul resultFul = managerService.selectList(managerQueryModel);
        if (resultFul.getStatus() != 200) {
            return resultFul;
        }
        List<TbManager> tbManagerList = (List<TbManager>) resultFul.getRows();
        if (CollectionUtils.isEmpty(tbManagerList)) {
            resultFul.setMessage("账号可用");
        } else {
            resultFul.setMessage( "账号已存在");
            resultFul.setStatus(HttpStatus.SC_BAD_REQUEST);
        }
        return resultFul;
    }

}
