package com.yushang.wallpaper.layer.router.store;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.store.CouponQueryModel;
import com.yushang.wallpaper.layer.model.store.CouponUpdateModel;
import org.springframework.lang.NonNull;

/**
 * 优惠券模块
 */
public interface CouponService {

    /**
     * 查询优惠价列表
     *
     * @param queryModel 查询参数
     * @return 优惠价信息列表
     */
    @NonNull
    ResultFul selectCouponList(@NonNull CouponQueryModel queryModel);

    /**
     * 更新优惠券信息
     *
     * @param couponUpdateModel 优惠券信息
     * @return 受影响条数
     */
    @NonNull
    ResultFul updateCoupon(@NonNull CouponUpdateModel couponUpdateModel);


}
