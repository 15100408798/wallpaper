package com.yushang.wallpaper.common.config.Shiro.permission;

import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.pojo.shiro.TbPermission;
import com.yushang.wallpaper.layer.router.user.ShiroPermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
public class ReloadPermission implements ApplicationListener<ApplicationContextEvent>{
	
	@Resource
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	@Resource
	private ShiroPermissionService shiroPermissionService;

	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		//获得权限集合
		List<String> permissionList = shiroPermissionService.selectPermissionList();
		//1、获取controller中所有带有@RequestMapper标签的方法
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
		//2、获得方法的详情
		Collection<HandlerMethod> methodsValue = handlerMethods.values();
		//循环遍历、添加权限
		for(HandlerMethod handlerMethod : methodsValue){
			//3、遍历所有方法，判断当前方法是否贴有@RequiresPermissions权限控制标签
			RequiresPermissions requiresAnnotation = handlerMethod.getMethodAnnotation(RequiresPermissions.class);
			RequiresRoles rolesAnnotation = handlerMethod.getMethodAnnotation(RequiresRoles.class);
			if(requiresAnnotation != null && rolesAnnotation != null){
				//获得注解上的内容
				String requiresResource = requiresAnnotation.value()[0];
				//验证是否已经存入了该权限
				if (permissionList.contains(requiresResource)){
					//结束本次循环
					continue;
				}
				TbPermission tbPermission = new TbPermission();
				//存入该权限
				String permissionName = handlerMethod.getMethodAnnotation(PermissionName.class).value();
				tbPermission.setName(permissionName);
				tbPermission.setValue(requiresResource);
				//存入数据库
				shiroPermissionService.insertPermission(tbPermission);

				String[] rolesValue = rolesAnnotation.value();
				//存入权限与角色关系表
				shiroPermissionService.insertRolePermission(rolesValue,tbPermission.getPermissionId());

			}
		}
	}

}
