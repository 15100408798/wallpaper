package com.yushang.wallpaper.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 购物车
 */

@Entity(name = "tb_complain_type")
@Data
public class TbComplainType implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,columnDefinition = "int(32) comment '购物车Id'")
	private Integer complainTypeId;//购物车Id

	@Column(nullable = false,columnDefinition = "int(32) comment '商品关联id'")
	private Integer productSkuId;//商品关联id

	@Column(nullable = false,columnDefinition = "int(32) comment '用户id'")
	private Integer userId;//用户id

	@Column(nullable = false,columnDefinition = "int(32) comment '商品id'")
	private Integer productId;//商品id

	@Column(nullable = false,columnDefinition = "smallint(6) default 0 comment '数量'")
	private Short productNum;//数量

	@Column(nullable = false,columnDefinition = "tinyint(1) default 2 comment '1自提，2快递'")
	private Byte sendType;//配送方式(1自提，2快递)

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GTM+8")
	@Column(nullable = false,columnDefinition = "datetime(6) comment '创建时间'")
	private Date createTime;	      //创建时间

	@Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '0 未删除 1 已删除'")
	private Byte deleteFlag;// 0 未删除 1 已删除

}
