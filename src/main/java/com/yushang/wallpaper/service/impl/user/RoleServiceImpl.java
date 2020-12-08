package com.yushang.wallpaper.service.impl.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.user.RoleMapper;
import com.yushang.wallpaper.common.pojo.shiro.TbRole;
import com.yushang.wallpaper.model.user.RoleInsertModel;
import com.yushang.wallpaper.model.user.RoleQueryModel;
import com.yushang.wallpaper.model.user.RoleUpdateModel;
import com.yushang.wallpaper.service.user.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * 角色模块
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 查询角色信息列表
     *
     * @param roleQueryModel 查询参数
     * @return 角色信息列表
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectList(RoleQueryModel roleQueryModel) {
        /* 校验参数 */
        Objects.requireNonNull(roleQueryModel);
        roleQueryModel.validPageSizeIsNull();
        // 分页
        PageHelper.startPage(roleQueryModel.getPage(), roleQueryModel.getSize());
        // 查询角色信息列表
        Page<TbRole> tbRolePage = roleMapper.selectList(roleQueryModel);
        return ResultFul.getSuccessList(tbRolePage.getResult(), tbRolePage.getTotal());
    }


    /**
     * 更新角色信息
     *
     * @param roleUpdateModel 角色信息
     * @return 受影响条数
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ResultFul updateRoleInfo(RoleUpdateModel roleUpdateModel) {
        /* 校验参数 */
        Objects.requireNonNull(roleUpdateModel);
        roleUpdateModel.validRoleIdNonNull();
        String roleIds = roleUpdateModel.getRoleIds();
        String[] roleIdValues = roleIds.split(",");     // 用户ID集合
        roleUpdateModel.setRoleIdValues(roleIdValues);
        // 更新角色信息
        int updateCount = roleMapper.updateRoleInfo(roleUpdateModel);
        if (updateCount != roleIdValues.length){
            throw new RuntimeException("更新角色信息失败");
        }
        return ResultFul.getSuccessTotal(updateCount);
    }

    /**
     * 添加角色信息
     *
     * @param roleInsertModel 角色信息
     * @return 受影响条数
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ResultFul insertRole(RoleInsertModel roleInsertModel) {
        /** 校验参数 */
        Objects.requireNonNull(roleInsertModel);
        roleInsertModel.validRoleName();
        /** 校验角色名称不能重复 */
        RoleQueryModel roleQueryModel = new RoleQueryModel(1, 1);
        roleQueryModel.setRoleName(roleInsertModel.getRoleName());
        Page<TbRole> tbRolePage = roleMapper.selectList(roleQueryModel);
        if (!CollectionUtils.isEmpty(tbRolePage.getResult())) {
            return ResultFul.getErrorMessage("角色名称已经存在");
        }
        /** 添加角色信息 */
        roleInsertModel.setCreateTime(new Date());
        roleInsertModel.setDeleteFlag(0);
        // 添加角色信息
        int insertCount = roleMapper.insertRole(roleInsertModel);
        if (insertCount != 1){
            throw new RuntimeException("添加角色信息失败");
        }
        return ResultFul.getSuccessTotal(insertCount);
    }
}
