package com.yushang.wallpaper.layer.model.user.user;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserQueryModel extends BaseModel {

    private static final long serialVersionUID = -6011348485284682956L;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 不包含用户ID
     */
    private Integer notUserId;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户身份
     */
    private Integer userRole;
    /**
     * 使用状态：0-可用，1-禁用
     */
    private Integer isUse;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 用户账号模糊查询
     */
    private String usernameLikeQuery;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 微信openid
     */
    private String openid;
    /**
     * 用户级别：
     * 0-普通用户, 1-VIP1, 2-VIP2, 3-VIP3, 4-VIP4
     */
    private Integer userLevel;

    public UserQueryModel(String username) {
        this.username = username;
    }


}
