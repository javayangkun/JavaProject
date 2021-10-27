package com.huaiwei.rbac.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huaiwei.rbac.common.Result;
import com.huaiwei.rbac.entity.system.SysDepartment;
import com.huaiwei.rbac.service.system.ISysDepartmentService;
import com.huaiwei.rbac.vo.DepartmentTree;
import com.huaiwei.rbac.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author yangkun
 * @since 2021-09-30
 */
@Transactional
@Controller
@RequestMapping("/department")
public class SysDepartmentController {

    private ISysDepartmentService departmentService;

    @Autowired
    public void setDepartmentService(ISysDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/main")
    public String jumpMain() {
        return "system/department/main";
    }

    @ResponseBody
    @GetMapping("/tree")
    public Result tree() {
        List<DepartmentTree> tree = departmentService.selectTree();
        return Result.SUCCESS().data(tree);
    }

    @ResponseBody
    @GetMapping("/list")
    public Result list(String id) {
        QueryWrapper<SysDepartment> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        List<SysDepartment> list = departmentService.list(wrapper);
        return Result.SUCCESS().data(list).count(list.size());
    }


}

