package com.yushang.wallpaper.layer.router.log;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.pojo.log.TbLog;
import com.yushang.wallpaper.model.log.LogReqModel;
import org.springframework.lang.NonNull;

/**
 * 日志业务层
 */
public interface LogService {

    /**
     * 储存日志
     *
     * @param tbLog 日志信息
     * @return 受影响条数
     */
    @NonNull
    int insertLog(@NonNull TbLog tbLog);

    /**
     * 查询日志列表
     *
     * @param reqModel 请求参数
     * @return 日志列表
     */
    @NonNull
    ResultFul selectLogList(@NonNull LogReqModel reqModel);

}
