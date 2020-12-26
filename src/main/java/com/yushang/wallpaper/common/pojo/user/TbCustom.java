package com.yushang.wallpaper.common.pojo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 客服管理
 */
@Setter
@Getter
@NoArgsConstructor
public class TbCustom implements Serializable {

    private static final long serialVersionUID = 272816712350498151L;
    /**
     * 客服ID
     */
    private Integer customId;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date createTime;
    /**
     * 最后更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date lastUpdateTime;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Byte deleteFlag;

}
