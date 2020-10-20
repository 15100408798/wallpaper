package com.yushang.wallpaper.layer.service;

import java.util.Date;

public interface AsyncService {
    void updateCustomeService(Byte customServiceId);

    void saveShopOneWeek(Long id, Date sevenDayTime, Date nowTime);
}
