package com.huaiwei.rbac.mapper.system;

import com.huaiwei.rbac.entity.system.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huaiwei.rbac.vo.PermissionTree;

import java.util.List;

/**
 * <p>
 * 权限菜单表 Mapper 接口
 * </p>
 *
 * @author yangkun
 * @since 2021-09-03
 */

public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> selectPermissionList(String userId);

    List<SysPermission> selectSysPermissionByroleId(String roleId);

    List<SysPermission> selectUnSysPermissionByroleId(String roleId);
}
