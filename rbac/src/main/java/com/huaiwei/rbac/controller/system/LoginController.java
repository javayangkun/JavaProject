package com.huaiwei.rbac.controller.system;

import com.huaiwei.rbac.common.Result;

import com.huaiwei.rbac.entity.system.SysUser;
import com.huaiwei.rbac.service.system.ISysUserService;
import com.huaiwei.rbac.utils.UserStatus;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 主要功能：
 * 1.用户登录
 * 2.跳转到主页面
 * 3.生成验证码
 * 4.退出登录
 */

@Slf4j
@Controller
public class LoginController {

    private ISysUserService userService;

    @Autowired
    public void setUserService(ISysUserService userService) {
        this.userService = userService;
    }

    /**
     * 跳转到主页主页面
     *
     * @return
     */
    @GetMapping("/index")
    public String jumpMain() {
        return "index";
    }

    /**
     * 用户登录处理
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha  验证码
     * @param request  HttpServletRequest
     * @return Result
     */
    @ResponseBody
    @PostMapping(value = "/login")
    public Result login(String username, String password, String captcha, HttpServletRequest request) {
        if (!CaptchaUtil.ver(captcha, request)) {
            CaptchaUtil.clear(request);  // 清除session中的验证码
            return Result.FAILURE().message("验证码不正确");
        }
        SysUser user = userService.findUserLoginNameAndPassword(username, password);
        if (user == null) {
            return Result.FAILURE().message("用户名或者密码不正确");
        }
        if (user.getIsDisabled() == UserStatus.DISABLED) {
            return Result.FAILURE().message("用户被禁止登录");
        }
        HttpSession session = request.getSession();
        //保存用户session
        session.setAttribute("user", user);
        return Result.SUCCESS().message("登录成功");
    }

    /**
     * 生成验证码，并存储在session中，再次生成时会清除session中的验证码
     * 具体可查看源码
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    @GetMapping("/captcha")
    public void generatorCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtil.out(100, 38, 5, request, response);
    }

    /**
     * 退出登录
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String path = request.getContextPath();
        session.removeAttribute("user");
        response.sendRedirect(path);
    }

}
