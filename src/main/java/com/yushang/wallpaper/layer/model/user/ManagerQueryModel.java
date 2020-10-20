package com.yushang.wallpaper.layer.model.user;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ManagerQueryModel extends BaseModel {

    private static final long serialVersionUID = -7071941824467150715L;
    private Short managerId;   // 管理员ID
    private String username;    // 管理员账号
    private String password;    // 管理员密码
    private Integer userRole;         //  1 超级管理员  2 管理员
    private int sort = 1;   // 排序字段: 1-创建时间，
    private Integer deleteFlag;     // 删除状态：0-未删除，1-已删除
    private Integer isUse;         //  管理员状态：0-可用，1-禁用
    private String telphone;       // 管理员手机号

    public ManagerQueryModel(int page, int size) {
        super(page, size);
    }

    public ManagerQueryModel(String username) {
        this.username = username;
    }

}
