package com.yushang.wallpaper.layer.controller;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.layer.service.MoneyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "layer/money")
public class MoneyController {

    @Autowired
    private MoneyService moneyService;

    @PermissionName(value="查询活动报名收费集合")
    @RequiresPermissions(value = { "money:selectActiveUserList" })
    @RequiresRoles(value = { "1" })
    @Log(title = "查询活动报名收费集合", tabName = "tb_active_user", operateType = 1)
    @RequestMapping("selectActiveUserList")
    public Map<String,Object> selectActiveUserList(
            @RequestParam("page") int page, @RequestParam("size") int size){
        return  moneyService.selectActiveUserList(page,size);
    }


    @PermissionName(value="查询活动流水集合")
    @RequiresPermissions(value = { "money:selectAllAUList" })
    @RequiresRoles(value = { "1" })
    @Log(title = "查询活动流水集合", tabName = "tb_active", operateType = 1)
    @RequestMapping("selectAllAUList")
    public Map<String,Object> selectAllAUList(
            @RequestParam("page") int page, @RequestParam("size") int size){
        return  moneyService.selectAllAUList(page,size);
    }

    @PermissionName(value="查询商品流水集合")
    @RequiresPermissions(value = { "money:selectStoreMoneyList" })
    @RequiresRoles(value = { "1" })
    @Log(title = "查询商品流水集合", tabName = "tb_order", operateType = 1)
    @RequestMapping("selectStoreMoneyList")
    public Map<String,Object> selectStoreMoneyList(
            @RequestParam("page") int page, @RequestParam("size") int size){
        return  moneyService.selectStoreMoneyList(page,size);
    }


    @PermissionName(value="按照周统计")
    @RequiresPermissions(value = { "money:selectOneWeekList" })
    @RequiresRoles(value = { "1" })
    @Log(title = "按照周统计", tabName = "tb_order", operateType = 1)
    @RequestMapping("selectOneWeekList")
    public Map<String,Object> selectOneWeekList(
            @RequestParam("page") int page, @RequestParam("size") int size){
        return  moneyService.selectOneWeekList(page,size);
    }
}
