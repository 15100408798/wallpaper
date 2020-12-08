package com.yushang.wallpaper.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 角色模块 -- Role Insert Model
 */
@Setter
@Getter
@NoArgsConstructor
public class RoleInsertModel implements Serializable {

    private static final long serialVersionUID = -3364910279000027025L;
    private Integer deleteFlag = 0;     // 删除状态：0-未删除，1-已删除
    private String roleName;     // 角色名字
    private Date createTime = new Date();   // 创建时间

    public void validRoleName() {
        Objects.requireNonNull(roleName);
    }


}
