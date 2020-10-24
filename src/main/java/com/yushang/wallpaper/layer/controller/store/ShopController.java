package com.yushang.wallpaper.layer.controller.store;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.enums.LogEnum;
import com.yushang.wallpaper.layer.model.store.ShopQueryModel;
import com.yushang.wallpaper.layer.model.store.ShopUpdateModel;
import com.yushang.wallpaper.layer.service.store.ShopService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商户管理
 */
@RestController
@RequestMapping(value = "layer/shop")
public class ShopController {

    @Resource
    private ShopService shopService;

    @PermissionName(value = "查询商铺集合")
    @RequiresPermissions(value = {"shop:selectStoreList"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "查询商铺集合", tabName = "tb_shop", operateType = 1, logEnum = LogEnum.SHOP_LIST)
    @RequestMapping(value = {"selectStoreList"})
    public ResultFul selectStoreList(ShopQueryModel shopQueryModel) {
        return shopService.selectStoreList(shopQueryModel);
    }


    @PermissionName(value = "删除商铺")
    @RequiresPermissions(value = {"shop:delStore"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "删除商铺", tabName = "tb_shop", operateType = 2, logEnum = LogEnum.DEL_SHOP)
    @RequestMapping(value = {"delStore"})
    public ResultFul delStore(ShopUpdateModel shopUpdateModel) {
        shopUpdateModel.setDeleteFlag(1);
        return shopService.updateShopInfo(shopUpdateModel);
    }


    @PermissionName(value = "上架商铺")
    @RequiresPermissions(value = {"shop:openStore"})
    @RequiresRoles(value = {"1"})
    @Log(title = "上架商铺", tabName = "tb_shop", operateType = 5, logEnum = LogEnum.OPEN_SHOP)
    @RequestMapping("openStore")
    public ResultFul openStore(ShopUpdateModel shopUpdateModel) {
        shopUpdateModel.setStatus(1);
        return shopService.updateShopInfo(shopUpdateModel);
    }


    @PermissionName(value = "下架商铺")
    @RequiresPermissions(value = {"shop:disabledStore"})
    @RequiresRoles(value = {"1"})
    @Log(title = "下架商铺", tabName = "tb_shop", operateType = 6, logEnum = LogEnum.DIS_SHOP)
    @RequestMapping("disabledStore")
    public ResultFul disabledStore(ShopUpdateModel shopUpdateModel) {
        shopUpdateModel.setStatus(2);
        return shopService.updateShopInfo(shopUpdateModel);
    }


}
