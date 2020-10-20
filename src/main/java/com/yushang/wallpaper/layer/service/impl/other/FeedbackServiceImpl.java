package com.yushang.wallpaper.layer.service.impl.other;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.other.FeekBackMapper;
import com.yushang.wallpaper.common.pojo.other.TbFeekback;
import com.yushang.wallpaper.layer.model.other.FeedbackQueryModel;
import com.yushang.wallpaper.layer.model.other.FeedbackUpdateModel;
import com.yushang.wallpaper.layer.service.other.FeedbackService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 反馈管理模块
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeekBackMapper feekBackMapper;

    /**
     * 查询反馈管理信息列表
     *
     * @param feedbackQueryModel 搜索条件
     * @return 反馈管理信息列表
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectFeedbackList(FeedbackQueryModel feedbackQueryModel) {
        /* 校验参数 */
        Objects.requireNonNull(feedbackQueryModel);
        feedbackQueryModel.validPageSizeIsNull();
        // 分页
        PageHelper.startPage(feedbackQueryModel.getPage(), feedbackQueryModel.getSize());
        // 查询反馈管理信息列表
        Page<TbFeekback> tbFeekbackPage = feekBackMapper.selectFeedbackList(feedbackQueryModel);
        return ResultFul.getSuccessList(tbFeekbackPage.getResult(), tbFeekbackPage.getTotal());
    }

    /**
     * 更新反馈管理信息
     *
     * @param feedbackUpdateModel 反馈管理信息
     * @return 受影响条数
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ResultFul updateFeedback(FeedbackUpdateModel feedbackUpdateModel) {
        /* 校验参数 */
        Objects.requireNonNull(feedbackUpdateModel);
        feedbackUpdateModel.validFeedbackIdIsNotNull();
        String feedbackIds = feedbackUpdateModel.getFeedbackIds();
        String[] feedbackIdValues = feedbackIds.split(",");
        if (ArrayUtils.isEmpty(feedbackIdValues)){
            throw new NullPointerException();
        }
        feedbackUpdateModel.setFeedbackIdValues(feedbackIdValues);
        // 更新反馈管理信息
        int updateCount = feekBackMapper.updateFeedback(feedbackUpdateModel);
        return ResultFul.getSuccessTotal(updateCount);
    }

}
