package com.huaiwei.rbac.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huaiwei.rbac.entity.system.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author yangkun
 * @since 2021-09-26
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * @param page  页码
     * @param limit 每页条数
     * @param name  角色名称
     * @param alias 角色别名
     * @return Page<SysRole>
     */
    Page<SysRole> roleLists(Integer page, Integer limit, String name, String alias);

    /**
     * 查询已被分配的角色
     *
     * @return List<SysRole>
     */
    List<SysRole> distributedList(String userId);

    /**
     * 查询未被分配的角色
     *
     * @return List<SysRole>
     */
    List<SysRole> undistributedList(String userId);
}
