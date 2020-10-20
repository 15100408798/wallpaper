package com.yushang.wallpaper.layer.service;

import java.util.Map;

public interface MoneyService {
    Map<String, Object> selectActiveUserList(int page, int size);

    Map<String, Object> selectAllAUList(int page, int size);

    Map<String, Object> selectStoreMoneyList(int page, int size);

    Map<String, Object> selectOneWeekList(int page, int size);
}
