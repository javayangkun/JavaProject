package com.huaiwei.rbac.service.system;

import com.huaiwei.rbac.entity.system.SysRole;
import com.huaiwei.rbac.entity.system.SysRoleUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huaiwei.rbac.vo.RoleTransfer;

import java.util.List;

/**
 * <p>
 * 角色用户关系表 服务类
 * </p>
 *
 * @author yangkun
 * @since 2021-10-11
 */
public interface ISysRoleUserService extends IService<SysRoleUser> {

    /**
     * @param roleIds 角色id集合
     * @param userId  用户id
     * @throws Exception 异常
     */
    void delSaveRoleUser(List<String> roleIds, String userId) throws Exception;
}
