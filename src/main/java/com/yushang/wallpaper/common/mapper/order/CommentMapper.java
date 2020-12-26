package com.yushang.wallpaper.common.mapper.order;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.order.TbComment;
import com.yushang.wallpaper.layer.model.order.comment.CommentQueryModel;
import com.yushang.wallpaper.layer.model.order.comment.CommentUpdateModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论管理
 *
 * @author machao
 * @version 1.0
 * @date 2020/12/9 1:08
 * @Description TODO
 */
@Mapper
public interface CommentMapper {

    /**
     * 查询评论信息列表
     *
     * @param commentQueryModel 筛选参数
     * @return 评论信息列表
     */
    Page<TbComment> selectCommentList(CommentQueryModel commentQueryModel);

    /**
     * 更新评论信息
     *
     * @param commentUpdateModel 更新参数
     * @return 受影响条数
     */
    int updateComment(CommentUpdateModel commentUpdateModel);
}
