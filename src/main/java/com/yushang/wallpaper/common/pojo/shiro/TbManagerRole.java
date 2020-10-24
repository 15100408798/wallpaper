package com.yushang.wallpaper.common.pojo.shiro;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "tb_manager_role")
@Data
public class TbManagerRole implements Serializable {

    private static final long serialVersionUID = 3882368333377622653L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "smallint(32) comment '管理角色表'")
    private Short id;

    @Column(nullable = false, columnDefinition = "smallint(32) comment '管理者id'")
    private Short managerId;

    @Column(nullable = false, columnDefinition = "tinyint(3) comment '角色id'")
    private Byte roleId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "timestamp(0) default CURRENT_TIMESTAMP comment '创建时间'")
    private Date createTime;    // 创建时间

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '删除状态：0-未删除，1-已删除'")
    private Byte deleteFlag;    // 删除状态：0-未删除，1-已删除


}
