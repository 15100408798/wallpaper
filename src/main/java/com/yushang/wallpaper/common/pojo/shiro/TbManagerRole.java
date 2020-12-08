package com.yushang.wallpaper.common.pojo.shiro;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class TbManagerRole implements Serializable {

    private static final long serialVersionUID = 7588135761753007848L;
    private Short id;
    /**
     * 管理者id
     */
    private Short managerId;
    /**
     * 角色id
     */
    private Byte roleId;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date createTime;    // 创建时间
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Byte deleteFlag;

}
