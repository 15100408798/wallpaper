package com.yushang.wallpaper.layer.service.store;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.store.ProductSkuMapper;
import com.yushang.wallpaper.common.pojo.store.TbProductSku;
import com.yushang.wallpaper.model.store.ProductSkuQueryModel;
import com.yushang.wallpaper.model.store.ProductSkuUpdateModel;
import com.yushang.wallpaper.layer.router.store.ProductSkuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 商品Sku模块
 */
@Service
public class ProductSkuServiceImpl implements ProductSkuService {

    @Resource
    private ProductSkuMapper productSkuMapper;

    /**
     * 查询商品sku集合
     *
     * @param productSkuQueryModel 查询参数
     * @return 商品sku信息列表
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectSkuList(ProductSkuQueryModel productSkuQueryModel) {
        /* 校验参数 */
        Objects.requireNonNull(productSkuQueryModel);
        productSkuQueryModel.validPageSizeIsNull();
        PageHelper.startPage(productSkuQueryModel.getPage(), productSkuQueryModel.getSize());   // 分页
        // 查询商品sku集合
        Page<TbProductSku> tbProductSkuPage = productSkuMapper.selectSkuList(productSkuQueryModel);
        return ResultFul.getSuccessList(tbProductSkuPage.getResult(), tbProductSkuPage.getTotal());
    }

    /**
     * 更新商品sku信息
     *
     * @param productSkuUpdateModel 商品sku
     * @return 受影响条数
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Override
    public ResultFul updateProductSkuInfo(ProductSkuUpdateModel productSkuUpdateModel) {
        /* 校验参数 */
        Objects.requireNonNull(productSkuUpdateModel);
        productSkuUpdateModel.validProuductIdsNonNull();
        String productSkuIds = productSkuUpdateModel.getProductSkuIds();
        String[] productSkuIdValues = productSkuIds.split(",");
        productSkuUpdateModel.setProductSkuIdValues(productSkuIdValues);
        // 更新商品sku信息
        int updateCount = productSkuMapper.updateProductSkuInfo(productSkuUpdateModel);
        if (updateCount != 1) {
            return ResultFul.getErrorMessage("更新失败");
        }
        return ResultFul.getSuccessTotal(updateCount);
    }
}
