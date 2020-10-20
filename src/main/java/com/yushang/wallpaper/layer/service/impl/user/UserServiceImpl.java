package com.yushang.wallpaper.layer.service.impl.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.CustomeMapper;
import com.yushang.wallpaper.common.mapper.user.UserMapper;
import com.yushang.wallpaper.common.pojo.TbCustomService;
import com.yushang.wallpaper.common.pojo.user.TbUser;
import com.yushang.wallpaper.layer.model.user.UserQueryModel;
import com.yushang.wallpaper.layer.model.user.UserUpdateModel;
import com.yushang.wallpaper.layer.service.AsyncService;
import com.yushang.wallpaper.layer.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CustomeMapper customeMapper;

    @Autowired
    private AsyncService asyncService;

    /**
     * 查询用户信息列表
     *
     * @param reqModel 查询参数
     * @return 用户信息列表
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectTbUserList(UserQueryModel reqModel) {
        /* 校验参数 */
        Objects.requireNonNull(reqModel);
        reqModel.validPageSizeIsNull();
        // 分页
        PageHelper.startPage(reqModel.getPage(), reqModel.getSize());
        // 查询用户集合
        Page<TbUser> tbUserPage = userMapper.selectTbUserList(reqModel);
        return ResultFul.getSuccessList(tbUserPage.getResult(), tbUserPage.getTotal());
    }

    /**
     * 更新用户信息
     *
     * @param userUpdateModel 用户信息
     * @return 受影响条数
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ResultFul updateUserInfo(UserUpdateModel userUpdateModel) {
        /* 校验参数 */
        Objects.requireNonNull(userUpdateModel);
        userUpdateModel.validUserIdsIsNotNull();
        String userIds = userUpdateModel.getUserIds();
        String[] userIdValues = userIds.split(",");     // 用户ID集合
        userUpdateModel.setUserIdValues(userIdValues);
        // 更新用户信息
        int updateCount = userMapper.updateUserIsUseStatus(userUpdateModel);
        return ResultFul.getSuccessTotal(updateCount);
    }

    /**
     * 存入数据库
     */
    @Transactional
    @Override
    public void insertRole(String rolesResource) {
        if (rolesResource.equals("1")) {
            userMapper.insertRole(rolesResource, "超级管理员");
        } else if (rolesResource.equals("2")) {
            userMapper.insertRole(rolesResource, "管理员");
        } else if (rolesResource.equals("3")) {
            userMapper.insertRole(rolesResource, "活动审核员");
        } else if (rolesResource.equals("4")) {
            userMapper.insertRole(rolesResource, "商城管理员");
        }
    }

    @Override
    public Integer selectUserScoreById(Integer userId) {
        return userMapper.selectUserScoreById(userId);
    }

    @Override
    public Map<String, Object> selectCSList(int page, int size) {
        PageHelper.startPage(page, size);
        List<TbCustomService> list = customeMapper.selectCSList();
        int count = customeMapper.selectCount();
        return ResultFul.getListMap(list, count);
    }

    @Transactional
    @Override
    public Map<String, Object> deleteSC(String ids) {
        if (ids.isEmpty()) {
            return ResultFul.getMessageMap("id不能为空");
        }
        customeMapper.deleteSC(ids.split(","));
        return ResultFul.getStatusMap();
    }

    @Transactional
    @Override
    public Map<String, Object> openSC(String ids) {
        if (ids.isEmpty()) {
            return ResultFul.getMessageMap("id不能为空");
        }
        //判断现在是否有推荐的客服
        int count = customeMapper.selectIsExistIsTop();
        if (count != 0) {
            Byte customServiceId = customeMapper.getIsTopCustomId();
            asyncService.updateCustomeService(customServiceId);
        }
        customeMapper.openSC(ids.split(","));
        return ResultFul.getStatusMap();
    }

    @Transactional
    @Override
    public Map<String, Object> disabledSC(String ids) {
        if (ids.isEmpty()) {
            return ResultFul.getMessageMap("id不能为空");
        }
        customeMapper.disabledSC(ids.split(","));
        return ResultFul.getStatusMap();
    }
}
