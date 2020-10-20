package com.yushang.wallpaper.common.mapper;

import java.util.HashMap;
import java.util.List;

public interface ActiveUserMapper {
    List<HashMap<String, Object>> selectActiveUserList();

    Integer selectCount();

}
