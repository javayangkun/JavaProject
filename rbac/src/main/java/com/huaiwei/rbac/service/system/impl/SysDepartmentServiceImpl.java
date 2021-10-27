package com.huaiwei.rbac.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huaiwei.rbac.entity.system.SysDepartment;
import com.huaiwei.rbac.mapper.system.SysDepartmentMapper;
import com.huaiwei.rbac.service.system.ISysDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaiwei.rbac.vo.DepartmentTree;
import com.huaiwei.rbac.vo.PermissionTree;
import com.huaiwei.rbac.vo.Tree;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author yangkun
 * @since 2021-09-30
 */
@Transactional
@Service
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentMapper, SysDepartment> implements ISysDepartmentService {
    /**
     * 查询部门树
     *
     * @return List<Tree>
     */
    @Override
    public List<DepartmentTree> selectTree() {
        QueryWrapper<SysDepartment> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("code");
        List<SysDepartment> sysDepartments = baseMapper.selectList(wrapper);
        List<DepartmentTree> departmentTree = new ArrayList<>();
        for (SysDepartment department : sysDepartments) {
            DepartmentTree tree = new DepartmentTree();
            tree.setTitle(department.getName());
            tree.setId(department.getId());
            tree.setPid(department.getPid());
            departmentTree.add(tree);
        }
        return buildDepartmentTree(departmentTree);
    }

    private List<DepartmentTree> buildDepartmentTree(List<DepartmentTree> departmentTree) {
        List<DepartmentTree> root = new ArrayList<>();
        for (DepartmentTree tree : departmentTree) {
            if (tree.getPid().equals("0")) {
                root.add(this.buildChildDepartment(tree, departmentTree));
            }
        }
        return root;
    }

    private DepartmentTree buildChildDepartment(DepartmentTree tree, List<DepartmentTree> departmentTree) {
        tree.setChildren(new ArrayList<>());
        for (DepartmentTree t : departmentTree) {
            if (t.getPid().equals(tree.getId())) {
                tree.getChildren().add(this.buildChildDepartment(t, departmentTree));
            }
        }
        return tree;
    }
}
