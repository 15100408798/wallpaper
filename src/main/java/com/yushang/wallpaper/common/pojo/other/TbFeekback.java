package com.yushang.wallpaper.common.pojo.other;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 反馈模块
 */
@Setter
@Getter
@NoArgsConstructor
public class TbFeekback implements Serializable {

    private static final long serialVersionUID = 1131729665210442987L;
    /**
     * 反馈主键id
     */
    private Integer feekbackId;
    /**
     * 反馈内容
     */
    private String content;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 反馈状态：1-待处理，2-已处理，3-已忽略
     */
    private Byte status;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date createTime;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Byte deleteFlag;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户手机号
     */
    private String userPhone;

}
