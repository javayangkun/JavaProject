package com.huaiwei.rbac.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huaiwei.rbac.entity.system.SysRole;
import com.huaiwei.rbac.entity.system.SysRoleUser;
import com.huaiwei.rbac.mapper.system.SysRoleUserMapper;
import com.huaiwei.rbac.service.system.ISysRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaiwei.rbac.vo.RoleTransfer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色用户关系表 服务实现类
 * </p>
 *
 * @author yangkun
 * @since 2021-10-11
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements ISysRoleUserService {


    /**
     * @param roleIds 角色id集合
     * @param userId  用户id
     * @throws Exception 异常
     */
    @Override
    public void delSaveRoleUser(List<String> roleIds, String userId) throws Exception {
        List<SysRoleUser> roleUserList = new ArrayList<>();
        QueryWrapper<SysRoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        baseMapper.delete(wrapper);
        for (String roleId : roleIds) {
            SysRoleUser roleUser = new SysRoleUser();
            roleUser.setRoleId(roleId);
            roleUser.setUserId(userId);
            roleUserList.add(roleUser);
        }
        boolean saveBatch = this.saveBatch(roleUserList);
        if (!saveBatch) {
            throw new Exception("批量保存角色用户关系对象失败");
        }

    }
}
