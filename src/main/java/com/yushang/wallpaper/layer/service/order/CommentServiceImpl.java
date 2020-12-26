package com.yushang.wallpaper.layer.service.order;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.order.CommentMapper;
import com.yushang.wallpaper.common.pojo.order.TbComment;
import com.yushang.wallpaper.layer.router.order.CommentService;
import com.yushang.wallpaper.layer.model.order.comment.CommentQueryModel;
import com.yushang.wallpaper.layer.model.order.comment.CommentUpdateModel;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 评论管理
 *
 * @author machao
 * @version 1.0
 * @date 2020/12/9 0:57
 * @Description TODO
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 查询评论信息列表
     *
     * @param commentQueryModel 筛选参数
     * @return 评论信息列表
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectCommentList(CommentQueryModel commentQueryModel) {
        /** 校验参数 */
        Objects.requireNonNull(commentQueryModel);
        commentQueryModel.validPageSizeIsNull();
        commentQueryModel.startPage();
        // 查询反馈管理信息列表
        Page<TbComment> tbFeekbackPage = commentMapper.selectCommentList(commentQueryModel);
        return ResultFul.getSuccessList(tbFeekbackPage.getResult(), tbFeekbackPage.getTotal());
    }

    /**
     * 更新评论信息
     *
     * @param commentUpdateModel 更新参数
     * @return 受影响条数
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ResultFul updateComment(CommentUpdateModel commentUpdateModel) {
        /* 校验参数 */
        Objects.requireNonNull(commentUpdateModel);
        commentUpdateModel.validCommentIdIsNotNull();
        String commentIds = commentUpdateModel.getCommentIds();
        String[] commentIdValues = commentIds.split(",");
        if (ArrayUtils.isEmpty(commentIdValues)){
            throw new NullPointerException();
        }
        commentUpdateModel.setCommentIdValues(commentIdValues);
        // 更新评论信息
        int updateCount = commentMapper.updateComment(commentUpdateModel);
        if (updateCount != commentIdValues.length) {
            throw new RuntimeException("更新评论信息失败");
        }
        return ResultFul.getSuccessTotal(updateCount);
    }


}
