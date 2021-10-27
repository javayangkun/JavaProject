package com.huaiwei.rbac.controller.system;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huaiwei.rbac.common.Result;
import com.huaiwei.rbac.entity.system.SysUser;
import com.huaiwei.rbac.service.system.ISysUserService;
import com.huaiwei.rbac.utils.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yangkun
 * @since 2021-09-07
 */
@Transactional
@Controller
@RequestMapping("/user")
public class SysUserController {

    private ISysUserService userService;

    @Autowired
    public void setUserService(ISysUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/main")
    public String jumpUserMain() {
        return "system/user/main";
    }

    @GetMapping("/open/add")
    public String openAdd() {
        return "system/user/add";
    }

    @GetMapping("/open/edit")
    public String openEdit() {
        return "system/user/edit";
    }

    @GetMapping("open/role")
    public String openRole() {
        return "system/user/role";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public Result userList(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit,
                           @RequestParam(required = false) String realName, @RequestParam(required = false) String deptId,
                           @RequestParam(required = false) String searchDeptId) {
        Page<SysUser> userPage = userService.userLists(page, limit, realName, deptId, searchDeptId);
        long total = userPage.getTotal();
        List<SysUser> userLists = userPage.getRecords();
        return Result.SUCCESS().data(userLists).count(total);
    }

    @ResponseBody
    @PostMapping("/add")
    public Result userAdd(@RequestBody SysUser user) throws Exception {
        user.setPassword("111111");
        boolean save = userService.save(user);
        if (!save) {
            throw new Exception("保存用户对象失败");
        }
        return Result.SUCCESS().message("保存用户对象成功");
    }

    @ResponseBody
    @PostMapping("/edit")
    public Result userUpdate(SysUser user) throws Exception {

        boolean save = userService.updateById(user);
        if (!save) {
            throw new Exception("更新用户对象失败");
        }
        return Result.SUCCESS().message("更新用户对象成功");
    }

    @ResponseBody
    @GetMapping("/isDisabled")
    public Result userStatus(int isDisabled, String id) throws Exception {
        if (isDisabled == UserStatus.ENABLED) {
            isDisabled = UserStatus.DISABLED;
        } else {
            isDisabled = UserStatus.ENABLED;
        }
        UpdateWrapper<SysUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_disabled", isDisabled);
        updateWrapper.eq("id", id);
        boolean update = userService.update(updateWrapper);
        if (!update) {
            throw new Exception("更新用户状态失败");
        }
        return Result.SUCCESS().message("更新用户状态成功");
    }
}

