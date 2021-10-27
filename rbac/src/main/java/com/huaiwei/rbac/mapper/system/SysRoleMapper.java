package com.huaiwei.rbac.mapper.system;

import com.huaiwei.rbac.entity.system.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author yangkun
 * @since 2021-09-26
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    Set<String> selectRoleNameByUserId(String userId);

    /**
     * 查询已被分配的角色
     *
     * @return List<SysRole>
     */
    List<SysRole> selectDistributedList(String userId);

    /**
     * 查询未被分配的角色
     *
     * @return List<SysRole>
     */
    List<SysRole> selectUndistributedList(String userId);
}
