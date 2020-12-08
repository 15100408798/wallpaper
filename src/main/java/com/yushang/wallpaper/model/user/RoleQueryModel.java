package com.yushang.wallpaper.model.user;

import com.yushang.wallpaper.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 角色模块 -- Role Query Model
 */
@Setter
@Getter
@NoArgsConstructor
public class RoleQueryModel extends BaseModel {

    private static final long serialVersionUID = -9043274293144292742L;
    private Integer deleteFlag = 0;     // 删除状态：0-未删除，1-已删除
    private Integer roleId;     // 角色Id
    private String roleName;     // 角色名字

    public RoleQueryModel(int page, int size) {
        super(page, size);
    }

}
