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
public class ManagerUpdateModel extends BaseModel {

    private static final long serialVersionUID = -4363695820454682118L;
    /**
     * 管理员ID
     */
    private String managerIds;
    /**
     * 管理员ID集合
     */
    private String[] managerIdValues;
    /**
     * 管理员状态：0-开启，1-禁用
     */
    private Integer isUseStatus;
    private Date lastLoginTime = new Date();
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 管理员账号
     */
    private String username;
    /**
     * 管理员密码
     */
    private String password;
    /**
     * 管理员手机号
     */
    private String telphone;
    /**
     * 管理员昵称
     */
    private String userNick;
    /**
     * 用户级别：1-超级管理员，2-管理员
     */
    private Integer userRole;

    public void validManageIdsIsNotNull () {
        Objects.requireNonNull(managerIds);
    }

    public void validParams(){
        Objects.requireNonNull(username, "账号不能为空");
        Objects.requireNonNull(password, "密码不能为空");
        Objects.requireNonNull(userRole, "角色不能为空");
        Objects.requireNonNull(isUseStatus, "管理员状态不能为空");
        Objects.requireNonNull(telphone, "手机号不能为空");
        Objects.requireNonNull(userNick, "昵称不能为空");
    }

}
