package com.yushang.wallpaper.layer.controller.store;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.model.enums.LogEnum;
import com.yushang.wallpaper.model.store.ProductSkuQueryModel;
import com.yushang.wallpaper.model.store.ProductSkuUpdateModel;
import com.yushang.wallpaper.layer.router.store.ProductSkuService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品Sku模块
 */
@RestController
@RequestMapping(value = "layer/product")
public class ProductSkuController {

    @Resource
    private ProductSkuService productService;

    @PermissionName(value = "查询商品sku集合")
    @RequiresPermissions(value = {"product:selectSkuList"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "查询商品sku集合", tabName = "tb_product_sku", operateType = 1, logEnum = LogEnum.SKU_LIST)
    @RequestMapping(value = {"selectSkuList"})
    public ResultFul selectSkuList(ProductSkuQueryModel productSkuQueryModel) {
        return productService.selectSkuList(productSkuQueryModel);
    }

    @PermissionName(value = "删除sku商品")
    @RequiresPermissions(value = {"product:delSkuProduct"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "删除sku商品", tabName = "tb_product_sku", operateType = 2, logEnum = LogEnum.DEL_SKU)
    @RequestMapping(value = {"delSkuProduct"})
    public ResultFul delSkuProduct(ProductSkuUpdateModel productSkuUpdateModel) {
        productSkuUpdateModel.setDeleteFlag(1);
        return productService.updateProductSkuInfo(productSkuUpdateModel);
    }

    @PermissionName(value = "还原sku商品")
    @RequiresPermissions(value = {"product:redSkuProduct"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "还原sku商品", tabName = "tb_product_sku", operateType = 12, logEnum = LogEnum.RED_SKU)
    @RequestMapping(value = {"redSkuProduct"})
    public ResultFul redSkuProduct(ProductSkuUpdateModel productSkuUpdateModel) {
        productSkuUpdateModel.setDeleteFlag(0);
        return productService.updateProductSkuInfo(productSkuUpdateModel);
    }

    @PermissionName(value = "上架sku商品")
    @RequiresPermissions(value = {"product:openSkuProduct"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "上架sku商品", tabName = "tb_product_sku", operateType = 5, logEnum = LogEnum.UP_SKU)
    @RequestMapping(value = {"openSkuProduct"})
    public ResultFul openSkuProduct(ProductSkuUpdateModel productSkuUpdateModel) {
        productSkuUpdateModel.setProductSkuStatus(0);
        return productService.updateProductSkuInfo(productSkuUpdateModel);
    }


    @PermissionName(value = "下架sku商品")
    @RequiresPermissions(value = {"product:disabledSkuProduct"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "下架sku商品", tabName = "tb_product_sku", operateType = 6, logEnum = LogEnum.DOWN_SKU)
    @RequestMapping(value = {"disabledSkuProduct"})
    public ResultFul disabledSkuProduct(ProductSkuUpdateModel productSkuUpdateModel) {
        productSkuUpdateModel.setProductSkuStatus(1);
        return productService.updateProductSkuInfo(productSkuUpdateModel);
    }


}
