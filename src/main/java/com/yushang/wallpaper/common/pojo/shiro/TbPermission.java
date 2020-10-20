package com.yushang.wallpaper.common.pojo.shiro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "tb_permission")
public class TbPermission implements Serializable {

    private static final long serialVersionUID = 6501184265765102268L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "smallint(3) comment '权限表'")
    private Short permissionId;     //id

    @Column(nullable = false, columnDefinition = "varchar(60) comment '地址'")
    private String value;   //地址

    @Column(nullable = false, columnDefinition = "varchar(30) comment '操作名称'")
    private String name;    //操作名称


}
