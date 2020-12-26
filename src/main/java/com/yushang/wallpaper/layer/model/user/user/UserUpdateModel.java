package com.yushang.wallpaper.layer.model.user.user;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class UserUpdateModel extends BaseModel {

    private static final long serialVersionUID = -3612455138069176139L;
    /**
     * 用户ID
     */
    private String userIds;
    /**
     * 用户ID数组
     */
    private String[] userIdValues;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户身份：1-管理, 2-普通用户
     */
    private Integer userRole;
    /**
     * 使用状态：0-可用，1-禁用
     */
    private Integer isUse;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 更新时间
     */
    private Date updateTime;

    public void validUserIdsIsNotNull () {
        Objects.requireNonNull(userIds);
    }

}
