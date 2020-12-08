package com.yushang.wallpaper.model.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 商户管理 -- Shop Update Model
 */
@Setter
@Getter
@NoArgsConstructor
public class ShopUpdateModel implements Serializable {
    private static final long serialVersionUID = 5971734055481172444L;

    private Integer deleteFlag = 0; // 删除状态：0-未删除，1-已删除
    private String shopIds; // 商户ID
    private String[] shopIdValues; // 商户ID数组
    private Date LastUpdateTime = new Date();   // 最后更新时间
    private Integer status;                // 上下架状态：1-上架，2-下架


    public void validShopIdIsNotNull() {
        Objects.requireNonNull(shopIds);
    }

}
