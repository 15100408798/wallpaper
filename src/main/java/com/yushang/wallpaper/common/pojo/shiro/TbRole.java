package com.yushang.wallpaper.common.pojo.shiro;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "tb_role")
public class TbRole implements Serializable {

    private static final long serialVersionUID = 4107578575127979409L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "tinyint(2) comment '角色表'")
    private Byte roleId; //角色值    1 超级管理员  2 管理员

    @Column(nullable = false,columnDefinition = "varchar(10) comment '角色名称'")
    private String name;        //  角色名称

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "timestamp(0) default CURRENT_TIMESTAMP comment '创建时间'")
    private Date createTime;    // 创建时间

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '删除状态：0-未删除，1-已删除'")
    private Byte deleteFlag;    // 删除状态：0-未删除，1-已删除


}
