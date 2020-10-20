package com.yushang.wallpaper.common.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tb_custom_service")
@Data
public class TbCustomService implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,columnDefinition = "tinyint(3) comment '客服人员id'")
	private Byte customServiceId;

	@Column(nullable = false,columnDefinition = "varchar(32) comment '微信号'")
	private String weixin;//微信号

	@Column(nullable = false,columnDefinition = "varchar(32) comment '手机号'")
	private String phone;//手机号

	@Column(nullable = false,columnDefinition = "varchar(32) comment '客服二维码'")
	private String image;//客服二维码

	@Column(nullable = false,columnDefinition = "varchar(32) comment '客服名称'")
	private String name;//客服名称

	@Column(nullable = false,columnDefinition = "tinyint(1) default 2 comment '1 推荐  2 不推荐'")
	private Byte isTop;	//1 推荐  2 不推荐

	@Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '0 未删除 1 已删除'")
	private Byte deleteFlag;// 0 未删除 1 已删除

}
