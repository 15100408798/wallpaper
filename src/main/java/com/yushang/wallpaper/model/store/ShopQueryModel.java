package com.yushang.wallpaper.model.store;

import com.yushang.wallpaper.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 商户管理 -- Shop Query Model
 */
@Setter
@Getter
@NoArgsConstructor
public class ShopQueryModel extends BaseModel {

    private static final long serialVersionUID = -8570221587049878186L;
    private Integer deleteFlag = 0; // 删除状态：0-未删除，1-已删除
    private Integer shopStatus; // 上下架状态：1-上架，2-下架
    private String username; // 用户账号
    private String shopName; // 商铺名称



}
