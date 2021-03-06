package com.yushang.wallpaper.layer.controller.log;


import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.enums.user.UserLoginLogEnum;
import com.yushang.wallpaper.layer.model.log.LogReqModel;
import com.yushang.wallpaper.layer.service.log.LogService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "日志模块")
@RestController
@RequestMapping(value = "layer/log")
public class LogController {

    @Resource
    private LogService logService;

    @PermissionName(value = "查询日志列表")
    @RequiresPermissions(value = {"log:selectList"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "查询日志列表", tabName = "tb_log", operateType = 1, logEnum = UserLoginLogEnum.LOG_LIST)
    @RequestMapping(value = {"selectList"})
    public ResultFul selectList(LogReqModel reqModel) {
        return logService.selectLogList(reqModel);
    }
}
