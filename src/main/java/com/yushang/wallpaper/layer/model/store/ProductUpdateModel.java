package com.yushang.wallpaper.layer.model.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 商品管理 -- Product Update Model
 */
@Setter
@Getter
@NoArgsConstructor
public class ProductUpdateModel implements Serializable {

    private static final long serialVersionUID = -4853938719459188143L;
    private Integer deleteFlag = 0; // 删除状态：0-未删除，1-已删除
    private String productIds; // 商品ID
    private String[] productIdValues; // 商品ID数组
    private Date lastUpdateTime = new Date();   // 最后更新时间
    private Integer productStatus;    // 上下架状态：0-上架，1-下架
    private Integer homeTopFlag;   // 是否推荐：0-否，1-首页推荐。  推荐优先展示

    public void validProductIdsIsNotNull () {
        Objects.requireNonNull(productIds);
    }


}
