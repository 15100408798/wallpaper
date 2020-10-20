package com.yushang.wallpaper.common.mapper;

import java.util.HashMap;
import java.util.List;

public interface RebateMapper {
    void deleteRB(String[] split);

    List<HashMap<String, Object>> selectFKList();

    int selectCount();

}
