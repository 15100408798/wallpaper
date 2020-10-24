package com.yushang.wallpaper.layer.controller.user;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.pojo.user.TbManager;
import com.yushang.wallpaper.layer.model.enums.LogEnum;
import com.yushang.wallpaper.layer.model.user.ManagerQueryModel;
import com.yushang.wallpaper.layer.model.user.UserLoginRequest;
import com.yushang.wallpaper.layer.service.user.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Slf4j
@Api(tags = "登录模块")
@Controller
@RequestMapping(value = "layer/user")
public class LoginController {

    @Resource
    private ManagerService managerService;

    @Log(logEnum = LogEnum.USER_LOGIN, title = "管理员登录", tabName = "tb_manager")
    @ApiOperation(notes = "管理员登录", value = "submitLogin")
    @PostMapping(value = "submitLogin")
    @ResponseBody
    public ResultFul submitLogin(UserLoginRequest userLoginRequest, HttpServletRequest request, HttpServletResponse response) {
        /* 校验参数 */
        Objects.requireNonNull(userLoginRequest);
        userLoginRequest.validUsernameAndPasswordIsNull();
        /* 调用shiro框架进行登录 */
        String username = userLoginRequest.getUsername();   // 登录账号
        String password = userLoginRequest.getPassword();   // 登录密码
        boolean rememberMe = userLoginRequest.isRememberMe();   // 记住我，免登陆
        try {
            // 获得登录的主体
            Subject subject = SecurityUtils.getSubject();
            // 进行封装账号和密码
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
            // 进行登录
            subject.login(token);
            // 根据账号，获取登录者信息
            ManagerQueryModel managerQueryModel = new ManagerQueryModel(1, 1);
            managerQueryModel.setUsername(username);
            ResultFul resultFul = managerService.selectList(managerQueryModel);
            List<TbManager> tbManagerList = (List<TbManager>) resultFul.getRows();
            TbManager tbManager = tbManagerList.get(0);
            request.getSession().setAttribute("manager", tbManager);  // 存入session
            // 登录成功，没有报异常、跳转到之前请求页面
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            if (savedRequest != null) {
                return ResultFul.getSuccessRows(savedRequest.getRequestURI());
            } else {
                return ResultFul.getSuccessRows(request.getContextPath() + "/skip/common/index");
            }
        } catch (UnknownAccountException e) {
            return ResultFul.getErrorMessage("账号不存在");
        } catch (DisabledAccountException e) {
            return ResultFul.getErrorMessage("账号被禁用");
        } catch (IncorrectCredentialsException e) {
            return ResultFul.getErrorMessage("密码不正确");
        } catch (Exception ex) {
            return ResultFul.getErrorMessage("其他异常问题");
        }
    }


    @GetMapping(value = "logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "common/login";
    }

}
