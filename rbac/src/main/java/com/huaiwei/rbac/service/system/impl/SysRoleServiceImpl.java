package com.huaiwei.rbac.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huaiwei.rbac.entity.system.SysRole;
import com.huaiwei.rbac.mapper.system.SysRoleMapper;
import com.huaiwei.rbac.service.system.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author yangkun
 * @since 2021-09-26
 */
@Transactional
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    private SysRoleMapper roleMapper;

    @Autowired
    public void setRoleMapper(SysRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    /**
     * @param page  页码
     * @param limit 每页条数
     * @param name  角色名称
     * @param alias 角色别名
     * @return Page<SysRole>
     */
    @Override
    public Page<SysRole> roleLists(Integer page, Integer limit, String name, String alias) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        Page<SysRole> rolePage = new Page<>(page, limit);
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.eq("name", name);
        }
        if (!StringUtils.isEmpty(alias)) {
            queryWrapper.eq("alias", alias);
        }
        baseMapper.selectPage(rolePage, queryWrapper);
        return rolePage;
    }

    /**
     * 查询已被分配的角色
     *
     * @return List<SysRole>
     */
    @Override
    public List<SysRole> distributedList(String userId) {
        return roleMapper.selectDistributedList(userId);
    }

    /**
     * 查询未被分配的角色
     *
     * @return List<SysRole>
     */
    @Override
    public List<SysRole> undistributedList(String userId) {
        return roleMapper.selectUndistributedList(userId);
    }
}
