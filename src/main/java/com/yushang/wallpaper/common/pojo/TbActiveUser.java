package com.yushang.wallpaper.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "tb_active_user")
@Data
public class TbActiveUser implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(32) comment '活动报名人id'")
    private Integer activeUserId;//id

    private String code;//票号

    private Integer userId;//用户id

    private String userName;//姓名

    private String userPhone;//联系电话

    private Integer activeId;//活动id

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment ' 0 待审核  1 审核通过  2 审核不通过'")
    private Byte status;//0 待审核  1 审核通过  2 审核不通过

    private Short teamNum;//跟团人数

    @Column(nullable = false,columnDefinition = "decimal(15) comment '实际付款'")
    private BigDecimal price;//实际付款

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GTM+8")
    @Column(nullable = false,columnDefinition = "datetime(6) comment '创建时间'")
    private Date createTime;	      //创建时间

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '0 未删除 1 已删除'")
    private Byte deleteFlag;// 0 未删除 1 已删除

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GTM+8")
    @Column(nullable = true,columnDefinition = "datetime(6) comment '结束时间'")
    private Date endTime;//结束时间

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GTM+8")
    @Column(nullable = true,columnDefinition = "datetime(6) comment '支付时间'")
    private Date payTime;//支付时间

    @Column(nullable = false,columnDefinition = "tinyint(1) default 1 comment '1 未支付  2 已支付'")
    private Byte payStatus;//1 未支付  2 已支付

    @Column(nullable = false,columnDefinition = "tinyint(1) default 1 comment '1  微信支付  2 余额支付'")
    private Byte payType;//1  微信支付  2 余额支付


}
