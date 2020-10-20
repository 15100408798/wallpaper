package com.yushang.wallpaper.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "tb_active")
@Data
public class TbActive implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(32) comment '活动id'")
    private Integer activeId;//活动id

    private String activeName;//活动名称

    private String imageUrl;//活动封面图

    private String photo;//互动详情图

    private String linkName;//联系人

    private String linkPhone;//联系人联系电话

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '等级限制 0普通用户 1.vip1 2 vip2 3 vip3 4.vip4'")
    private Byte userLevel;//等级限制 0普通用户 1.vip1 2 vip2 3 vip3 4.vip4

    private String introduct;//活动介绍

    private Integer maxNumber;//最大人数

    @Column(nullable = false,columnDefinition = "tinyint(1) default 1 comment ' 首页推荐 1.否 2 是'")
    private Byte isHomePage;// 首页推荐 1.否 2 是

    @Column(nullable = false,columnDefinition = "decimal(9,2)  comment ' 价格 0.免费'")
    private BigDecimal price ;//价格 0.免费

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '0.上架 1.下架'")
    private Byte status; //0.上架 1.已下架

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '0 否 1 是'")
    private Byte isCheck ;//是否需要审核 0否 1是

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '是否报名人信息 0 否 1 是'")
    private Byte isBaseInfo;//是否报名人信息 0否 1是

    private Long provinceId;//省份ID

    private Long cityId;//城市ID

    private Long areaId;//地区ID

    private String address;//详细地址

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GTM+8")
    @Column(nullable = false,columnDefinition = "datetime(6) comment '创建时间'")
    private Date createTime;	      //创建时间

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '0 未删除 1 已删除'")
    private Byte deleteFlag;// 0 未删除 1 已删除

    private String startTime; //活动开始时间

    private String endTime; //活动结束时间

    private String enrollStartTime;//活动报名开始时间

    private String enrollEndTime;//活动报名结束时间

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '是否需要跟团人名字 0否 1是'")
    private Byte activeTeamName; //是否需要跟团人名字 0否 1是

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '是否需要跟团人性别 0否 1是'")
    private Byte activeTeamSex;//是否需要跟团人性别 0否 1是

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '是否需要跟团人手机号 0否 1是'")
    private Byte activeTeamPhone;//是否需要跟团人手机号 0否 1是

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '是否需要跟团人身份证 0否 1是'")
    private Byte activeTeanIdcard;//是否需要跟团人身份证 0否 1是

}

