package com.yushang.wallpaper.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Entity(name = "tb_week_money")
public class TbWeekMoney implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,columnDefinition = "int(32) comment '周统计表'")
	private Integer weekMoneyId;//id

	@Column(nullable = false,columnDefinition = "tinyint(1) default 1 comment '1 待付款    2  已付款'")
	private Byte status;//1 待付款    2  已付款


	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
	@Column(nullable = false,columnDefinition = "datetime(6) comment '创建时间'")
	private Date createTime;

	@Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '0 未删除 1 已删除'")
	private Byte deleteFlag;// 0 未删除 1 已删除

	@Column(nullable = false,columnDefinition = "bigint(32) comment '商户id'")
	private Long shopId;//商户id

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
	@Column(nullable = false,columnDefinition = "datetime(6) comment '开始时间'")
	private Date startTime;//开始时间

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
	@Column(nullable = false,columnDefinition = "datetime(6) comment '结束时间'")
	private Date endTime;//结束时间

	@Column(nullable = false,columnDefinition = "decimal(6) comment '总金额'")
	private BigDecimal allMoney;//总金额

	@Column(nullable = false,columnDefinition = "decimal(6) comment '付款金额'")
	private BigDecimal payMoney;//付款金额

	
}
