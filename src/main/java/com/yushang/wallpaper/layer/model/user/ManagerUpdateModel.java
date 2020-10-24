package com.yushang.wallpaper.layer.model.user;

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

    private static final long serialVersionUID = 2282425941995047232L;
    private String managerIds;   // 管理员ID
    private String[] managerIdValues; // 管理员ID集合
    private Integer isUseStatus;    // 管理员状态：0-开启，1-禁用
    private Date lastLoginTime = new Date();
    private Integer deleteFlag;     // 删除状态：0-未删除，1-已删除
    private String username;     // 用户账号
    private String password;     // 用户密码
    private Integer userRole;      // 用户级别：1-超级管理员，2-管理员

    public void validManageIdsIsNotNull () {
        Objects.requireNonNull(managerIds);
    }

}
