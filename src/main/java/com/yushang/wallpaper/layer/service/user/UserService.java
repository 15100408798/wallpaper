package com.yushang.wallpaper.layer.service.user;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.user.UserQueryModel;
import com.yushang.wallpaper.layer.model.user.UserUpdateModel;
import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * 用户业务层
 */
public interface UserService {

    /**
     * 查询用户集合
     *
     * @param reqModel 查询参数
     * @return 用户信息列表
     */
    @NonNull
    ResultFul selectTbUserList(@NonNull UserQueryModel reqModel);

    /**
     * 更新用户信息
     *
     * @param userUpdateModel 用户信息
     * @return 受影响条数
     */
    @NonNull
    ResultFul updateUserInfo(@NonNull UserUpdateModel userUpdateModel);

    /**
     * 存入数据库
     */
    void insertRole(String rolesResource);

    Integer selectUserScoreById(Integer userId);

    Map<String, Object> selectCSList(int page, int size);

    Map<String, Object> deleteSC(String ids);

    Map<String, Object> openSC(String ids);

    Map<String, Object> disabledSC(String ids);

}
