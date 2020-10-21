package com.yushang.wallpaper.layer.controller.order;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.enums.user.UserLoginLogEnum;
import com.yushang.wallpaper.layer.model.order.CommentQueryModel;
import com.yushang.wallpaper.layer.model.order.CommentUpdateModel;
import com.yushang.wallpaper.layer.service.order.CommentService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 评论管理模块
 */
@RestController
@RequestMapping(value = "/layer/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @PermissionName(value = "查询评论信息列表")
    @RequiresPermissions(value = {"comment:selectList"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "查询评论信息列表", tabName = "tb_comment", operateType = 1, logEnum = UserLoginLogEnum.COMMENT_LIST)
    @RequestMapping("selectList")
    public ResultFul selectList(CommentQueryModel commentQueryModel) {
        return commentService.selectList(commentQueryModel);
    }

    @PermissionName(value = "删除评论信息")
    @RequiresPermissions(value = {"comment:deleteComment"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "删除评论信息", tabName = "tb_comment", operateType = 2, logEnum = UserLoginLogEnum.DEL_COMMENT)
    @RequestMapping("deleteComment")
    public ResultFul deleteComment(CommentUpdateModel commentUpdateModel) {
        commentUpdateModel.setDeleteFlag(1);
        return commentService.updateCommentInfo(commentUpdateModel);
    }

    @PermissionName(value = "上架评论信息")
    @RequiresPermissions(value = {"comment:upComment"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "上架评论信息", tabName = "tb_comment", operateType = 5, logEnum = UserLoginLogEnum.UP_COMMENT)
    @RequestMapping("upComment")
    public ResultFul upComment(CommentUpdateModel commentUpdateModel) {
        commentUpdateModel.setStatus(0);
        return commentService.updateCommentInfo(commentUpdateModel);
    }

    @PermissionName(value = "下架评论信息")
    @RequiresPermissions(value = {"comment:downComment"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "下架评论信息", tabName = "tb_comment", operateType = 6, logEnum = UserLoginLogEnum.DOWN_COMMENT)
    @RequestMapping("downComment")
    public ResultFul downComment(CommentUpdateModel commentUpdateModel) {
        commentUpdateModel.setStatus(1);
        return commentService.updateCommentInfo(commentUpdateModel);
    }

}
