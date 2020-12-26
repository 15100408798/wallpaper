package com.yushang.wallpaper.layer.model.store.productLabel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 更新商品类别-Request Model
 */
@Setter
@Getter
@NoArgsConstructor
public class LabelUpdateModel implements Serializable {

    private static final long serialVersionUID = 5014945705455795324L;
    /**
     * 商品类别ID
     */
    private String productLabelIds;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 商品类别ID数组
     */
    private String[] productLabelIdValues;
    /**
     * 商品类别名称
     */
    private String productLabelName;
    /**
     * 创建时间
     */
    private Date createTime;

    public void validProductLabelIdIsNotNull() {
        Objects.requireNonNull(productLabelIds);
    }

}
