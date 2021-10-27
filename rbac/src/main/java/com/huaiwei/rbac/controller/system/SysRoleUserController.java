package com.huaiwei.rbac.controller.system;


import com.huaiwei.rbac.common.Result;
import com.huaiwei.rbac.entity.system.SysRole;
import com.huaiwei.rbac.service.system.ISysRoleUserService;
import com.huaiwei.rbac.vo.RoleTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 * 角色用户关系表 前端控制器
 * </p>
 *
 * @author yangkun
 * @since 2021-10-11
 */
@Controller
@RequestMapping("/role")
public class SysRoleUserController {


    private ISysRoleUserService roleUserService;

    @Autowired
    public void setRoleUserService(ISysRoleUserService roleUserService) {
        this.roleUserService = roleUserService;
    }

    @ResponseBody
    @PostMapping("/user")
    public Result roleUserDelSave(@RequestParam(value = "roleIds[]") List<String> roleIds, String userId) throws Exception {
        roleUserService.delSaveRoleUser(roleIds, userId);
        return Result.SUCCESS().message("分配角色成功");
    }
}

