package com.huaiwei.rbac.controller.system;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huaiwei.rbac.common.Result;
import com.huaiwei.rbac.entity.system.SysRole;
import com.huaiwei.rbac.service.system.ISysRoleService;
import com.huaiwei.rbac.vo.RoleTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author yangkun
 * @since 2021-09-26
 */
@Controller
@RequestMapping("/role")
public class SysRoleController {
    private ISysRoleService roleServices;

    @Autowired
    public void setRoleServices(ISysRoleService roleServices) {
        this.roleServices = roleServices;
    }

    @GetMapping("/main")
    public String jumpRoleMain() {
        return "system/role/main";
    }

    @ResponseBody
    @GetMapping("/list")
    public Result roleList(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit,
                           @RequestParam(required = false) String name, @RequestParam(required = false) String alias
    ) {
        Page<SysRole> rolePage = roleServices.roleLists(page, limit, name, alias);
        long total = rolePage.getTotal();
        List<SysRole> roleLists = rolePage.getRecords();
        return Result.SUCCESS().data(roleLists).count(total);
    }


    @ResponseBody
    @PostMapping("/edit")
    public Result roleEdit(@RequestBody SysRole role) throws Exception {
        boolean update = roleServices.updateById(role);
        if (!update) {
            throw new Exception("更新角色对象失败");
        }
        return Result.SUCCESS().message("更新角色对象成功");
    }

    @ResponseBody
    @GetMapping("/delete")
    public Result roleDelete(String id) throws Exception {
        boolean remove = roleServices.removeById(id);
        if (!remove) {
            throw new Exception("删除角色对象失败");
        }
        return Result.SUCCESS().message("删除角色对象成功");
    }

    @ResponseBody
    @PostMapping("/batch/delete")
    public Result roleBatchDelete(@RequestBody List<String> ids) throws Exception {
        boolean batchDelete = roleServices.removeByIds(ids);
        if (!batchDelete) {
            throw new Exception("批量删除角色对象失败");
        }
        return Result.SUCCESS().message("批量删除角色对象成功");
    }

    @GetMapping("/open/add")
    public String openAdd() {
        return "system/role/add";
    }

    @ResponseBody
    @PostMapping("/add")
    public Result roleAdd(@RequestBody SysRole role) throws Exception {
        boolean save = roleServices.save(role);
        if (!save) {
            throw new Exception("保存角色对象失败");
        }
        return Result.SUCCESS().message("保存角色对象成功");
    }

    @ResponseBody
    @GetMapping("/transfer")
    public Result roleTransfer(@RequestParam String userId) {
        List<SysRole> distributed = roleServices.distributedList(userId);
        List<SysRole> undistributed = roleServices.undistributedList(userId);
        List<RoleTransfer> transferList = new ArrayList<>();
        for (SysRole role : distributed) {
            RoleTransfer transfer = new RoleTransfer();
            transfer.setValue(role.getId());
            transfer.setDistributed(true);
            transfer.setTitle(role.getName());
            transferList.add(transfer);
        }
        for (SysRole role : undistributed) {
            RoleTransfer transfer = new RoleTransfer();
            transfer.setValue(role.getId());
            transfer.setDistributed(false);
            transfer.setTitle(role.getName());
            transferList.add(transfer);
        }
        return Result.SUCCESS().data(transferList);
    }


}

