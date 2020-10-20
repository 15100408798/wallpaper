package com.yushang.wallpaper.common.pojo.shiro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tb_role_permission")
@Setter
@Getter
@NoArgsConstructor
public class TbRolePermission implements Serializable {

    private static final long serialVersionUID = 2034201656076500699L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "smallint(6) comment '角色权限表'")
    private Short id;     //id

    @Column(nullable = false,columnDefinition = "tinyint(1) comment '角色值'")
    private Byte roleId;

    @Column(nullable = false,columnDefinition = "smallint(3) comment '权限id'")
    private Short permissionId;
}
