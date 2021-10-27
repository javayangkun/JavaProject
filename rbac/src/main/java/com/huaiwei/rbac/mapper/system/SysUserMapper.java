package com.huaiwei.rbac.mapper.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huaiwei.rbac.entity.system.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yangkun
 * @since 2021-09-07
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    IPage<SysUser> selectUserList(IPage<SysUser> page,
                                  @Param(value = "realName") String realName,
                                  @Param(value = "deptId") String deptId,
                                  @Param(value = "searchDeptId") String searchDeptId);
}
