package com.huaiwei.rbac.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huaiwei.rbac.common.Result;
import com.huaiwei.rbac.entity.system.SysPermission;
import com.huaiwei.rbac.entity.system.SysUser;
import com.huaiwei.rbac.service.system.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 权限菜单表 前端控制器
 * </p>
 *
 * @author yangkun
 * @since 2021-09-03
 */
@Transactional
@Controller
@RequestMapping("/permission")
public class SysPermissionController {

    private ISysPermissionService permissionService;

    @Autowired
    public void setPermissionService(ISysPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/main")
    public String jumpMain() {
        return "system/permission/main";
    }

    @GetMapping("/contents/open/add")
    public String openContentsAdd() {
        return "system/permission/addContext";
    }

    @GetMapping("/menu/open/add")
    public String openMenuAdd() {
        return "system/permission/addMenu";
    }

    @GetMapping("/contents/open/edit")
    public String openContentsEdit() {
        return "system/permission/editContext";
    }

    @GetMapping("/menu/open/edit")
    public String openMenuEdit() {
        return "system/permission/editMenu";
    }

    @ResponseBody
    @GetMapping(value = "/tree", produces = {"application/json"})
    public Map<String, Object> getPermissionTree(HttpSession session) {
        Map<String, Object> menu = new LinkedHashMap<>();
        //首页信息
        SysPermission homeInfo = new SysPermission();
        homeInfo.setTitle("首页");
        homeInfo.setHref("page/welcome-1.html?t=1");
        menu.put("homeInfo", homeInfo);
        //logo信息
        SysPermission logoInfo = new SysPermission();
        logoInfo.setTitle("LAYUI MINI");
        logoInfo.setImage("images/logo.png");
        logoInfo.setHref("");
        menu.put("logoInfo", logoInfo);
        SysUser user = (SysUser) session.getAttribute("user");
        List<SysPermission> menuInfo = permissionService.selectTree(user.getId());
        menu.put("menuInfo", menuInfo);
        return menu;

    }

    @ResponseBody
    @GetMapping("/list")
    public Result permissionList() {
        QueryWrapper<SysPermission> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("code");
        List<SysPermission> list = permissionService.list(wrapper);
        return Result.SUCCESS().data(list).count(list.size());
    }

    @ResponseBody
    @PostMapping("/menu/add")
    public Result menuAdd(@RequestBody SysPermission permission) throws Exception {
        permission.setType(1);
        boolean save = permissionService.save(permission);
        if (!save) {
            throw new Exception("保存菜单失败");
        }
        return Result.SUCCESS().message("保存菜单成功");
    }

    @ResponseBody
    @PostMapping("/menu/edit")
    public Result menuEdit(@RequestBody SysPermission permission) throws Exception {
        boolean update = permissionService.updateById(permission);
        if (!update) {
            throw new Exception("更新菜单失败");
        }
        return Result.SUCCESS().message("更新菜单成功");
    }

    @PostMapping("/contents/add")
    @ResponseBody
    public Result contentsAdd(@RequestBody SysPermission permission) throws Exception {
        permission.setType(2);
        boolean save = permissionService.save(permission);
        if (!save) {
            throw new Exception("保存目录对象失败");
        }
        return Result.SUCCESS().message("保存目录对象成功");
    }

    @PostMapping("/contents/edit")
    @ResponseBody
    public Result contentsEdit(@RequestBody SysPermission permission) throws Exception {
        permission.setTarget("_self");
        boolean update = permissionService.updateById(permission);
        if (!update) {
            throw new Exception("更新目录对象失败");
        }
        return Result.SUCCESS().message("更新目录对象成功");
    }


    @ResponseBody
    @PostMapping("/contents/delete")
    public Result contentsDelete(@RequestBody SysPermission permission) throws Exception {
        String id = permission.getId();
        QueryWrapper<SysPermission> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", id);
        List<SysPermission> list = permissionService.list(wrapper);
        if (!Objects.requireNonNull(list).isEmpty()) {
            return Result.FAILURE().message("存在子菜单，不能删除");
        }
        boolean remove = permissionService.removeById(id);
        if (!remove) {
            throw new Exception("删除授权菜单对象失败");
        }
        return Result.SUCCESS().message("删除授权菜单对象成功");
    }
}

