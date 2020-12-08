package com.yushang.wallpaper.model.other;

import com.yushang.wallpaper.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 反馈管理--Request Query Model
 */
@Setter
@Getter
@NoArgsConstructor
public class FeedbackQueryModel extends BaseModel {

    private static final long serialVersionUID = 3900829025325946197L;
    /**
     * 处理状态：1-待处理；2-已处理；3-已忽略
     */
    private Integer feedbackStatus;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 用户账号
     */
    private String username;

}
