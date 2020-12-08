package com.yushang.wallpaper.layer.service.store;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.OrderMapper;
import com.yushang.wallpaper.common.mapper.store.ShopMapper;
import com.yushang.wallpaper.common.pojo.store.TbShop;
import com.yushang.wallpaper.model.store.ShopQueryModel;
import com.yushang.wallpaper.model.store.ShopUpdateModel;
import com.yushang.wallpaper.service.AsyncService;
import com.yushang.wallpaper.layer.router.store.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 商户管理模块
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AsyncService asyncService;

    /**
     * 查询商户信息列表
     *
     * @param shopQueryModel 查询参数
     * @return 商户信息列表
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectStoreList(ShopQueryModel shopQueryModel) {
        /* 校验参数 */
        Objects.requireNonNull(shopQueryModel);
        shopQueryModel.validPageSizeIsNull();
        PageHelper.startPage(shopQueryModel.getPage(), shopQueryModel.getSize());   // 分页
        // 查询商户信息列表
        Page<TbShop> tbShopPage = shopMapper.selectStoreList(shopQueryModel);
        return ResultFul.getSuccessList(tbShopPage.getResult(), tbShopPage.getTotal());
    }

    /**
     * 更新商户信息列表
     *
     * @param shopUpdateModel 商户信息列表
     * @return 受影响条数
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ResultFul updateShopInfo(ShopUpdateModel shopUpdateModel) {
        /* 校验参数 */
        Objects.requireNonNull(shopUpdateModel);
        shopUpdateModel.validShopIdIsNotNull();
        String shopIds = shopUpdateModel.getShopIds();
        String[] shopIdValues = shopIds.split(",");
        shopUpdateModel.setShopIdValues(shopIdValues);
        // 更新商户信息列表
        int updateCount = shopMapper.updateShopInfo(shopUpdateModel);
        return ResultFul.getSuccessTotal(updateCount);
    }

    @Override
    public List<Long> selectShopIdList() {
        return shopMapper.selectShopIdList();
    }

    @Override
    public void saveShopOneWeek() {
        //获得数据库中的商铺Id
        List<Long> shopIdList = shopMapper.selectShopIdList();
        //获得现在时间
        Date nowTime = new Date();
        //获得七天前现在的时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        Date sevenDayTime = calendar.getTime();
        if (shopIdList != null && shopIdList.size() > 0) {
            for (int i = 0; i < shopIdList.size(); i++) {
                Long id = shopIdList.get(i);
                asyncService.saveShopOneWeek(id, sevenDayTime, nowTime);
            }
        }
    }


}
