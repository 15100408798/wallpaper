package com.yushang.wallpaper.layer.service.store;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.store.ProductLabelMapper;
import com.yushang.wallpaper.common.pojo.store.TbProductLabel;
import com.yushang.wallpaper.layer.model.store.productLabel.LabelQueryModel;
import com.yushang.wallpaper.layer.model.store.productLabel.LabelUpdateModel;
import com.yushang.wallpaper.layer.router.store.ProductLabelService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 商品类别管理
 */
@Service
public class ProductLabelServiceImpl implements ProductLabelService {

    @Resource
    private ProductLabelMapper productLabelMapper;

    /**
     * 查询商品类别信息列表
     *
     * @param queryModel 筛选参数
     * @return 商品类别信息列表
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectLabelList(LabelQueryModel queryModel) {
        /* 校验参数 */
        Objects.requireNonNull(queryModel);
        queryModel.startPage();
        // 查询商品类别信息列表
        Page<TbProductLabel> tbProductLabelPage = productLabelMapper.selectLabelList(queryModel);
        return ResultFul.getSuccessList(tbProductLabelPage.getResult(), tbProductLabelPage.getTotal());
    }

    /**
     * 更新商品类别信息
     *
     * @param labelUpdateModel 商品类别信息
     * @return 受影响条数
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Override
    public ResultFul updateProductLabel(LabelUpdateModel labelUpdateModel) {
        /* 校验参数 */
        Objects.requireNonNull(labelUpdateModel);
        labelUpdateModel.validProductLabelIdIsNotNull();
        // 商品类别ID
        String productLabelIds = labelUpdateModel.getProductLabelIds();
        String[] productLabelIdValues = productLabelIds.split(",");
        if (ArrayUtils.isEmpty(productLabelIdValues)) {
            throw new NullPointerException("商品类别ID为null");
        }
        labelUpdateModel.setProductLabelIdValues(productLabelIdValues);
        /** 查询商品名称是否存在，存在则不添加 */
        if (StringUtils.isNotBlank(labelUpdateModel.getProductLabelName())) {
            LabelQueryModel labelQueryModel = new LabelQueryModel();
            labelQueryModel.setNotProductLabelId(Integer.parseInt(labelUpdateModel.getProductLabelIds()));
            labelQueryModel.setProductLabelName(labelUpdateModel.getProductLabelName());
            Page<TbProductLabel> tbProductLabelPage = productLabelMapper.selectLabelList(labelQueryModel);
            if (!CollectionUtils.isEmpty(tbProductLabelPage.getResult())) {
                return ResultFul.getErrorMessage("该商品类别名称已经存在");
            }
        }
        // 更新商品类别信息
        int updateCount = productLabelMapper.updateProductLabel(labelUpdateModel);
        if (updateCount != productLabelIdValues.length) {
            return ResultFul.getErrorMessage("更新失败");
        }
        return ResultFul.getSuccessTotal(updateCount);
    }

    /**
     * 新增商品类别
     *
     * @param labelUpdateModel 商品类别信息
     * @return 受影响条数
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Override
    public ResultFul insertProductLabel(LabelUpdateModel labelUpdateModel) {
        Objects.requireNonNull(labelUpdateModel);
        if (StringUtils.isBlank(labelUpdateModel.getProductLabelName())) {
            throw new NullPointerException("商品类别名称不能为空");
        }
        /** 查询商品名称是否存在，存在则不添加 */
        LabelQueryModel labelQueryModel = new LabelQueryModel();
        labelQueryModel.setProductLabelName(labelUpdateModel.getProductLabelName());
        Page<TbProductLabel> tbProductLabelPage = productLabelMapper.selectLabelList(labelQueryModel);
        if (!CollectionUtils.isEmpty(tbProductLabelPage.getResult())) {
            return ResultFul.getErrorMessage("该商品类别名称已经存在");
        }
        /** 新增商品类别 */
        int insertCount = productLabelMapper.insertProductLabel(labelUpdateModel);
        if (insertCount != 1) {
            return ResultFul.getErrorMessage("新增信息失败");
        }
        return ResultFul.getSuccessTotal(insertCount);
    }


}
