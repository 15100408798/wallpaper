package com.yushang.wallpaper.service.other;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.model.other.FeedbackQueryModel;
import com.yushang.wallpaper.model.other.FeedbackUpdateModel;
import org.springframework.lang.NonNull;

/**
 * 反馈管理模块
 */
public interface FeedbackService {

    /**
     * 查询反馈管理信息列表
     *
     * @param feedbackQueryModel 搜索条件
     * @return 反馈管理信息列表
     */
    @NonNull
    ResultFul selectFeedbackList(@NonNull FeedbackQueryModel feedbackQueryModel);

    /**
     * 更新反馈管理信息
     *
     * @param feedbackUpdateModel 反馈管理信息
     * @return 受影响条数
     */
    @NonNull
    ResultFul updateFeedback(@NonNull FeedbackUpdateModel feedbackUpdateModel);

}
