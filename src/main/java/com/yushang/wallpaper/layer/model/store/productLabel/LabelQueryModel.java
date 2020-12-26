package com.yushang.wallpaper.layer.model.store.productLabel;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 查询商品类别-Request Model
 */
@Setter
@Getter
@NoArgsConstructor
public class LabelQueryModel extends BaseModel {

    private static final long serialVersionUID = 6953953800023050192L;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 商品类别名称
     */
    private String productLabelName;

    /**
     * 商品类别ID
     */
    private Integer productLabelId;

    /**
     * 不包含商品类别ID
     */
    private Integer notProductLabelId;

    public LabelQueryModel(int page, int size) {
        super(page, size);
    }

}
