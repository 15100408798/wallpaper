package com.yushang.wallpaper.layer.service.store;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.store.CouponMapper;
import com.yushang.wallpaper.common.pojo.store.TbCoupon;
import com.yushang.wallpaper.layer.model.store.CouponQueryModel;
import com.yushang.wallpaper.layer.model.store.CouponUpdateModel;
import com.yushang.wallpaper.layer.router.store.CouponService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class CouponServiceImpl implements CouponService {

    @Resource
    private CouponMapper couponMapper;

    /**
     * 查询优惠价列表
     *
     * @param queryModel 查询参数
     * @return 优惠价信息列表
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectCouponList(CouponQueryModel queryModel) {
        /* 校验参数 */
        Objects.requireNonNull(queryModel);
        queryModel.validPageSizeIsNull();
        // 分页
        PageHelper.startPage(queryModel.getPage(), queryModel.getSize());
        Page<TbCoupon> tbCouponPage = couponMapper.selectCouponList(queryModel);
        return ResultFul.getSuccessList(tbCouponPage.getResult(), tbCouponPage.getTotal());
    }

    /**
     * 更新优惠券信息
     *
     * @param couponUpdateModel 优惠券信息
     * @return 受影响条数
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ResultFul updateCoupon(CouponUpdateModel couponUpdateModel) {
        /* 校验参数 */
        Objects.requireNonNull(couponUpdateModel);
        couponUpdateModel.validCouponIdIsNotNull();
        // 商品类别ID
        String couponIds = couponUpdateModel.getCouponIds();
        String[] couponIdValues = couponIds.split(",");
        if (ArrayUtils.isEmpty(couponIdValues)){
            throw new NullPointerException("优惠券ID为null");
        }
        couponUpdateModel.setCouponIdValues(couponIdValues);
        // 更新优惠券信息
        int updateCount = couponMapper.updateCoupon(couponUpdateModel);
        return ResultFul.getSuccessTotal(updateCount);
    }

}
