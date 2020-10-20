package com.yushang.wallpaper.common.pojo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_manager")
public class TbManager implements Serializable {

    private static final long serialVersionUID = 1458364362729444872L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "smallint(6) comment '管理员id'")
    private Short managerId;

    @Column(nullable = false, columnDefinition = "varchar(12) comment '管理员账号'")
    private String username;

    @Column(nullable = false, columnDefinition = "varchar(100) comment '管理员密码'")
    private String password;

    @Column(nullable = false, columnDefinition = "varchar(12) comment '加密盐'")
    private String salt;

    @Column(nullable = false, columnDefinition = "varchar(16) comment '管理员昵称'")
    private String userNick;

    @Column(nullable = false, columnDefinition = "varchar(11) comment '手机'")
    private String telphone;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 2 comment '1-超级管理员，2-管理员'")
    private Byte userRole;         //  1 超级管理员  2 管理员

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "timestamp(6) comment '创建时间'")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = true, columnDefinition = "timestamp(6) comment '最后登陆时间'")
    private Date lastLoginTime;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 1 comment '管理员状态：0-可用，1-禁用'")
    private Byte isUse;         //  管理员状态：0-可用，1-禁用

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '删除状态：0-未删除，1-已删除'")
    private Byte deleteFlag;         //  删除状态：0-未删除，1-已删除

    public TbManager(String username) {
        this(username, null);
    }

    public TbManager(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
