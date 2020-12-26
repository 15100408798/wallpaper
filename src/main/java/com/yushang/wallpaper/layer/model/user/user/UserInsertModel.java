package com.yushang.wallpaper.layer.model.user.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class UserInsertModel extends BaseModel {

    private static final long serialVersionUID = 5401546943211582415L;
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
    private Date createTime;
    /**
     * 最后登陆时间
     */
    private Date lastLoginTime;
    /**
     * 用户身份：1-管理, 2-普通用户
     */
    private Integer userRole;
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
    private Integer isUse;
    /**
     * 用户积分
     */
    private Integer score;
    /**
     * 用户级别：
     * 0-普通用户, 1-VIP1, 2-VIP2, 3-VIP3, 4-VIP4
     */
    private Integer userLevel;
    /**
     * 用户余额
     */
    private BigDecimal money;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 更新时间
     */
    private Integer updateTime;

    public void validInsertInfoIsNotNull() {
        Objects.requireNonNull(username, "账号不能为null");
        Objects.requireNonNull(password, "密码不能为null");
        Objects.requireNonNull(userNick, "昵称不能为null");
        Objects.requireNonNull(mobile, "手机号不能为null");
        Objects.requireNonNull(userRole, "用户级别不能为null");
    }


}
