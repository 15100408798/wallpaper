package com.yushang.wallpaper.common.pojo.shiro;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="tb_manager_role")
@Data
public class TbManagerRole implements Serializable {

    private static final long serialVersionUID = 3882368333377622653L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "tinyint(3) comment '管理角色表'")
    private Byte id;

    @Column(nullable = false,columnDefinition = "tinyint(3) comment '管理者id'")
    private Byte managerId;

    @Column(nullable = false,columnDefinition = "tinyint(3) comment '角色id'")
    private Byte roleId;

}
