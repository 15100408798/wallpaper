package com.yushang.wallpaper.common.mapper.other;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.other.TbFeekback;
import com.yushang.wallpaper.layer.model.other.FeedbackQueryModel;
import com.yushang.wallpaper.layer.model.other.FeedbackUpdateModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeekBackMapper {

    /**
     * 查询反馈管理信息列表
     *
     * @param feedbackQueryModel 搜索条件
     * @return 反馈管理信息列表
     */
    Page<TbFeekback> selectFeedbackList(FeedbackQueryModel feedbackQueryModel);

    /**
     * 更新反馈管理信息
     *
     * @param feedbackUpdateModel 反馈管理信息
     * @return 受影响条数
     */
    int updateFeedback(FeedbackUpdateModel feedbackUpdateModel);
}
