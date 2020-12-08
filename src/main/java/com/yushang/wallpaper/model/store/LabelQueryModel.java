package com.yushang.wallpaper.model.store;

import com.yushang.wallpaper.model.base.BaseModel;
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

    private static final long serialVersionUID = 3042846421675959043L;

    private Integer deleteFlag = 0; // 删除状态：0-未删除，1-已删除
    private String productLabelName;        //  商品类别名称

    public LabelQueryModel(int page, int size) {
        super(page, size);
    }

}
