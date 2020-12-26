package com.yushang.wallpaper.layer.controller.order;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.enums.StatusEnum;
import com.yushang.wallpaper.layer.router.order.CommentService;
import com.yushang.wallpaper.layer.model.enums.LogEnum;
import com.yushang.wallpaper.layer.model.order.comment.CommentQueryModel;
import com.yushang.wallpaper.layer.model.order.comment.CommentUpdateModel;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论管理模块
 */
@RestController
@RequestMapping(value = "/layer/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PermissionName(value = "查询评论信息列表")
    @RequiresPermissions(value = {"system:selectCommentList"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "查询评论信息列表", tabName = "tb_comment", operateType = 1, logEnum = LogEnum.COMMENT_LIST)
    @RequestMapping("selectCommentList")
    public ResultFul selectCommentList(CommentQueryModel commentQueryModel) {
        return commentService.selectCommentList(commentQueryModel);
    }

    @PermissionName(value = "删除评论信息")
    @RequiresPermissions(value = {"system:deleteComment"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "删除评论信息", tabName = "tb_comment", operateType = 2, logEnum = LogEnum.DEL_COMMENT)
    @RequestMapping("deleteComment")
    public ResultFul deleteComment(CommentUpdateModel commentUpdateModel) {
        commentUpdateModel.setDeleteFlag(StatusEnum.DELETE_YES.getCode());
        return commentService.updateComment(commentUpdateModel);
    }


    @PermissionName(value = "下架评论信息")
    @RequiresPermissions(value = {"system:disabledComment"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "下架评论信息", tabName = "tb_comment", operateType = 6, logEnum = LogEnum.DISABLE_COMMENT)
    @RequestMapping("disabledComment")
    public ResultFul disabledComment(CommentUpdateModel commentUpdateModel) {
        commentUpdateModel.setCommentStatus(StatusEnum.DOWN.getCode());
        return commentService.updateComment(commentUpdateModel);
    }

    @PermissionName(value = "上架评论信息")
    @RequiresPermissions(value = {"system:openComment"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "上架评论信息", tabName = "tb_comment", operateType = 5, logEnum = LogEnum.OPEN_COMMENT)
    @RequestMapping("openComment")
    public ResultFul openComment(CommentUpdateModel commentUpdateModel) {
        commentUpdateModel.setCommentStatus(StatusEnum.UP.getCode());
        return commentService.updateComment(commentUpdateModel);
    }


}
