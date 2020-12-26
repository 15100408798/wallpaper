package com.yushang.wallpaper.layer.service.store;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.store.ProductMapper;
import com.yushang.wallpaper.common.pojo.store.TbProduct;
import com.yushang.wallpaper.layer.model.store.product.ProductInsertModel;
import com.yushang.wallpaper.layer.model.store.product.ProductQueryModel;
import com.yushang.wallpaper.layer.model.store.product.ProductUpdateModel;
import com.yushang.wallpaper.layer.router.store.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    /**
     * 查询商品信息列表
     *
     * @param reqModel 请求参数
     * @return 商品信息列表
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectList(ProductQueryModel reqModel) {
        /* 校验参数 */
        Objects.requireNonNull(reqModel);
        reqModel.startPage();
        // 查询商品信息列表
        Page<TbProduct> tbProductPage = productMapper.selectList(reqModel);
        return ResultFul.getSuccessList(tbProductPage.getResult(), tbProductPage.getTotal());
    }

    /**
     * 更新商品信息
     *
     * @param productUpdateModel 商品信息
     * @return 手影响条数
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ResultFul updateProductInfo(ProductUpdateModel productUpdateModel) {
        /* 校验参数 */
        Objects.requireNonNull(productUpdateModel);
        productUpdateModel.validProductIdsIsNotNull();
        String productIds = productUpdateModel.getProductIds();
        String[] productIdValues = productIds.split(",");
        productUpdateModel.setProductIdValues(productIdValues);
        // 更新商品信息
        int updateCount = productMapper.updateProductInfo(productUpdateModel);
        if (updateCount != productIdValues.length) {
            throw new RuntimeException("更新商品信息失败");
        }
        return ResultFul.getSuccessTotal(updateCount);
    }


    /**
     * 新增商品信息
     *
     * @param productInsertModel 商品信息
     * @return 受影响条数
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ResultFul insertProductInfo(ProductInsertModel productInsertModel) {
        // 更新商品信息
        int insertCount = productMapper.insertProductInfo(productInsertModel);
        if (insertCount != 1) {
            throw new RuntimeException("新增商品信息失败");
        }
        return ResultFul.getSuccessTotal(insertCount);
    }


}
