package com.yushang.wallpaper.common.mapper.user;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.shiro.TbRole;
import com.yushang.wallpaper.model.user.RoleInsertModel;
import com.yushang.wallpaper.model.user.RoleQueryModel;
import com.yushang.wallpaper.model.user.RoleUpdateModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色模块
 */
@Mapper
public interface RoleMapper {

    /**
     * 查询角色信息列表
     *
     * @param roleQueryModel 查询参数
     * @return 角色信息列表
     */
    Page<TbRole> selectList(RoleQueryModel roleQueryModel);

    /**
     * 更新角色信息
     *
     * @param roleUpdateModel 角色信息
     * @return 受影响条数
     */
    int updateRoleInfo(RoleUpdateModel roleUpdateModel);

    /**
     * 新增角色信息
     *
     * @param roleInsertModel 角色信息
     * @return 受影响条数
     */
    int insertRole(RoleInsertModel roleInsertModel);
}
