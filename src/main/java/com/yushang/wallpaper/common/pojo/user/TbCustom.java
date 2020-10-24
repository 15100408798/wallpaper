package com.yushang.wallpaper.common.pojo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 客服管理
 */
@Entity(name = "tb_custom")
@Setter
@Getter
@NoArgsConstructor
public class TbCustom implements Serializable {

    private static final long serialVersionUID = 3234488271767727553L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int(32) comment '客服人员id'")
    private Integer customId;

    @Column(nullable = false, columnDefinition = "varchar(300) comment '微信号'")
    private String wxOpenId;        //  微信号

    @Column(nullable = false, columnDefinition = "varchar(50) comment '手机号'")
    private String phone;           //  手机号

    @Column(nullable = false, columnDefinition = "varchar(800) comment '客服二维码'")
    private String image;           //  客服二维码

      @Column(nullable = false, columnDefinition = "varchar(100) comment '客服名称'")
    private String customName;            //  客服名称

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "timestamp(0) default CURRENT_TIMESTAMP comment '创建时间'")
    private Date createTime;    // 创建时间

    @Column(nullable = true, columnDefinition = "datetime(6) comment '最后更新时间'")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date lastUpdateTime;    // 最后更新时间

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '删除状态：0-未删除，1-已删除'")
    private Byte deleteFlag;    // 删除状态：0-未删除，1-已删除

}
