package com.yushang.wallpaper.common.mapper;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.user.TbCustom;
import com.yushang.wallpaper.model.user.CustomQueryModel;
import com.yushang.wallpaper.model.user.CustomUpdateModel;
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
}
