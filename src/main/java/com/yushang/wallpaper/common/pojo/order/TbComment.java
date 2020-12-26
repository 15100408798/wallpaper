package com.yushang.wallpaper.common.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论管理
 */
@Setter
@Getter
@NoArgsConstructor
public class TbComment implements Serializable {

    private static final long serialVersionUID = 6366728846747373736L;
    /**
     * 评论id
     */
    private Long commentId;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 状态 1-已上架,2-已下架
     */
    private Byte status;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date createTime;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Byte deleteFlag;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 用户昵称
     */
    private String userNick;

}
