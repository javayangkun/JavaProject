package com.huaiwei.rbac.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huaiwei.rbac.entity.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yangkun
 * @since 2021-09-07
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 根据登录名和密码登录，待定，以后要修改成权限类型的
     *
     * @param username 登录名
     * @param password 密码
     * @return User 对象
     */
    SysUser findUserLoginNameAndPassword(String username, String password);

    /**
     * @param currentPage
     * @param pageSize
     * @param realName
     * @param deptId
     * @return
     */
    Page<SysUser> userLists(Integer currentPage, Integer pageSize, String realName, String deptId, String searchDeptId);


}
