package com.yushang.wallpaper.layer.model.user.manager;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class ManagerInsertModel extends BaseModel {

    private static final long serialVersionUID = 6307067649670513378L;
    private Short managerId;    // 管理员ID
    private String username;    // 管理员账号
    private String password;    // 管理员密码
    private String salt;        // 盐
    private String userNick;    // 用户昵称
    private String telphone;    // 管理员手机号
    private Byte userRole;      // 用户级别：1-超级管理员，2-管理员
    private Date createTime;    // 创建时间
    private Date lastLoginTime; // 最后一次登录时间
    private Byte isUse;         //  管理员状态：0-可用，1-禁用
    private Byte deleteFlag;    //  删除状态：0-未删除，1-已删除

    public void validInsertInfoIsNotNull() {
        Objects.requireNonNull(username, "账号不能为null");
        Objects.requireNonNull(password, "密码不能为null");
        Objects.requireNonNull(userNick, "昵称不能为null");
        Objects.requireNonNull(telphone, "手机号不能为null");
        Objects.requireNonNull(userRole, "用户级别不能为null");
        Objects.requireNonNull(isUse, "状态不能为null");
    }


}
