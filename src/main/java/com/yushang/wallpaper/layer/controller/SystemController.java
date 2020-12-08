package com.yushang.wallpaper.layer.controller;


import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.service.SystemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "layer/system")
public class SystemController {

    @Autowired
    private SystemService systemService;







    @PermissionName(value="查询会员折扣列表")
    @RequiresPermissions(value = { "system:selectRBList" })
    @RequiresRoles(value = { "2" })
    @Log(title = "查询会员折扣列表",tabName = "tb_rebate",operateType = 1)
    @RequestMapping("selectRBList")
    public Map<String,Object> selectRBList(
            @RequestParam("page") int page, @RequestParam("size") int size){
        return systemService.selectRBList(page,size);
    }

    @PermissionName(value="删除会员折扣")
    @RequiresPermissions(value = { "system:deleteRB" })
    @RequiresRoles(value = { "2" })
    @Log(title = "删除会员折扣",tabName = "tb_rebate",operateType = 2)
    @RequestMapping("deleteRB")
    public Map<String,Object> deleteRB(String ids){
        return systemService.deleteRB(ids);
    }


}
