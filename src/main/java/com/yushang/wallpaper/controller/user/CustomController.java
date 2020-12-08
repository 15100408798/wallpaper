package com.yushang.wallpaper.controller.user;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.model.enums.LogEnum;
import com.yushang.wallpaper.model.user.CustomQueryModel;
import com.yushang.wallpaper.model.user.CustomUpdateModel;
import com.yushang.wallpaper.service.user.CustomService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "客服模块")
@RestController
@RequestMapping(value = "layer/custom")
public class CustomController {

    @Resource
    private CustomService customService;

    @PermissionName(value = "查询客户客服列表")
    @RequiresPermissions(value = {"custom:selectList"})
    @RequiresRoles(value = {"1"})
    @Log(title = "查询客户客服列表", tabName = "tb_custom", operateType = 1, logEnum = LogEnum.CUSTOM_LIST)
    @RequestMapping("selectList")
    public ResultFul selectList(CustomQueryModel customQueryModel) {
        return customService.selectList(customQueryModel);
    }

    @PermissionName(value = "删除客户客服")
    @RequiresPermissions(value = {"custom:deleteCustom"})
    @RequiresRoles(value = {"1"})
    @Log(title = "删除客户客服", tabName = "tb_custom", operateType = 2, logEnum = LogEnum.DEL_CUSTOM)
    @RequestMapping("deleteCustom")
    public ResultFul deleteCustom(CustomUpdateModel customUpdateModel) {
        customUpdateModel.setDeleteFlag(1);
        return customService.updateCustomInfo(customUpdateModel);
    }

}
