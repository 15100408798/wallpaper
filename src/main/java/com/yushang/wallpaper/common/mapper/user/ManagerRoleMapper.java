package com.yushang.wallpaper.common.mapper.user;

import com.yushang.wallpaper.common.pojo.shiro.TbManagerRole;
import com.yushang.wallpaper.layer.model.user.ManagerRoleUpdateModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理员角色关系表
 */
@Mapper
public interface ManagerRoleMapper {

    /**
     * 添加管理员角色表信息
     *
     * @param tbManagerRole 管理员角色表信息
     * @return 受影响条数
     */
    int insertManagerRoleInfo(TbManagerRole tbManagerRole);

    /**
     * 更新管理员角色表信息
     *
     * @param managerRoleUpdateModel 管理员角色表信息
     * @return 受影响条数
     */
    int updateManagerRoleInfo(ManagerRoleUpdateModel managerRoleUpdateModel);
}
