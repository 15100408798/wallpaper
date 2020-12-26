package com.yushang.wallpaper.common.mapper.user;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.user.TbCustom;
import com.yushang.wallpaper.layer.model.user.custom.CustomInsertModel;
import com.yushang.wallpaper.layer.model.user.custom.CustomQueryModel;
import com.yushang.wallpaper.layer.model.user.custom.CustomUpdateModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomMapper {

    /**
     * 查询客户客服列表
     *
     * @param customQueryModel 查询参数
     * @return 客户客服列表
     */
    Page<TbCustom> selectList(CustomQueryModel customQueryModel);

    /**
     * 更新客户客服信息
     *
     * @param customUpdateModel 客户客服信息
     * @return 受影响条数
     */
    int updateCustomInfo(CustomUpdateModel customUpdateModel);

    /**
     * 新增客户客服信息
     *
     * @param customInsertModel 客户客服信息
     * @return 受影响条数
     */
    int insertCustomInfo(CustomInsertModel customInsertModel);
}
