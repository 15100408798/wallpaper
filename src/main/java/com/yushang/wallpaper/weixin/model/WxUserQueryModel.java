package com.yushang.wallpaper.weixin.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class WxUserQueryModel implements Serializable {

    private static final long serialVersionUID = 263085841068388060L;
    private String token;   // 用户token
    private String wxCode;    // 微信code
    private String phone;   // 用户手机号
    private String phoneCode;   // 手机号验证码

}
