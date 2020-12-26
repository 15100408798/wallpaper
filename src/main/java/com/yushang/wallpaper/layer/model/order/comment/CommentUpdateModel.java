package com.yushang.wallpaper.layer.model.order.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * 评论管理--Request Update Model
 */
@Setter
@Getter
@NoArgsConstructor
public class CommentUpdateModel implements Serializable {

    private static final long serialVersionUID = 2185927914395428259L;
    /**
     * 评论信息ID
     */
    private String commentIds;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 评论信息ID数组
     */
    private String[] commentIdValues;
    /**
     * 评论状态: 0-已上架, 1-已下架
     */
    private Integer commentStatus;

    public void validCommentIdIsNotNull() {
        Objects.requireNonNull(commentIds);
    }

}
