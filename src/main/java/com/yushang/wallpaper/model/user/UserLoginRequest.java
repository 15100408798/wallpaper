package com.yushang.wallpaper.model.user;

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

    private static final long serialVersionUID = -4143195308828159934L;
    private String username;     // 登录者账号
    private String password;    // 登录者密码
    private boolean rememberMe; // 记住我

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
