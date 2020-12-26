package com.yushang.wallpaper.layer.model.user.custom;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

/**
 * 客服模块 -- Custom Update Model
 */
@Setter
@Getter
@NoArgsConstructor
public class CustomUpdateModel extends BaseModel {

    private static final long serialVersionUID = -1784848308940239563L;
    /**
     * 客服ID
     */
    private String customIds;
    /**
     * 客服ID集合
     */
    private String[] customIdValues;
    /**
     * 更新时间
     */
    private Date lastUpdateTime;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 微信号
     */
    private String wxOpenId;
    /**
     * 客服名称
     */
    private String customName;

    public void validCustomIdNonNull() {
        Objects.requireNonNull(customIds);
    }

}
