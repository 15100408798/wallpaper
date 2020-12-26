package com.yushang.wallpaper.layer.model.user.manager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * 登录请求参数
 */
@Setter
@Getter
@NoArgsConstructor
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 5131711484800278756L;
    /**
     * 登录者账号
     */
    private String username;
    /**
     * 登录者密码
     */
    private String password;
    /**
     * 记住我
     */
    private boolean rememberMe;

    public UserLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * 验证账号密码不能为空
     */
    public void validUsernameAndPasswordIsNull() {
        if (StringUtils.isBlank(username)) {
            throw new NullPointerException("登录账号不能为null");
        } else if (StringUtils.isBlank(password)) {
            throw new NullPointerException("登录密码不能为null");
        }
    }

}
