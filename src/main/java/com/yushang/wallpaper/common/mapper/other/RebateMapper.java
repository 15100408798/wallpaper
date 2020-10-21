package com.yushang.wallpaper.common.mapper.other;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.other.TbRebate;
import com.yushang.wallpaper.layer.model.other.RebateInsertModel;
import com.yushang.wallpaper.layer.model.other.RebateQueryModel;
import com.yushang.wallpaper.layer.model.other.RebateUpdateModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员折扣管理
 */
@Mapper
public interface RebateMapper {


    /**
     * 更新会员折扣信息
     *
     * @param rebateUpdateModel 会员折扣信息
     * @return 受影响条数
     */
    int updateRebateInfo(RebateUpdateModel rebateUpdateModel);

    /**
     * 查询会员折扣列表
     *
     * @param rebateQueryModel 查询参数
     * @return 会员折扣列表
     */
    Page<TbRebate> selectList(RebateQueryModel rebateQueryModel);


    /**
     * 新增会员折扣信息
     *
     * @param rebateInsertModel 会员折扣信息
     * @return 受影响条数
     */
    int insertRebateInfo(RebateInsertModel rebateInsertModel);
}
