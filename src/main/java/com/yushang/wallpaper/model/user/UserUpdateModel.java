package com.yushang.wallpaper.model.user;

import com.yushang.wallpaper.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class UserUpdateModel extends BaseModel {

    private static final long serialVersionUID = -3612455138069176139L;
    private String userIds;     // 用户ID
    private String[] userIdValues;  // 用户ID数组
    private Integer deleteFlag;     // 删除状态：0-未删除，1-已删除
    private String username;    // 用户账号
    private Integer userRole;       // 用户身份：1-管理, 2-普通用户
    private Integer isUse;      // 使用状态：0-可用，1-禁用
    private String mobile;     // 用户手机号

    public void validUserIdsIsNotNull () {
        Objects.requireNonNull(userIds);
    }

}
