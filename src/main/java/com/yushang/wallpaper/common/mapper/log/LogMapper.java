package com.yushang.wallpaper.common.mapper.log;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.log.TbLog;
import com.yushang.wallpaper.model.log.LogReqModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {

    /**
     * 储存日志
     *
     * @param tbLog 日志信息
     * @return 受影响条数
     */
    int insertLog(TbLog tbLog);

    /**
     * 查询日志列表
     *
     * @param reqModel 请求参数
     * @return 日志列表
     */
    Page<TbLog> selectLogList(LogReqModel reqModel);

}
