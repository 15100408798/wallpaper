package com.yushang.wallpaper.layer.controller.other;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.enums.FeekbackStatusEnum;
import com.yushang.wallpaper.common.enums.StatusEnum;
import com.yushang.wallpaper.model.enums.LogEnum;
import com.yushang.wallpaper.model.other.FeedbackQueryModel;
import com.yushang.wallpaper.model.other.FeedbackUpdateModel;
import com.yushang.wallpaper.layer.router.other.FeedbackService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 反馈模块
 */
@RestController
@RequestMapping(value = "layer/feekback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PermissionName(value = "查询反馈信息列表")
    @RequiresPermissions(value = {"system:selectFeedbackList"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "查询反馈信息列表", tabName = "tb_feekback", operateType = 1, logEnum = LogEnum.FEEDBACK_LIST)
    @RequestMapping("selectFeedbackList")
    public ResultFul selectFeedbackList(FeedbackQueryModel feedbackQueryModel) {
        return feedbackService.selectFeedbackList(feedbackQueryModel);
    }

    @PermissionName(value = "删除反馈信息")
    @RequiresPermissions(value = {"system:deleteFeedback"})
    @RequiresRoles(value = {"1"})
    @Log(title = "删除反馈信息", tabName = "tb_feekback", operateType = 2, logEnum = LogEnum.DEL_FEEDBACK)
    @RequestMapping("deleteFeedback")
    public ResultFul deleteFeedback(FeedbackUpdateModel feedbackUpdateModel) {
        feedbackUpdateModel.setDeleteFlag(StatusEnum.DELETE_YES.getCode());
        return feedbackService.updateFeedback(feedbackUpdateModel);
    }


    @PermissionName(value = "忽略反馈信息")
    @RequiresPermissions(value = {"system:disabledFeedback"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "忽略反馈信息", tabName = "tb_feekback", operateType = 10, logEnum = LogEnum.DISABLE_FEEDBACK)
    @RequestMapping("disabledFeedback")
    public ResultFul disabledFeedback(FeedbackUpdateModel feedbackUpdateModel) {
        feedbackUpdateModel.setFeedbackStatus(FeekbackStatusEnum.FEEKBACK_STATUS_IGNORE.getCode());
        return feedbackService.updateFeedback(feedbackUpdateModel);
    }

    @PermissionName(value = "处理反馈信息")
    @RequiresPermissions(value = {"system:openFeedback"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "处理反馈信息", tabName = "tb_feekback", operateType = 9, logEnum = LogEnum.OPEN_FEEDBACK)
    @RequestMapping("openFeedback")
    public ResultFul openFeedback(FeedbackUpdateModel feedbackUpdateModel) {
        feedbackUpdateModel.setFeedbackStatus(FeekbackStatusEnum.FEEKBACK_STATUS_YES.getCode());
        return feedbackService.updateFeedback(feedbackUpdateModel);
    }


}
