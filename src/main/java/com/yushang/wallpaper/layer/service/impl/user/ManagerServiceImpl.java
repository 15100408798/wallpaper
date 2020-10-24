package com.yushang.wallpaper.layer.service.impl.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.config.enums.ValidEnum;
import com.yushang.wallpaper.common.config.exception.ValidException;
import com.yushang.wallpaper.common.mapper.user.ManagerMapper;
import com.yushang.wallpaper.common.mapper.user.ManagerRoleMapper;
import com.yushang.wallpaper.common.pojo.shiro.TbManagerRole;
import com.yushang.wallpaper.common.pojo.user.TbManager;
import com.yushang.wallpaper.common.utils.MD5Utils;
import com.yushang.wallpaper.layer.model.user.ManagerInsertModel;
import com.yushang.wallpaper.layer.model.user.ManagerQueryModel;
import com.yushang.wallpaper.layer.model.user.ManagerRoleUpdateModel;
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
    @Resource
    private ManagerRoleMapper managerRoleMapper;

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
        if (updateCount != 1) {
            throw new RuntimeException("更新管理员失败");
        }
        /** 删除管理员角色关系表信息 */
        ManagerRoleUpdateModel managerRoleUpdateModel = new ManagerRoleUpdateModel();
        if (managerUpdateModel.getDeleteFlag() != null || managerUpdateModel.getUserRole() != null) {
            managerRoleUpdateModel.setDeleteFlag(managerUpdateModel.getDeleteFlag());
            managerRoleUpdateModel.setManagerIdValues(managerIdValues);
            managerRoleUpdateModel.setRoleId(managerUpdateModel.getUserRole());
            int updateManagerRoleCount = managerRoleMapper.updateManagerRoleInfo(managerRoleUpdateModel);
            if (updateManagerRoleCount != managerIdValues.length) {
                throw new RuntimeException("更新管理员角色关系表失败");
            }
        }
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
        /** 用户名不能重复 */
        Page<TbManager> usernameManagerPage = managerMapper.selectList(new ManagerQueryModel(managerInsertModel.getUsername()));
        if (!CollectionUtils.isEmpty(usernameManagerPage.getResult())) {
            return ResultFul.getErrorMessage("用户名不能重复");
        }
        /** 校验手机号，手机号不能为重复 */
        ManagerQueryModel phoneQueryModel = new ManagerQueryModel();
        phoneQueryModel.setTelphone(managerInsertModel.getTelphone());
        Page<TbManager> phoneManagerPage = managerMapper.selectList(phoneQueryModel);
        if (!CollectionUtils.isEmpty(phoneManagerPage.getResult())) {
            return ResultFul.getErrorMessage("手机号已经存在");
        }
        /** 新增管理员信息 */
        managerInsertModel.setSalt(managerInsertModel.getUsername());
        managerInsertModel.setPassword(MD5Utils.disrect(managerInsertModel.getPassword(), managerInsertModel.getUsername()));
        managerInsertModel.setCreateTime(new Date());
        managerInsertModel.setDeleteFlag((byte) 0);
        int insertCount = managerMapper.insertManagerInfo(managerInsertModel);
        if (insertCount != 1) {
            throw new RuntimeException("新增管理员失败");
        }
        /** 添加角色表信息 */
        TbManagerRole tbManagerRole = new TbManagerRole();
        tbManagerRole.setManagerId(managerInsertModel.getManagerId());
        tbManagerRole.setRoleId(managerInsertModel.getUserRole());
        tbManagerRole.setCreateTime(new Date());
        tbManagerRole.setDeleteFlag((byte) 0);
        int insertRoleCount = managerRoleMapper.insertManagerRoleInfo(tbManagerRole);
        if (insertRoleCount != 1) {
            throw new RuntimeException("新增管理员角色信息失败");
        }
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
