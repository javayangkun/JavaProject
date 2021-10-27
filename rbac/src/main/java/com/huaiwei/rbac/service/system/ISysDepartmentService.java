package com.huaiwei.rbac.service.system;

import com.huaiwei.rbac.entity.system.SysDepartment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huaiwei.rbac.vo.DepartmentTree;
import com.huaiwei.rbac.vo.Tree;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author yangkun
 * @since 2021-09-30
 */
public interface ISysDepartmentService extends IService<SysDepartment> {

    /**
     * 查询部门树
     *
     * @return List<Tree>
     */
    List<DepartmentTree> selectTree();
}
