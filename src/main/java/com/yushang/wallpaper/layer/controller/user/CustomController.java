package com.yushang.wallpaper.layer.controller.user;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.enums.StatusEnum;
import com.yushang.wallpaper.common.pojo.user.TbCustom;
import com.yushang.wallpaper.layer.model.enums.LogEnum;
import com.yushang.wallpaper.layer.model.user.custom.CustomInsertModel;
import com.yushang.wallpaper.layer.model.user.custom.CustomQueryModel;
import com.yushang.wallpaper.layer.model.user.custom.CustomUpdateModel;
import com.yushang.wallpaper.layer.router.user.CustomService;
import io.swagger.annotations.Api;
import org.apache.http.HttpStatus;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "客服模块")
@RestController
@RequestMapping(value = "layer/custom")
public class CustomController {

    @Autowired
    private CustomService customService;

    @PermissionName(value = "查询客户客服列表")
    @RequiresPermissions(value = {"custom:selectList"})
    @RequiresRoles(value = {"1"})
    @Log(title = "查询客户客服列表", tabName = "tb_custom", operateType = 1, logEnum = LogEnum.CUSTOM_LIST)
    @RequestMapping("selectList")
    public ResultFul selectList(CustomQueryModel customQueryModel) {
        return customService.selectList(customQueryModel);
    }

    @PermissionName(value = "新增客户客服")
    @RequiresPermissions(value = {"custom:insertCustom"})
    @RequiresRoles(value = {"1"})
    @Log(title = "新增客户客服", tabName = "tb_custom", operateType = 11, logEnum = LogEnum.ADD_CUSTOM)
    @RequestMapping("insertCustom")
    public ResultFul insertCustom(CustomInsertModel customInsertModel) {
        return customService.insertCustom(customInsertModel);
    }

    @PermissionName(value = "修改客户客服")
    @RequiresPermissions(value = {"custom:updateCustom"})
    @RequiresRoles(value = {"1"})
    @Log(title = "修改客户客服", tabName = "tb_custom", operateType = 13, logEnum = LogEnum.UPDATE_CUSTOM)
    @RequestMapping("updateCustom")
    public ResultFul updateCustom(CustomUpdateModel customUpdateModel) {
        return customService.updateCustomInfo(customUpdateModel);
    }

    @PermissionName(value = "删除客户客服")
    @RequiresPermissions(value = {"custom:deleteCustom"})
    @RequiresRoles(value = {"1"})
    @Log(title = "删除客户客服", tabName = "tb_custom", operateType = 2, logEnum = LogEnum.DEL_CUSTOM)
    @RequestMapping("deleteCustom")
    public ResultFul deleteCustom(CustomUpdateModel customUpdateModel) {
        customUpdateModel.setDeleteFlag(StatusEnum.DELETE_YES.getCode());
        return customService.updateCustomInfo(customUpdateModel);
    }

    @PermissionName(value = "检查微信号是否可用")
    @RequiresPermissions(value = {"custom:checkWxOpenId"})
    @RequiresRoles(value = {"1"})
    @Log(title = "检查微信号是否可用", tabName = "tb_custom", operateType = 1, logEnum = LogEnum.CUSTOM_LIST)
    @RequestMapping(value = {"checkWxOpenId"})
    public ResultFul checkWxOpenId(CustomQueryModel customQueryModel) {
        customQueryModel.setDeleteFlag(StatusEnum.DELETE_NO.getCode());
        ResultFul resultFul = customService.selectList(customQueryModel);
        if (resultFul.getStatus() != 200) {
            return resultFul;
        }
        List<TbCustom> tbCustomList = (List<TbCustom>) resultFul.getRows();
        if (CollectionUtils.isEmpty(tbCustomList)) {
            resultFul.setMessage("微信号可用");
        } else {
            resultFul.setMessage( "微信号已存在");
            resultFul.setStatus(HttpStatus.SC_BAD_REQUEST);
        }
        return resultFul;
    }

}
