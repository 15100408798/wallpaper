package com.yushang.wallpaper.layer.model.store;

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

    private static final long serialVersionUID = -3231087430305578953L;
    private String productLabelIds;     // 商品类别ID
    private Integer deleteFlag; // 删除状态：0-未删除，1-已删除
    private String[] productLabelIdValues;   // 商品类别ID数组
    private String productLabelName;        //  商品类别名称
    private Date createTime;    // 创建时间

    public void validProductLabelIdIsNotNull() {
        Objects.requireNonNull(productLabelIds);
    }

}
