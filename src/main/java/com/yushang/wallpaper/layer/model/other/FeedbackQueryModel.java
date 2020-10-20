package com.yushang.wallpaper.layer.model.other;

import com.yushang.wallpaper.layer.model.base.BaseModel;
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

    private static final long serialVersionUID = 40163152639504333L;
    private Integer feedbackStatus;     // 处理状态：1-待处理；2-已处理；3-已忽略
    private Integer deleteFlag = 0; // 删除状态：0-未删除，1-已删除
    private String username;    // 用户账号

}
