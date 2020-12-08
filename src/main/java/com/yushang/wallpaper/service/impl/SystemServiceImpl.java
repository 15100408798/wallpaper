package com.yushang.wallpaper.service.impl;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.RebateMapper;
import com.yushang.wallpaper.service.SystemService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemServiceImpl implements SystemService {





    @Autowired
    private RebateMapper rebateMapper;





    @Override
    public Map<String, Object> selectRBList(int page, int size) {
        PageHelper.startPage(page,size);
        List<HashMap<String, Object>> list = rebateMapper.selectFKList();
        int count = rebateMapper.selectCount();
        return ResultFul.getListMap(list,count);
    }

    @Transactional
    @Override
    public Map<String, Object> deleteRB(String ids) {
        rebateMapper.deleteRB(ids.split(","));
        return ResultFul.getStatusMap();
    }
}
