package com.yushang.wallpaper.common.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论管理
 */
@Setter
@Getter
@NoArgsConstructor
public class TbCommentDetail implements Serializable {

    /**
     * 评论id
     */
    private Long commentId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单详情id
     */
    private Long orderItemId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论照片
     */
    private String imgs;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userImage;
    /**
     * 用户等级
     */
    private Integer userLevel;
    /**
     * 描述相符(几颗星)
     */
    private Double scoreDesc;
    /**
     * 服务态度
     */
    private Double scroeService;
    /**
     * 总体评价
     */
    private Double scoreTotal;
    /**
     * 状态 1-已上架,2-已下架
     */
    private Integer status;
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
     * 商品sku名称
     */
    private String productSkuName;
    private List<String> photoList;


}
