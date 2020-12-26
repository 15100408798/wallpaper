package com.yushang.wallpaper.common.pojo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户管理
 */
@Setter
@Getter
@NoArgsConstructor
public class TbUser implements Serializable {

    private static final long serialVersionUID = 7611166232119851066L;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date createTime;
    /**
     * 最后登陆时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date lastLoginTime;
    /**
     * 用户身份：1-管理, 2-普通用户
     */
    private Byte userRole;
    /**
     * 用户昵称
     */
    private String userNick;
    /**
     * 盐
     */
    private String salt;
    /**
     * 微信openid
     */
    private String openId;
    /**
     * 用户头像
     */
    private String photo;
    /**
     * 用户省份
     */
    private Integer provinceId;
    /**
     * 用户城市
     */
    private Integer cityId;
    /**
     * 用户状态：0-开启，1-禁用
     */
    private Byte isUse;
    /**
     * 用户积分
     */
    private Integer score;
    /**
     * 用户级别：
     * 0-普通用户, 1-VIP1, 2-VIP2, 3-VIP3, 4-VIP4
     */
    private Byte userLevel;
    /**
     * 用户余额
     */
    private BigDecimal money;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Byte deleteFlag;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date updateTime;


}
