package com.yushang.wallpaper.layer.service.impl.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.config.enums.ValidEnum;
import com.yushang.wallpaper.common.config.exception.ValidException;
import com.yushang.wallpaper.common.mapper.user.ManagerMapper;
import com.yushang.wallpaper.common.pojo.user.TbManager;
import com.yushang.wallpaper.common.utils.MD5Utils;
import com.yushang.wallpaper.layer.model.user.ManagerInsertModel;
import com.yushang.wallpaper.layer.model.user.ManagerQueryModel;
import com.yushang.wallpaper.layer.model.user.ManagerUpdateModel;
import com.yushang.wallpaper.layer.service.user.ManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Resource
    private ManagerMapper managerMapper;

    /**
     * 查询管理员集合
     *
     * @param managerQueryModel 查询参数
     * @return 管理员信息列表
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectList(ManagerQueryModel managerQueryModel) {
        /* 校验参数 */
        Objects.requireNonNull(managerQueryModel);
        managerQueryModel.validPageSizeIsNull();
        /* 查询管理员集合 */
        PageHelper.startPage(managerQueryModel.getPage(), managerQueryModel.getSize());
        Page<TbManager> tbManagerPage = managerMapper.selectList(managerQueryModel);
        return ResultFul.getSuccessList(tbManagerPage.getResult(), tbManagerPage.getTotal());
    }

    /**
     * 更新管理员信息
     *
     * @param managerUpdateModel 更新参数
     * @return 受影响条数
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ResultFul updateTbManager(ManagerUpdateModel managerUpdateModel) {
        /* 校验参数 */
        Objects.requireNonNull(managerUpdateModel);
        managerUpdateModel.validManageIdsIsNotNull();
        String managerIds = managerUpdateModel.getManagerIds();
        String[] managerIdValues = managerIds.split(",");
        managerUpdateModel.setManagerIdValues(managerIdValues);
        // 更新管理员信息
        int updateCount = managerMapper.updateTbManager(managerUpdateModel);
        return ResultFul.getSuccessTotal(updateCount);
    }


    /**
     * 新增管理员信息
     *
     * @param managerInsertModel 管理员信息
     * @return 受影响条数
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ResultFul insertManager(ManagerInsertModel managerInsertModel) {
        /* 校验参数 */
        Objects.requireNonNull(managerInsertModel);
        managerInsertModel.validInsertInfoIsNotNull();
        // 用户名不能重复
        Page<TbManager> tbManagerPage = managerMapper.selectList(new ManagerQueryModel(managerInsertModel.getUsername()));
        List<TbManager> tbManagerList = tbManagerPage.getResult();
        if (!CollectionUtils.isEmpty(tbManagerList)) {
            return ResultFul.getErrorMessage("用户名不能重复");
        }
        managerInsertModel.setSalt(managerInsertModel.getUsername());
        managerInsertModel.setPassword(MD5Utils.disrect(managerInsertModel.getPassword(), managerInsertModel.getUsername()));
        managerInsertModel.setCreateTime(new Date());
        managerInsertModel.setDeleteFlag((byte) 0);
        int insertCount = managerMapper.insertManagerInfo(managerInsertModel);
        return ResultFul.getSuccessTotal(insertCount);
    }

    /**
     * 查询管理员信息
     *
     * @param managerQueryModel 查询参数
     * @return 管理员信息
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectInfo(ManagerQueryModel managerQueryModel) throws ValidException {
        /* 校验参数 */
        Objects.requireNonNull(managerQueryModel);
        managerQueryModel.setPage(1);
        managerQueryModel.setSize(1);
        Objects.requireNonNull(managerQueryModel.getManagerId(), "管理员ID不能为Null");
        // 查询管理员信息
        Page<TbManager> tbManagerPage = managerMapper.selectList(managerQueryModel);
        List<TbManager> tbManagerList = tbManagerPage.getResult();
        if (CollectionUtils.isEmpty(tbManagerList)) {
            throw new ValidException(ValidEnum.NO_FOUND_INFO);
        } else if (tbManagerList.size() != 1) {
            throw new ValidException(ValidEnum.SELECT_INFO_ERROR);
        }
        return ResultFul.getSuccessList(tbManagerList, tbManagerPage.getTotal());
    }

}
