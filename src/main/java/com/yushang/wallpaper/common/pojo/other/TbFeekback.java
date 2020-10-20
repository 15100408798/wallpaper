package com.yushang.wallpaper.common.pojo.other;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 反馈模块
 */
@Setter
@Getter
@NoArgsConstructor
@Entity(name = "tb_feekback")
public class TbFeekback implements Serializable {

    private static final long serialVersionUID = -4848692473845166489L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int(32) comment '反馈主键id'")
    private Integer feekbackId;     //  反馈主键id

    @Column(nullable = false, columnDefinition = "varchar(500) comment '反馈内容'")
    private String content;         //  反馈内容

    @Column(nullable = false, columnDefinition = "int(32) comment '用户id'")
    private Integer userId;         //  用户id

    @Column(nullable = false, columnDefinition = "tinyint(1) default 1 comment '反馈状态：1-待处理，2-已处理，3-已忽略'")
    private Byte status;            //  反馈状态：1-待处理，2-已处理，3-已忽略

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "timestamp(0) default CURRENT_TIMESTAMP comment '创建时间'")
    private Date createTime;        //   创建时间

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '删除状态：0-未删除，1-已删除'")
    private Byte deleteFlag;        //  删除状态：0-未删除，1-已删除

    private transient String userNick;  // 用户昵称
    private transient String username;  // 用户账号

}
