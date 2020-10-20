package com.yushang.wallpaper.common.pojo.store;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品类别
 */
@Entity(name = "tb_product_label")
@Setter
@Getter
@NoArgsConstructor
public class TbProductLabel implements Serializable {

    private static final long serialVersionUID = -6637381474699051811L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "smallint(6) comment '商品类型id'")
    private Short productLabelId;    // 商品类别id

    @Column(nullable = false, unique = true, columnDefinition = "varchar(100) comment '商品类别名称'")
    private String productLabelName;        //  商品类别名称

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "timestamp(0) default CURRENT_TIMESTAMP comment '创建时间'")
    private Date createTime;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '删除状态: 0-未删除 1-已删除'")
    private Byte deleteFlag;    // 删除状态: 0-未删除 1-已删除

}
