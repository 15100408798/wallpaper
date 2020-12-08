package com.yushang.wallpaper.model.order;

import com.yushang.wallpaper.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 评论管理--Request Query Model
 */
@Setter
@Getter
@NoArgsConstructor
public class CommentQueryModel extends BaseModel {

    private static final long serialVersionUID = -8943664292827901813L;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 评论状态: 0-已上架, 1-已下架
     */
    private Integer commentStatus;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;


}
