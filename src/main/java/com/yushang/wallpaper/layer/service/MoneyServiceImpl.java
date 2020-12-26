package com.yushang.wallpaper.layer.service;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.ActiveMapper;
import com.yushang.wallpaper.common.mapper.ActiveUserMapper;
import com.yushang.wallpaper.common.mapper.OneWeekMapper;
import com.yushang.wallpaper.common.mapper.order.OrderMapper;
import com.yushang.wallpaper.service.MoneyService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MoneyServiceImpl implements MoneyService {

    @Autowired
    private ActiveUserMapper activeUserMapper;

    @Autowired
    private ActiveMapper activeMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OneWeekMapper oneWeekMapper;

    @Override
    public Map<String, Object> selectActiveUserList(int page, int size) {
        PageHelper.startPage(page,size);
        List<HashMap<String,Object>> list = activeUserMapper.selectActiveUserList();
        Integer count = activeUserMapper.selectCount();
        return ResultFul.getListMap(list,count);
    }

    @Override
    public Map<String, Object> selectAllAUList(int page, int size) {
        PageHelper.startPage(page,size);
        List<HashMap<String,Object>> list = activeMapper.selectAllAUList();
        int count = activeMapper.selectCount();
        return ResultFul.getListMap(list,count);
    }

    @Override
    public Map<String, Object> selectStoreMoneyList(int page, int size) {
        PageHelper.startPage(page,size);
        List<HashMap<String,Object>> list = orderMapper.selectStoreMoneyList();
        int count = orderMapper.selectCount();
        return ResultFul.getListMap(list,count);
    }

    @Override
    public Map<String, Object> selectOneWeekList(int page, int size) {
        PageHelper.startPage(page,size);
        List<HashMap<String,Object>> list = oneWeekMapper.selectOneWeekList();
        int count = oneWeekMapper.selectOneWeekCount();
        return ResultFul.getListMap(list,count);
    }
}
