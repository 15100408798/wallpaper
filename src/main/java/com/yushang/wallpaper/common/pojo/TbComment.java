package com.yushang.wallpaper.common.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 评论管理
 */
@Setter
@Getter
@NoArgsConstructor
//@Entity(name = "tb_comment")
public class TbComment implements Serializable {

    private static final long serialVersionUID = 3713865869151695847L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false, columnDefinition = "bigint(64) comment '评论id'")
    private Long commentId;         //  评论id

//    @Column(nullable = false, columnDefinition = "varchar(100) comment '订单编号'")
    private String orderNo;         //  订单编号
    private Long orderItemId;       //  订单详情id
    private String content;         //  评论内容
    private String imgs;            //  评论照片
    private Long productId;         //  商品id
    private String productName;     //  商品名称
    private String skuName;         //  sku名称
    private Long userId;            //  用户id
    private String userName;        //  用户姓名
    private String userImage;       //  用户头像
    private Integer userLevel;      //  用户等级
    private Double scoreDesc;       //  描述相符(几颗星)
    private Double scroeService;    //  服务态度
    private Double scoreTotal;      //  总体评价
    private Integer status;         //  状态 1 已上架  2 已下架
    private String createTime;      //  创建时间
    private Integer deleteFlag;     //  0 未删除 1 已删除
    private String productSkuName;  //  商品sku名称
    private List<String> photoList;


}
