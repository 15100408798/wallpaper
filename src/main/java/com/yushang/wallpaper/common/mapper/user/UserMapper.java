package com.yushang.wallpaper.common.mapper.user;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.user.TbUser;
import com.yushang.wallpaper.layer.model.user.UserQueryModel;
import com.yushang.wallpaper.layer.model.user.UserUpdateModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

@Mapper
public interface UserMapper {

    /**
     * 查询用户信息列表
     *
     * @param reqModel 查询参数
     * @return 用户信息列表
     */
    Page<TbUser> selectTbUserList(UserQueryModel reqModel);

    /**
     * 更新用户信息
     *
     * @param userUpdateModel 用户信息
     * @return 受影响条数
     */
    int updateUserIsUseStatus(UserUpdateModel userUpdateModel);

    HashMap<String, Object> selectUser(@Param("userId") int userId);

    /**
     * 微信小程序：通过openid查询是否有该用户
     */
    int selectUserByOpenId(@Param("openid") String openid);

    /**
     * 微信小程序：通过openid获取用户信息
     */
    HashMap<String, Object> getUserByOpenId(@Param("openid") String openid);

    /**
     * 微信小程序：添加用户
     */
    void insertTbUser(TbUser tbUser);

    /**
     * 微信小程序：通过openid查看用户是否被禁用
     */
    int selectUserIsUse(@Param("openid") String openid);

    /**
     * 微信小程序：绑定手机号
     */
    void updateMobile(@Param("userId") int userId, @Param("phone") String phone);

    /**
     * 微信小程序：验证手机号是否被绑定
     */
    int selectIsExistPhone(@Param("userId") int userId, @Param("phone") String phone);

    /**
     * 存入数据库
     */
    void insertRole(@Param("roles") String rolesResource, @Param("name") String name);

    HashMap<String, Object> selectUserNameById(@Param("userId") Integer userId);

    Integer selectUserScoreById(@Param("userId") Integer userId);

}
