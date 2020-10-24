package com.yushang.wallpaper.layer.service;

import java.util.Date;

public interface AsyncService {

    void saveShopOneWeek(Long id, Date sevenDayTime, Date nowTime);
}
