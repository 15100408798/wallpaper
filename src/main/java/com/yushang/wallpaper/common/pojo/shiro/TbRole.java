package com.yushang.wallpaper.common.pojo.shiro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
    private String name;        //角色名称


}
