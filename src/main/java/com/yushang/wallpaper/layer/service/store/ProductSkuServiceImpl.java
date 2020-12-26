package com.yushang.wallpaper.layer.service.store;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.enums.StatusEnum;
import com.yushang.wallpaper.common.mapper.store.ProductSkuMapper;
import com.yushang.wallpaper.common.pojo.store.TbProductSku;
import com.yushang.wallpaper.layer.model.store.productSku.ProductSkuInsertModel;
import com.yushang.wallpaper.layer.model.store.productSku.ProductSkuQueryModel;
import com.yushang.wallpaper.layer.model.store.productSku.ProductSkuUpdateModel;
import com.yushang.wallpaper.layer.router.store.ProductSkuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Objects;

/**
 * 商品Sku模块
 */
@Service
public class ProductSkuServiceImpl implements ProductSkuService {

    @Autowired
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
        productSkuQueryModel.startPage();
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
        /** 校验是否存在相同sku */
        if (Objects.nonNull(productSkuUpdateModel.getProductLabelId()) && StringUtils.isNotBlank(productSkuUpdateModel.getProductSkuName())
                    && StringUtils.isNotBlank(productSkuUpdateModel.getProductSkuSize())) {
            ProductSkuQueryModel productSkuQueryModel = new ProductSkuQueryModel();
            productSkuQueryModel.setNotProductSkuId(Integer.parseInt(productSkuUpdateModel.getProductSkuIds()));
            productSkuQueryModel.setProductLabelId(productSkuUpdateModel.getProductLabelId());
            productSkuQueryModel.setProductSkuName(productSkuUpdateModel.getProductSkuName());
            productSkuQueryModel.setProductSkuSize(productSkuUpdateModel.getProductSkuSize());
            Page<TbProductSku> tbProductSkuPage = productSkuMapper.selectSkuList(productSkuQueryModel);
            if (!CollectionUtils.isEmpty(tbProductSkuPage)) {
                return ResultFul.getErrorMessage("存在相同sku，添加失败");
            }
        }
        /** 更新商品sku信息 */
        int updateCount = productSkuMapper.updateProductSkuInfo(productSkuUpdateModel);
        if (updateCount != productSkuIdValues.length) {
            return ResultFul.getErrorMessage("更新失败");
        }
        return ResultFul.getSuccessTotal(updateCount);
    }


    /**
     * 新增商品sku信息
     *
     * @param productSkuInsertModel 商品sku
     * @return 受影响条数
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Override
    public ResultFul insertSkuProduct(ProductSkuInsertModel productSkuInsertModel) {
        /* 校验参数 */
        Objects.requireNonNull(productSkuInsertModel);
        /** 校验是否存在相同sku */
        ProductSkuQueryModel productSkuQueryModel = new ProductSkuQueryModel();
        productSkuQueryModel.setProductLabelId(productSkuInsertModel.getProductLabelId());
        productSkuQueryModel.setProductSkuName(productSkuInsertModel.getProductSkuName());
        productSkuQueryModel.setProductSkuSize(productSkuInsertModel.getProductSkuSize());
        Page<TbProductSku> tbProductSkuPage = productSkuMapper.selectSkuList(productSkuQueryModel);
        if (!CollectionUtils.isEmpty(tbProductSkuPage)) {
            return ResultFul.getErrorMessage("存在相同sku，添加失败");
        }
        /** 新增商品sku信息 */
        productSkuInsertModel.setProductSkuStatus(StatusEnum.UP.getCode());
        productSkuInsertModel.setCreateTime(new Date());
        productSkuInsertModel.setDeleteFlag(StatusEnum.DELETE_NO.getCode());
        int insertCount = productSkuMapper.insertSkuProduct(productSkuInsertModel);
        if (insertCount != 1) {
            return ResultFul.getErrorMessage("新增失败");
        }
        return ResultFul.getSuccessTotal(insertCount);
    }


}
