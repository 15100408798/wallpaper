package com.yushang.wallpaper.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "tb_rebate")
@Data
public class TbRebate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "tinyint(1) comment '会员折扣'")
    private Byte rebateId;

    @Column(nullable = false,columnDefinition = "varchar(32) comment '折扣'")
    private String rebateName;//折扣

    @Column(nullable = false,columnDefinition = "tinyint(1) default 1 comment '1 普通  2 vip1  3 vip 2  4 vip3  5 vip4'")
    private Byte gradel;//1 普通  2 vip1  3 vip 2  4 vip3  5 vip4

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @Column(nullable = false,columnDefinition = "datetime(6) comment '创建时间'")
    private Date createTime;

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '0 未删除 1 已删除'")
    private Byte deleteFlag;// 0 未删除 1 已删除

}
