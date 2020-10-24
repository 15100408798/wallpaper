package com.yushang.wallpaper.layer.service.impl.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.user.UserMapper;
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
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

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

    @Override
    public Integer selectUserScoreById(Integer userId) {
        return userMapper.selectUserScoreById(userId);
    }

}
