package com.yushang.wallpaper.common.mapper;

import java.util.HashMap;
import java.util.List;

public interface ActiveMapper {
    int selectCount();

    List<HashMap<String, Object>> selectAllAUList();

}
