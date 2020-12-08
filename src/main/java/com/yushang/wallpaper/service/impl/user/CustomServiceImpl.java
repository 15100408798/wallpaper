package com.yushang.wallpaper.service.impl.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.CustomMapper;
import com.yushang.wallpaper.common.pojo.user.TbCustom;
import com.yushang.wallpaper.model.user.CustomQueryModel;
import com.yushang.wallpaper.model.user.CustomUpdateModel;
import com.yushang.wallpaper.service.user.CustomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * 客服模块
 */
@Service
public class CustomServiceImpl implements CustomService {

    @Resource
    private CustomMapper customMapper;

    /**
     * 查询客户客服列表
     *
     * @param customQueryModel 查询参数
     * @return 客户客服列表
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectList(CustomQueryModel customQueryModel) {
        /* 校验参数 */
        Objects.requireNonNull(customQueryModel);
        customQueryModel.validPageSizeIsNull();
        // 分页
        PageHelper.startPage(customQueryModel.getPage(), customQueryModel.getSize());
        // 查询客户客服列表
        Page<TbCustom> tbUserPage = customMapper.selectList(customQueryModel);
        return ResultFul.getSuccessList(tbUserPage.getResult(), tbUserPage.getTotal());
    }

    /**
     * 更新客户客服信息
     *
     * @param customUpdateModel 客户客服信息
     * @return 受影响条数
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ResultFul updateCustomInfo(CustomUpdateModel customUpdateModel) {
        /* 校验参数 */
        Objects.requireNonNull(customUpdateModel);
        customUpdateModel.validCustomIdNonNull();
        String customIds = customUpdateModel.getCustomIds();
        String[] customIdValues = customIds.split(",");     // 用户ID集合
        customUpdateModel.setCustomIdValues(customIdValues);
        customUpdateModel.setLastUpdateTime(new Date());
        // 更新用户信息
        int updateCount = customMapper.updateCustomInfo(customUpdateModel);
        return ResultFul.getSuccessTotal(updateCount);
    }


}
