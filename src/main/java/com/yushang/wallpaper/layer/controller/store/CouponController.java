package com.yushang.wallpaper.layer.controller.store;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.enums.user.UserLoginLogEnum;
import com.yushang.wallpaper.layer.model.store.CouponQueryModel;
import com.yushang.wallpaper.layer.model.store.CouponUpdateModel;
import com.yushang.wallpaper.layer.service.store.CouponService;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 优惠券模块
 */
@RestController
@RequestMapping(value = "layer/coupon")
public class CouponController {

    @Resource
    private CouponService couponService;

    @PermissionName(value = "查询优惠价列表")
    @RequiresPermissions(value = {"coupon:selectCouponList"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "查询优惠价列表", tabName = "tb_coupon", operateType = 1, logEnum = UserLoginLogEnum.COUPON_LIST)
    @RequestMapping(value = {"selectCouponList"})
    public ResultFul selectCouponList(CouponQueryModel queryModel) {
        return couponService.selectCouponList(queryModel);
    }

    @PermissionName(value = "删除优惠卷")
    @RequiresPermissions(value = {"coupon:deleteCoupon"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "删除优惠卷", tabName = "tb_coupon", operateType = 2, logEnum = UserLoginLogEnum.DEL_COUPON)
    @RequestMapping(value = {"deleteCoupon"})
    public ResultFul deleteCoupon(String ids) {
        CouponUpdateModel couponUpdateModel = new CouponUpdateModel();
        couponUpdateModel.setDeleteFlag(NumberUtils.INTEGER_ONE);
        couponUpdateModel.setCouponIds(ids);
        return couponService.updateCoupon(couponUpdateModel);
    }


}
