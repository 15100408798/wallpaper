package com.yushang.wallpaper.common.pojo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class TbManager implements Serializable {

    private static final long serialVersionUID = 8243962606460487691L;
    /**
     * 管理员id
     */
    private Short managerId;
    /**
     * 管理员账号
     */
    private String username;
    /**
     * 管理员密码
     */
    private String password;
    /**
     * 加密盐
     */
    private String salt;
    /**
     * 管理员昵称
     */
    private String userNick;
    /**
     * 管理员手机
     */
    private String telphone;
    /**
     * 角色：1-超级管理员，2-管理员
     */
    private Byte userRole;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM+8")
    private Date createTime;
    /**
     * 最后登陆时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date lastLoginTime;
    /**
     * 管理员状态：0-可用，1-禁用
     */
    private Byte isUse;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Byte deleteFlag;         //  删除状态：0-未删除，1-已删除

    public TbManager(String username) {
        this(username, null);
    }

    public TbManager(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
