package com.yushang.wallpaper.service.impl.store;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.store.ProductLabelMapper;
import com.yushang.wallpaper.common.pojo.store.TbProductLabel;
import com.yushang.wallpaper.model.store.LabelQueryModel;
import com.yushang.wallpaper.model.store.LabelUpdateModel;
import com.yushang.wallpaper.service.store.ProductLabelService;
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
     * @param reqModel
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectLabelList(LabelQueryModel reqModel) {
        /* 校验参数 */
        Objects.requireNonNull(reqModel);
        reqModel.validPageSizeIsNull();
        // 分页
        PageHelper.startPage(reqModel.getPage(), reqModel.getSize());
        // 查询商品类别信息列表
        Page<TbProductLabel> tbProductLabelPage = productLabelMapper.selectLabelList(reqModel);
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
        // 查询商品名称是否存在，存在则不添加
        LabelQueryModel labelQueryModel = new LabelQueryModel(1, 1);
        labelQueryModel.setProductLabelName(labelUpdateModel.getProductLabelName());
        Page<TbProductLabel> tbProductLabelPage = productLabelMapper.selectLabelList(labelQueryModel);
        if (!CollectionUtils.isEmpty(tbProductLabelPage.getResult())) {
            return ResultFul.getErrorMessage("该商品类别名称已经存在");
        }
        // 新增商品类别
        int insertCount = productLabelMapper.insertProductLabel(labelUpdateModel);
        if (insertCount != 1) {
            return ResultFul.getErrorMessage("新增信息失败");
        }
        return ResultFul.getSuccessTotal(insertCount);
    }


}
