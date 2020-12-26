package com.yushang.wallpaper.layer.model.store;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 优惠券-Request Query
 */
@Setter
@Getter
@NoArgsConstructor
public class CouponQueryModel extends BaseModel {

    private static final long serialVersionUID = -3746524327261261182L;
    private Integer deleteFlag = 0;     // 删除状态：0-未删除；1-已删除
    private Long shopId;    // 商铺ID


}
