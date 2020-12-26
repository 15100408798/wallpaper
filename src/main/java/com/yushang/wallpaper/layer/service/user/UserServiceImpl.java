package com.yushang.wallpaper.layer.service.user;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.enums.StatusEnum;
import com.yushang.wallpaper.common.mapper.user.UserMapper;
import com.yushang.wallpaper.common.pojo.user.TbUser;
import com.yushang.wallpaper.common.utils.MD5Utils;
import com.yushang.wallpaper.layer.model.user.user.UserInsertModel;
import com.yushang.wallpaper.layer.model.user.user.UserQueryModel;
import com.yushang.wallpaper.layer.model.user.user.UserUpdateModel;
import com.yushang.wallpaper.layer.router.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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
        reqModel.startPage();
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
        /** 用户登录名不能重复 */
        UserQueryModel userQueryModel = new UserQueryModel();
        userQueryModel.setNotUserId(Integer.parseInt(userUpdateModel.getUserIds()));
        if (StringUtils.isNotBlank(userUpdateModel.getUsername())) {
            userQueryModel.setUsername(userUpdateModel.getUsername());
            Page<TbUser> usernamePage = userMapper.selectTbUserList(userQueryModel);
            if (!CollectionUtils.isEmpty(usernamePage.getResult())) {
                return ResultFul.getErrorMessage("用户名不能重复");
            }
        }
        /** 校验手机号，手机号不能为重复 */
        if (StringUtils.isNotBlank(userUpdateModel.getMobile())) {
            userQueryModel.setUsername(null);
            userQueryModel.setMobile(userUpdateModel.getMobile());
            Page<TbUser> phonePage = userMapper.selectTbUserList(userQueryModel);
            if (!CollectionUtils.isEmpty(phonePage.getResult())) {
                return ResultFul.getErrorMessage("手机号已经存在");
            }
        }
        userUpdateModel.setUpdateTime(new Date());
        // 更新用户信息
        int updateCount = userMapper.updateUserInfo(userUpdateModel);
        if (updateCount != userIdValues.length) {
            throw new RuntimeException("更新用户信息失败");
        }
        return ResultFul.getSuccessTotal(updateCount);
    }

    @Override
    public Integer selectUserScoreById(Integer userId) {
        return userMapper.selectUserScoreById(userId);
    }

    /**
     * 新增用户信息
     *
     * @param userInsertModel 用户信息
     * @return 受影响条数
     */
    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ResultFul insertUserInfo(UserInsertModel userInsertModel) {
        /* 校验参数 */
        Objects.requireNonNull(userInsertModel);
        userInsertModel.validInsertInfoIsNotNull();
        /** 用户登录名不能重复 */
        UserQueryModel userQueryModel = new UserQueryModel();
        userQueryModel.setUsername(userInsertModel.getUsername());
        Page<TbUser> usernamePage = userMapper.selectTbUserList(userQueryModel);
        if (!CollectionUtils.isEmpty(usernamePage.getResult())) {
            return ResultFul.getErrorMessage("用户名不能重复");
        }
        /** 校验手机号，手机号不能为重复 */
        userQueryModel.setUsername(null);
        userQueryModel.setMobile(userInsertModel.getMobile());
        Page<TbUser> phonePage = userMapper.selectTbUserList(userQueryModel);
        if (!CollectionUtils.isEmpty(phonePage.getResult())) {
            return ResultFul.getErrorMessage("手机号已经存在");
        }
        /** 新增用户信息 */
        userInsertModel.setOpenId("3423424");
        userInsertModel.setScore(0);
        userInsertModel.setMoney(BigDecimal.ZERO);
        userInsertModel.setSalt(userInsertModel.getUsername());
        userInsertModel.setPassword(MD5Utils.disrect(userInsertModel.getPassword(), userInsertModel.getUsername()));
        userInsertModel.setCreateTime(new Date());
        userInsertModel.setDeleteFlag(StatusEnum.DELETE_NO.getCode());
        userInsertModel.setIsUse(StatusEnum.AVAILABLE.getCode());
        int insertCount = userMapper.insertTbUserInfo(userInsertModel);
        if (insertCount != 1) {
            throw new RuntimeException("新增用户失败");
        }
        return ResultFul.getSuccessTotal(insertCount);
    }

}
