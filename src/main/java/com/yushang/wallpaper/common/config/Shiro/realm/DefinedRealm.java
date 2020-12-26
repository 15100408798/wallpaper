package com.yushang.wallpaper.common.config.Shiro.realm;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.pojo.user.TbManager;
import com.yushang.wallpaper.common.utils.MD5Utils;
import com.yushang.wallpaper.common.utils.redis.RedisUtils;
import com.yushang.wallpaper.layer.model.user.manager.ManagerQueryModel;
import com.yushang.wallpaper.layer.model.user.manager.ManagerUpdateModel;
import com.yushang.wallpaper.layer.router.user.ManagerService;
import com.yushang.wallpaper.layer.router.user.ShiroPermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

public class DefinedRealm extends AuthorizingRealm {

    @Resource
    private ManagerService managerService;

    @Resource
    private ShiroPermissionService shiroPermissionService;

    @Resource
    private RedisUtils redisUtils;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从redis里面获取权限信息
//        SimpleAuthorizationInfo userPermission = (SimpleAuthorizationInfo) redisUtils.getValueForOps("userPermission");
//        if (userPermission != null) {
//            return userPermission;
//        }
        //1、获得用户账号
        Short managerId = (Short) principalCollection.getPrimaryPrincipal();
        //2、获得角色
        Set<String> roleSet = shiroPermissionService.getRoleByAccount(managerId);
        //3、获得权限
        Set<String> permissionSet = shiroPermissionService.getPermissionByAccount(roleSet);
        //4、返回结果
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roleSet);
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        //放入缓存
        redisUtils.setValueForOps("userPermission", simpleAuthorizationInfo, 1800);
        return simpleAuthorizationInfo;
    }

    /**
     * 登陆
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //  1、获取账号和密码
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        password = MD5Utils.disrect(password, username);
        //  2、根据账号获取用户信息
        ManagerQueryModel managerQueryModel = new ManagerQueryModel(1, 1);
        managerQueryModel.setUsername(username);
        managerQueryModel.setDeleteFlag(0);
        ResultFul resultFul = managerService.selectList(managerQueryModel);
        List<TbManager> tbManagerList = (List<TbManager>) resultFul.getRows();
        TbManager tbManager = CollectionUtils.isEmpty(tbManagerList) ? null : tbManagerList.get(0);
        //  3、进行判断
        if (tbManager == null) {
            throw new UnknownAccountException();
        } else if (tbManager.getIsUse() == 1) {
            throw new DisabledAccountException();
        } else if (!tbManager.getPassword().equals(password)) {
            throw new IncorrectCredentialsException();
        } else {
            ManagerUpdateModel managerUpdateModel = new ManagerUpdateModel();
            managerUpdateModel.setManagerIds(tbManager.getManagerId().toString());
            managerService.updateTbManager(managerUpdateModel); // 更新最后登陆时间
        }
        //  4、返回信息
        return new SimpleAuthenticationInfo(tbManager.getManagerId(), tbManager.getPassword(), ByteSource.Util.bytes(tbManager.getSalt()), getName());
    }
}
