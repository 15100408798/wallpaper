package com.yushang.wallpaper.layer.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 管理员用户关系表 -- Manager-Role Update Model
 */
@Setter
@Getter
@NoArgsConstructor
public class ManagerRoleUpdateModel implements Serializable {

    private static final long serialVersionUID = -7890429795585097327L;
    private Integer deleteFlag; // 删除状态：0-未删除，1-已删除
    private String[] managerIdValues; // 管理员ID集合
    private Integer roleId;      // 用户级别：1-超级管理员，2-管理员

    public ManagerRoleUpdateModel(Integer deleteFlag, String[] managerIdValues) {
        this.deleteFlag = deleteFlag;
        this.managerIdValues = managerIdValues;
    }

}
