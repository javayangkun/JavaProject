package com.huaiwei.rbac.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huaiwei.rbac.common.Result;
import com.huaiwei.rbac.entity.system.SysRole;
import com.huaiwei.rbac.entity.system.SysRolePermission;
import com.huaiwei.rbac.service.system.ISysPermissionService;
import com.huaiwei.rbac.service.system.ISysRolePermissionService;
import com.huaiwei.rbac.service.system.ISysRoleService;
import com.huaiwei.rbac.vo.PermissionTree;
import com.huaiwei.rbac.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/authority")
public class AuthorityController {
    private ISysRoleService roleServices;

    private ISysPermissionService permissionService;

    private ISysRolePermissionService rolePermissionService;

    @Autowired
    public void setRoleServices(ISysRoleService roleServices) {
        this.roleServices = roleServices;
    }

    @Autowired
    public void setPermissionService(ISysPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setRolePermissionService(ISysRolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    /**
     * 跳转到授权页面视图
     *
     * @return String 视图名称
     */
    @GetMapping("/main")
    public String jumpMain() {
        return "system/authorization/main";
    }

    /**
     * 返回角色树
     *
     * @return Result 统一json对象
     */
    @ResponseBody
    @GetMapping("/role/tree")
    public Result roleTree() {
        List<SysRole> list = roleServices.list();
        //顶层
        Tree tree = new Tree();
        tree.setDisabled(true);
        tree.setTitle("角色一览");
        List<Tree> roleTreeList = new ArrayList<>();
        for (SysRole role : list) {
            Tree roleTree = new Tree();
            roleTree.setId(role.getId());
            roleTree.setTitle(role.getName());
            roleTreeList.add(roleTree);
        }
        tree.setChildren(roleTreeList);
        return Result.SUCCESS().data(tree);
    }

    @ResponseBody
    @GetMapping("/permission/tree")
    public Result permissionTree(String roleId) {
        List<PermissionTree> permissionTrees = permissionService.selectPermissionTree(roleId);
        return Result.SUCCESS().data(permissionTrees).count(permissionTrees.size());
    }

    @ResponseBody
    @PostMapping("/role-permission/save")
    public Result rolePermissionSave(@RequestParam(value = "permissionIds[]", required = false) List<String> permissionIds, String roleId) {
        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        if (null == permissionIds || permissionIds.isEmpty()) {
            rolePermissionService.remove(queryWrapper);
            return Result.SUCCESS();
        }
        List<SysRolePermission> list = rolePermissionService.list(queryWrapper);
        if (!list.isEmpty()) {
            rolePermissionService.remove(queryWrapper);
        }

        List<SysRolePermission> rolePermissionList = new ArrayList<>();
        for (String id : permissionIds) {
            SysRolePermission rolePermission = new SysRolePermission();
            rolePermission.setPermissionId(id);
            rolePermission.setRoleId(roleId);
            rolePermissionList.add(rolePermission);
        }
        rolePermissionService.saveBatch(rolePermissionList);
        return Result.SUCCESS();
    }


}
