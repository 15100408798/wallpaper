package com.yushang.wallpaper.layer.model.user;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserQueryModel extends BaseModel {

    private static final long serialVersionUID = -1518352677743747527L;
    private String username;    // 用户账号
    private Integer userRole;       // 用户身份
    private Integer isUse;      // 使用状态：0-可用，1-禁用
    private Integer deleteFlag = 0;     // 删除状态：0-未删除，1-已删除
    private String usernameLikeQuery;     // 用户账号模糊查询
    private String mobile;     // 用户手机号

}
