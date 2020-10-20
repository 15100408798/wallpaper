package com.yushang.wallpaper.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "tb_subscribe")
@Data
public class TbSubscribe implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(32) comment '商家id'")
    private Integer subscribeId;   //预约主键

    private Integer shopId;    //商品外键

    private Integer userId;    //预约人外键

    private String subscribeDay;   //预约日期

    private String subscribeTime;   //上午或下午

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @Column(nullable = false,columnDefinition = "datetime(6) comment '创建时间'")
    private Date createTime;

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '0 未删除 1 已删除'")
    private Byte deleteFlag;// 0 未删除 1 已删除
}
