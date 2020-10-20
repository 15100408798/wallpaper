package com.yushang.wallpaper.layer.controller.store;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.enums.user.UserLoginLogEnum;
import com.yushang.wallpaper.layer.model.store.LabelQueryModel;
import com.yushang.wallpaper.layer.model.store.LabelUpdateModel;
import com.yushang.wallpaper.layer.service.store.ProductLabelService;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


/**
 * 商品类别管理
 */
@RestController
@RequestMapping(value = "layer/productLabel")
public class ProductLabelController {

    @Resource
    private ProductLabelService productLabelService;


    @PermissionName(value = "查询商品类别集合")
    @RequiresPermissions(value = {"product:selectLabelList"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "查询商品类别集合", tabName = "tb_product_label", operateType = 1, logEnum = UserLoginLogEnum.PRODUCT_LABEL_LIST)
    @RequestMapping(value = {"selectLabelList"})
    public ResultFul selectLabelList(LabelQueryModel reqModel) {
        return productLabelService.selectLabelList(reqModel);
    }


    @PermissionName(value = "删除商品类别")
    @RequiresPermissions(value = {"product:deleteProductLabel"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "删除商品类别", tabName = "tb_product_label", operateType = 2, logEnum = UserLoginLogEnum.DEL_PRODUCT_LABEL)
    @RequestMapping(value = {"deleteProductLabel"})
    public ResultFul deleteProductLabel(LabelUpdateModel labelUpdateModel) {
        labelUpdateModel.setDeleteFlag(NumberUtils.INTEGER_ONE);
        return productLabelService.updateProductLabel(labelUpdateModel);
    }

    @PermissionName(value = "还原商品类别")
    @RequiresPermissions(value = {"product:reductionProductLabel"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "还原商品类别", tabName = "tb_product_label", operateType = 12, logEnum = UserLoginLogEnum.RED_PRODUCT_LABEL)
    @RequestMapping(value = {"reductionProductLabel"})
    public ResultFul reductionProductLabel(LabelUpdateModel labelUpdateModel) {
        labelUpdateModel.setDeleteFlag(NumberUtils.INTEGER_ZERO);
        return productLabelService.updateProductLabel(labelUpdateModel);
    }

    @PermissionName(value = "新增商品类别")
    @RequiresPermissions(value = {"product:insertProductLabel"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "新增商品类别", tabName = "tb_product_label", operateType = 11, logEnum = UserLoginLogEnum.INSERT_PRODUCT_LABEL)
    @RequestMapping(value = {"insertProductLabel"})
    public ResultFul insertProductLabel(LabelUpdateModel labelUpdateModel) {
        labelUpdateModel.setDeleteFlag(NumberUtils.INTEGER_ZERO);
        labelUpdateModel.setCreateTime(new Date());
        return productLabelService.insertProductLabel(labelUpdateModel);
    }


}
