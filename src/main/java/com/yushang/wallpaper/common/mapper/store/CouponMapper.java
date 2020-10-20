package com.yushang.wallpaper.common.mapper.store;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.store.TbCoupon;
import com.yushang.wallpaper.layer.model.store.CouponQueryModel;
import com.yushang.wallpaper.layer.model.store.CouponUpdateModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {

    /**
     * 查询优惠价列表
     *
     * @param queryModel 查询参数
     * @return 优惠价信息列表
     */
    Page<TbCoupon> selectCouponList(CouponQueryModel queryModel);

    /**
     * 更新优惠券信息
     *
     * @param couponUpdateModel 优惠券信息
     * @return 受影响条数
     */
    int updateCoupon(CouponUpdateModel couponUpdateModel);


}
