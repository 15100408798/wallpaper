package com.yushang.wallpaper.layer.router.order;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.model.order.CommentQueryModel;
import com.yushang.wallpaper.model.order.CommentUpdateModel;
import org.springframework.lang.NonNull;

/**
 * 评论管理
 *
 * @author machao
 * @version 1.0
 * @date 2020/12/9 0:55
 * @Description TODO
 */
public interface CommentService {

    /**
     * 查询评论信息列表
     *
     * @param commentQueryModel 筛选参数
     * @return 评论信息列表
     */
    @NonNull
    ResultFul selectCommentList(@NonNull CommentQueryModel commentQueryModel);

    /**
     * 更新评论信息
     *
     * @param commentUpdateModel 更新参数
     * @return 受影响条数
     */
    @NonNull
    ResultFul updateComment(@NonNull CommentUpdateModel commentUpdateModel);
}
