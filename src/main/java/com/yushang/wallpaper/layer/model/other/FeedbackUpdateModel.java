package com.yushang.wallpaper.layer.model.other;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * 反馈管理--Request Update Model
 */
@Setter
@Getter
@NoArgsConstructor
public class FeedbackUpdateModel implements Serializable {

    private static final long serialVersionUID = -5772030721790153569L;
    /**
     * 反馈信息ID
     */
    private String feedbackIds;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 反馈信息ID数组
     */
    private String[] feedbackIdValues;
    /**
     * 反馈状态：1-待处理，2-已处理，3-已忽略
     */
    private Integer feedbackStatus;

    public void validFeedbackIdIsNotNull() {
        Objects.requireNonNull(feedbackIds);
    }

}
