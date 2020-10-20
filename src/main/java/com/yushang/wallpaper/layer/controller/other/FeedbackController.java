package com.yushang.wallpaper.layer.controller.other;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.enums.user.UserLoginLogEnum;
import com.yushang.wallpaper.layer.model.other.FeedbackQueryModel;
import com.yushang.wallpaper.layer.model.other.FeedbackUpdateModel;
import com.yushang.wallpaper.layer.service.other.FeedbackService;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 反馈模块
 */
@RestController
@RequestMapping(value = "layer/feekback")
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    @PermissionName(value = "查询反馈信息列表")
    @RequiresPermissions(value = {"system:selectFeedbackList"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "查询反馈信息列表", tabName = "tb_feekback", operateType = 1, logEnum = UserLoginLogEnum.FEEDBACK_LIST)
    @RequestMapping("selectFeedbackList")
    public ResultFul selectFeedbackList(FeedbackQueryModel feedbackQueryModel) {
        return feedbackService.selectFeedbackList(feedbackQueryModel);
    }

    @PermissionName(value = "删除反馈信息")
    @RequiresPermissions(value = {"system:deleteFeedback"})
    @RequiresRoles(value = {"1"})
    @Log(title = "删除反馈信息", tabName = "tb_feekback", operateType = 2, logEnum = UserLoginLogEnum.DEL_FEEDBACK)
    @RequestMapping("deleteFeedback")
    public ResultFul deleteFeedback(FeedbackUpdateModel feedbackUpdateModel) {
        feedbackUpdateModel.setDeleteFlag(NumberUtils.INTEGER_ONE);
        return feedbackService.updateFeedback(feedbackUpdateModel);
    }


    @PermissionName(value = "忽略反馈信息")
    @RequiresPermissions(value = {"system:disabledFeedback"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "忽略反馈信息", tabName = "tb_feekback", operateType = 10, logEnum = UserLoginLogEnum.DISABLE_FEEDBACK)
    @RequestMapping("disabledFeedback")
    public ResultFul disabledFeedback(FeedbackUpdateModel feedbackUpdateModel) {
        feedbackUpdateModel.setFeedbackStatus(3);
        return feedbackService.updateFeedback(feedbackUpdateModel);
    }

    @PermissionName(value = "处理反馈信息")
    @RequiresPermissions(value = {"system:openFeedback"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "处理反馈信息", tabName = "tb_feekback", operateType = 9, logEnum = UserLoginLogEnum.OPEN_FEEDBACK)
    @RequestMapping("openFeedback")
    public ResultFul openFeedback(FeedbackUpdateModel feedbackUpdateModel) {
        feedbackUpdateModel.setFeedbackStatus(2);
        return feedbackService.updateFeedback(feedbackUpdateModel);
    }


}
