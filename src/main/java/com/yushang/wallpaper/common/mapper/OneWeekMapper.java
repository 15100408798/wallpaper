package com.yushang.wallpaper.common.mapper;

import java.util.HashMap;
import java.util.List;

public interface OneWeekMapper {

    List<HashMap<String, Object>> selectOneWeekList();

    int selectOneWeekCount();

}
