package com.yushang.wallpaper.common.mapper.user;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.user.TbManager;
import com.yushang.wallpaper.layer.model.user.ManagerInsertModel;
import com.yushang.wallpaper.layer.model.user.ManagerQueryModel;
import com.yushang.wallpaper.layer.model.user.ManagerUpdateModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagerMapper {

    /**
     * 查询管理员集合
     *
     * @param managerQueryModel 查询参数
     * @return 管理员信息列表
     */
    Page<TbManager> selectList(ManagerQueryModel managerQueryModel);

    /**
     * 更新管理员信息
     *
     * @param managerUpdateModel 更新参数
     * @return 受影响条数
     */
    int updateTbManager(ManagerUpdateModel managerUpdateModel);

    /**
     * 新增管理员信息
     *
     * @param managerInsertModel 管理员信息
     * @return 受影响条数
     */
    int insertManagerInfo(ManagerInsertModel managerInsertModel);

}
