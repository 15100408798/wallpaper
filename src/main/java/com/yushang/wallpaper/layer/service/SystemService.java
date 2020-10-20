package com.yushang.wallpaper.layer.service;

import java.util.Map;

public interface SystemService {


    Map<String, Object> selectRBList(int page, int size);

    Map<String, Object> deleteRB(String ids);
}
