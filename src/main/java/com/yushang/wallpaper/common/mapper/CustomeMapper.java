package com.yushang.wallpaper.common.mapper;

import com.yushang.wallpaper.common.pojo.TbCustomService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomeMapper {
    List<TbCustomService> selectCSList();

    int selectCount();

    Map<String, Object> deleteSC(String[] split);

    Map<String, Object> openSC(String[] split);

    Map<String, Object> disabledSC(String[] split);

    int selectIsExistIsTop();

    Byte getIsTopCustomId();

    void updateCustomeService(@Param(value = "customServiceId") Byte customServiceId);
}
