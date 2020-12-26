package com.yushang.wallpaper.common.mapper.user;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.user.TbUser;
import com.yushang.wallpaper.layer.model.user.user.UserInsertModel;
import com.yushang.wallpaper.layer.model.user.user.UserQueryModel;
import com.yushang.wallpaper.layer.model.user.user.UserUpdateModel;
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
    int updateUserInfo(UserUpdateModel userUpdateModel);

    HashMap<String, Object> selectUser(@Param("userId") int userId);

    /**
     * 添加用户
     *
     * @param userInsertModel 用户信息
     * @return 受影响条数
     */
    int insertTbUserInfo(UserInsertModel userInsertModel);

    /**
     * 微信小程序：验证手机号是否被绑定
     */
    int selectIsExistPhone(@Param("userId") int userId, @Param("phone") String phone);

    HashMap<String, Object> selectUserNameById(@Param("userId") Integer userId);

    Integer selectUserScoreById(@Param("userId") Integer userId);

}
