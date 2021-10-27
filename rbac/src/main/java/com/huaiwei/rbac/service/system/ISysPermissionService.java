package com.huaiwei.rbac.service.system;

import com.huaiwei.rbac.entity.system.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huaiwei.rbac.vo.PermissionTree;

import java.util.List;

/**
 * <p>
 * 权限菜单表 服务类
 * </p>
 *
 * @author yangkun
 * @since 2021-09-03
 */
public interface ISysPermissionService extends IService<SysPermission> {
    /**
     * 查找权限菜单树
     *
     * @return List<SysPermission>
     */
    List<SysPermission> selectTree();

    /**
     * 根据用户id查找用户权限树
     *
     * @param userId 用户id
     * @return List<SysPermission>
     */
    List<SysPermission> selectTree(String userId);

    /**
     * 根据角色id查询出角色的权限树
     *
     * @param roleId 角色id
     * @return List<PermissionTree>
     */
    List<PermissionTree> selectPermissionTree(String roleId);
}
