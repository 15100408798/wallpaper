package com.yushang.wallpaper.layer.service.impl.log;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.log.LogMapper;
import com.yushang.wallpaper.common.pojo.log.TbLog;
import com.yushang.wallpaper.layer.model.log.LogReqModel;
import com.yushang.wallpaper.layer.service.log.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class LoggerServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    /**
     * 储存日志
     *
     * @param tbLog 日志信息
     * @return 受影响条数
     */
    @Transactional
    @Override
    public int insertLog(TbLog tbLog) {
        Objects.requireNonNull(tbLog);
        return logMapper.insertLog(tbLog);
    }

    /**
     * 查询日志列表
     *
     * @param reqModel 请求参数
     * @return 日志列表
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectLogList(LogReqModel reqModel) {
        /* 校验参数 */
        Objects.requireNonNull(reqModel);
        reqModel.validPageSizeIsNull();
        // 分页
        PageHelper.startPage(reqModel.getPage(), reqModel.getSize());
        // 查询日志列表
        Page<TbLog> tbLogPage = logMapper.selectLogList(reqModel);
        return ResultFul.getSuccessList(tbLogPage.getResult(), tbLogPage.getTotal());
    }


}
