package com.yushang.wallpaper.layer.controller.store;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.enums.StatusEnum;
import com.yushang.wallpaper.layer.model.enums.LogEnum;
import com.yushang.wallpaper.layer.model.store.product.ProductInsertModel;
import com.yushang.wallpaper.layer.model.store.product.ProductQueryModel;
import com.yushang.wallpaper.layer.model.store.product.ProductUpdateModel;
import com.yushang.wallpaper.layer.router.store.ProductService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 商品管理模块
 */
@RestController
@RequestMapping(value = "layer/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @PermissionName(value = "查询商品集合")
    @RequiresPermissions(value = {"product:selectList"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "查询商品集合", tabName = "tb_product", operateType = 1, logEnum = LogEnum.PRODUCT_LIST)
    @RequestMapping(value = {"selectList"})
    public ResultFul selectList(ProductQueryModel reqModel) {
        return productService.selectList(reqModel);
    }


    @PermissionName(value = "删除商品")
    @RequiresPermissions(value = {"product:delProduct"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "删除商品", tabName = "tb_product", operateType = 2, logEnum = LogEnum.DEL_PRODUCT)
    @RequestMapping("delProduct")
    public ResultFul delProduct(ProductUpdateModel productUpdateModel) {
        productUpdateModel.setDeleteFlag(1);
        return productService.updateProductInfo(productUpdateModel);
    }


    @PermissionName(value = "上架商品")
    @RequiresPermissions(value = {"product:openProduct"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "上架商品", tabName = "tb_product", operateType = 5, logEnum = LogEnum.UP_PRODUCT)
    @RequestMapping("openProduct")
    public ResultFul openProduct(ProductUpdateModel productUpdateModel) {
        productUpdateModel.setProductStatus(0);
        return productService.updateProductInfo(productUpdateModel);
    }


    @PermissionName(value = "下架商品")
    @RequiresPermissions(value = {"product:disabledProduct"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "下架商品", tabName = "tb_product", operateType = 6, logEnum = LogEnum.DOWN_PRODUCT)
    @RequestMapping("disabledProduct")
    public ResultFul disabledProduct(ProductUpdateModel productUpdateModel) {
        productUpdateModel.setProductStatus(1);
        return productService.updateProductInfo(productUpdateModel);
    }


    @PermissionName(value = "推荐商品")
    @RequiresPermissions(value = {"product:tuiProduct"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "推荐商品", tabName = "tb_product", operateType = 7, logEnum = LogEnum.TUI_PRODUCT)
    @RequestMapping("tuiProduct")
    public ResultFul tuiProduct(ProductUpdateModel productUpdateModel) {
        productUpdateModel.setHomeTopFlag(1);
        return productService.updateProductInfo(productUpdateModel);
    }


    @PermissionName(value = "不推荐商品")
    @RequiresPermissions(value = {"product:butuiProduct"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "不推荐商品", tabName = "tb_product", operateType = 8, logEnum = LogEnum.NO_TUI_PRODUCT)
    @RequestMapping("butuiProduct")
    public ResultFul butuiProduct(ProductUpdateModel productUpdateModel) {
        productUpdateModel.setHomeTopFlag(0);
        return productService.updateProductInfo(productUpdateModel);
    }

    @PermissionName(value = "新增商品")
    @RequiresPermissions(value = {"product:insertProductInfo"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "新增商品", tabName = "tb_product", operateType = 11, logEnum = LogEnum.INSERT_PRODUCT)
    @RequestMapping("insertProductInfo")
    public ResultFul insertProductInfo(ProductInsertModel productInsertModel) {
        productInsertModel.setHomeTopFlag(StatusEnum.DOWN_HOME_TOP_FLAG.getCode());
        productInsertModel.setCreateTime(new Date());
        productInsertModel.setDeleteFlag(StatusEnum.DELETE_NO.getCode());
        productInsertModel.setProductStatus(StatusEnum.UP.getCode());
        return productService.insertProductInfo(productInsertModel);
    }


    @PermissionName(value = "修改商品")
    @RequiresPermissions(value = {"product:updateProductInfo"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "修改商品", tabName = "tb_product", operateType = 13, logEnum = LogEnum.UPDATE_PRODUCT)
    @RequestMapping("updateProductInfo")
    public ResultFul updateProductInfo(ProductUpdateModel productUpdateModel) {
        productUpdateModel.setLastUpdateTime(new Date());
        return productService.updateProductInfo(productUpdateModel);
    }

}
