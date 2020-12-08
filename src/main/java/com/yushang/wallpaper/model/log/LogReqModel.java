package com.yushang.wallpaper.model.log;

import com.yushang.wallpaper.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LogReqModel extends BaseModel {

    private static final long serialVersionUID = 9180968311769605025L;

    /**
     * 操作的类型
     * 1-查询, 2-删除
     * 3-开启, 4-禁用
     * 5-上架, 6-下架
     * 7 推荐  8 不推荐
     * 9 处理反馈信息 10 忽略反馈信息 11添加
     */
    private Integer operateTypeValue;   // 操作类型
    private String username;  // 管理员账号


}
