package com.yushang.wallpaper.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * 角色模块 -- Role Update Model
 */
@Setter
@Getter
@NoArgsConstructor
public class RoleUpdateModel implements Serializable {

    private static final long serialVersionUID = -3364910279000027025L;
    private Integer deleteFlag = 0;     // 删除状态：0-未删除，1-已删除
    private String roleIds;     // 角色Id
    private String[] roleIdValues;  // 角色Id数组

    public void validRoleIdNonNull () {
        Objects.requireNonNull(roleIds);
    }


}
