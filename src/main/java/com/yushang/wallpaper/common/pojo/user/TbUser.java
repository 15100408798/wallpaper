package com.yushang.wallpaper.common.pojo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "tb_user")
public class TbUser implements Serializable {

    private static final long serialVersionUID = -4808511685201320232L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int(6) comment '用户表'")
    private Integer userId;     // 用户id

    @Column(nullable = false, columnDefinition = "varchar(18) comment '用户账号'")
    private String username;     // 用户账号

    @Column(nullable = false, columnDefinition = "varchar(200) comment '用户密码'")
    private String password;    // 用户密码

    @Column(nullable = false, columnDefinition = "varchar(11) comment '用户手机号'")
    private String mobile;      // 用户手机号

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "datetime(6) comment '创建时间'")
    private Date createTime;    // 创建时间

    @Column(nullable = true, columnDefinition = "datetime(6) comment '最后登陆时间'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date lastLoginTime;    // 最后登陆时间

    @Column(nullable = false, columnDefinition = "tinyint(1) default 2 comment '用户身份：1-管理, 2-普通用户'")
    private Byte userRole;      // 用户身份：1-管理, 2-普通用户

    @Column(nullable = false, columnDefinition = "varchar(18) default '淘沙者' comment '用户昵称'")
    private String userNick;    // 用户昵称

    @Column(nullable = false, columnDefinition = "varchar(18) comment '盐'")
    private String salt;    // 盐

    @Column(nullable = false, columnDefinition = "varchar(60) comment '微信openid'")
    private String openId;    // 微信openid

    @Column(nullable = true, columnDefinition = "varchar(300) comment '用户头像'")
    private String photo;    // 用户头像

    @Column(nullable = false, columnDefinition = "int(30) comment '用户省份'")
    private Integer provinceId;    // 用户省份

    @Column(nullable = false, columnDefinition = "int(30) comment '用户城市'")
    private Integer cityId;    // 用户城市

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '用户状态：0-开启，1-禁用'")
    private Byte isUse;    // 用户状态：0-开启，1-禁用

    @Column(nullable = false, columnDefinition = "int(12) default 0 comment '积分'")
    private Integer score;    // 用户积分

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '用户级别：0-普通用户, 1-VIP1, 2-VIP2, 3-VIP3, 4-VIP4'")
    private Byte userLevel;    // 用户级别：0-普通用户, 1-VIP1, 2-VIP2, 3-VIP3, 4-VIP4

    @Column(nullable = false, columnDefinition = "decimal(12,2) default 0.00 comment '用户余额'")
    private BigDecimal money;    // 用户余额

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '删除状态：0-未删除，1-已删除'")
    private Byte deleteFlag;    // 删除状态：0-未删除，1-已删除


}
