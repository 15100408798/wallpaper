package com.yushang.wallpaper.layer.service.user;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.enums.StatusEnum;
import com.yushang.wallpaper.common.mapper.user.CustomMapper;
import com.yushang.wallpaper.common.pojo.user.TbCustom;
import com.yushang.wallpaper.layer.model.user.custom.CustomInsertModel;
import com.yushang.wallpaper.layer.model.user.custom.CustomQueryModel;
import com.yushang.wallpaper.layer.model.user.custom.CustomUpdateModel;
import com.yushang.wallpaper.layer.router.user.CustomService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Objects;

/**
 * 客服模块
 */
@Service
public class CustomServiceImpl implements CustomService {

    @Autowired
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
        customQueryModel.startPage();
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
        /** 校验手机号 */
        CustomQueryModel customQueryModel = new CustomQueryModel();
        customQueryModel.setNotCustomId(Integer.parseInt(customUpdateModel.getCustomIds()));
        if (StringUtils.isNotBlank(customUpdateModel.getPhone())) {
            customQueryModel.setCustomPhone(customUpdateModel.getPhone());
            Page<TbCustom> tbCustomPage = customMapper.selectList(customQueryModel);
            if (!CollectionUtils.isEmpty(tbCustomPage.getResult())) {
                return ResultFul.getErrorMessage("手机号已经存在");
            }
        }
        /** 校验微信号 */
        if (StringUtils.isNotBlank(customUpdateModel.getWxOpenId())) {
            customQueryModel.setCustomPhone(null);
            customQueryModel.setWxOpenId(customUpdateModel.getWxOpenId());
            Page<TbCustom> wxOpenIdPage = customMapper.selectList(customQueryModel);
            if (!CollectionUtils.isEmpty(wxOpenIdPage.getResult())) {
                return ResultFul.getErrorMessage("微信号已经存在");
            }
        }
        customUpdateModel.setLastUpdateTime(new Date());
        // 更新客户客服信息
        int updateCount = customMapper.updateCustomInfo(customUpdateModel);
        if (updateCount != customIdValues.length) {
            throw new RuntimeException("更新客户客服信息失败");
        }
        return ResultFul.getSuccessTotal(updateCount);
    }

    /**
     * 新增客户客服信息
     *
     * @param customInsertModel 客户客服信息
     * @return 受影响条数
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ResultFul insertCustom(CustomInsertModel customInsertModel) {
        /* 校验参数 */
        Objects.requireNonNull(customInsertModel);
        customInsertModel.validParams();
        /** 校验手机号 */
        CustomQueryModel customQueryModel = new CustomQueryModel();
        customQueryModel.setCustomPhone(customInsertModel.getPhone());
        Page<TbCustom> tbCustomPage = customMapper.selectList(customQueryModel);
        if (!CollectionUtils.isEmpty(tbCustomPage.getResult())) {
            return ResultFul.getErrorMessage("手机号已经存在");
        }
        /** 校验微信号 */
        customQueryModel.setCustomPhone(null);
        customQueryModel.setWxOpenId(customInsertModel.getWxOpenId());
        Page<TbCustom> wxOpenIdPage = customMapper.selectList(customQueryModel);
        if (!CollectionUtils.isEmpty(wxOpenIdPage.getResult())) {
            return ResultFul.getErrorMessage("微信号已经存在");
        }
        // TODO 修改
        customInsertModel.setImage("222");
        customInsertModel.setCreateTime(new Date());
        customInsertModel.setDeleteFlag(StatusEnum.DELETE_NO.getCode());
        // 新增客户客服信息
        int insertCount = customMapper.insertCustomInfo(customInsertModel);
        if (insertCount != 1) {
            throw new RuntimeException("新增客户客服信息失败");
        }
        return ResultFul.getSuccessTotal(insertCount);
    }


}
