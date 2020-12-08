package com.yushang.wallpaper.model.store;

import com.yushang.wallpaper.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * 优惠券-Request Query
 */
@Setter
@Getter
@NoArgsConstructor
public class CouponUpdateModel extends BaseModel {

    private static final long serialVersionUID = -3746524327261261182L;
    private String couponIds;     // 优惠券ID
    private Integer deleteFlag; // 删除状态：0-未删除，1-已删除
    private String[] couponIdValues;   // 优惠券ID数组

    public void validCouponIdIsNotNull() {
        Objects.requireNonNull(couponIds);
    }


}
