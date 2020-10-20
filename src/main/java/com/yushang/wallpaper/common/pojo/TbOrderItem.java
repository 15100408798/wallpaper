package com.yushang.wallpaper.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "tb_order_item")
public class TbOrderItem implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,columnDefinition = "int(32) comment '订单详情表'")
	private Integer orderItemId;//id

	@Column(nullable = false,columnDefinition = "int(32) comment '订单id'")
	private Integer orderId;//订单id

	@Column(nullable = false,columnDefinition = "int(32) comment '商品id'")
	private Integer productId;//商品id

	@Column(nullable = false,columnDefinition = "int(32) comment '商品分类skuid'")
	private Integer productSkuId;//商品分类skuid

	@Column(nullable = false,columnDefinition = "decimal(32,2) default 0.00 comment '产品价格、单价'")
	private BigDecimal productPrice;//产品价格、单价

	@Column(nullable = false,columnDefinition = "smallint(6) default 0 comment '数量'")
	private Short procutNum;//数量

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
	@Column(nullable = false,columnDefinition = "datetime(6) comment '创建时间'")
	private Date createTime;		//创建时间

	@Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '0  未删除    1  已删除'")
	private Byte isDel;         //  0  未删除    1  已删除

	@Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment ' 0  未评价    1  已评价'")
	private Byte isComment;         //  0  未评价    1  已评价

	@Column(nullable = false,columnDefinition = "int(32)  comment '用户id'")
	private Integer userId;		//用户id

}
