package com.yushang.wallpaper.layer.model.user.custom;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 客服模块 -- Custom Query Model
 */
@Setter
@Getter
@NoArgsConstructor
public class CustomQueryModel extends BaseModel {

    private static final long serialVersionUID = -297891123936774889L;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 手机号
     */
    private String customPhone;
    /**
     * 微信号
     */
    private String wxOpenId;
    /**
     * 客服ID
     */
    private Integer customId;
    /**
     * 不包含客服ID
     */
    private Integer notCustomId;

    public CustomQueryModel(int page, int size) {
        super(page, size);
    }


}
