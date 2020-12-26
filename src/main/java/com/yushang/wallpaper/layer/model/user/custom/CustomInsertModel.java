package com.yushang.wallpaper.layer.model.user.custom;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * 客服模块 -- Custom Insert Model
 */
@Setter
@Getter
@NoArgsConstructor
public class CustomInsertModel extends BaseModel {

    private static final long serialVersionUID = 6138740261589748853L;
    /**
     * 微信号
     */
    private String wxOpenId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 客服二维码
     */
    private String image;
    /**
     * 客服名称
     */
    private String customName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;

    public void validParams() {
        if (StringUtils.isBlank(phone)) {
            throw new RuntimeException("手机号不能为空");
        } else if (StringUtils.isBlank(wxOpenId)) {
            throw new RuntimeException("微信号不能为空");
        } else if (StringUtils.isBlank(customName)) {
            throw new RuntimeException("客服名称不能为空");
        }
    }

}
