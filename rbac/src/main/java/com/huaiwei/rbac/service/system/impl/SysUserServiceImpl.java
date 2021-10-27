package com.huaiwei.rbac.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huaiwei.rbac.entity.system.SysUser;
import com.huaiwei.rbac.mapper.system.SysUserMapper;
import com.huaiwei.rbac.service.system.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yangkun
 * @since 2021-09-07
 */
@Transactional
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private SysUserMapper userMapper;

    @Autowired
    public void setUserMapper(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据登录名和密码登录，待定，以后要修改成权限类型的
     *
     * @param username 用户名
     * @param password 密码
     * @return User 对象
     */
    @Override
    public SysUser findUserLoginNameAndPassword(String username, String password) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        // password = MD5Encoder.encode(password.getBytes(StandardCharsets.UTF_8));
        wrapper.eq("username", username);
        wrapper.eq("password", password);
        return baseMapper.selectOne(wrapper);
    }


    /**
     * user对象分页列表查询 默认每页10个
     *
     * @param page     当前页
     * @param limit    每页的数量
     * @param realName 真实姓名
     * @param deptId   部门id
     * @return
     */
    @Override
    public Page<SysUser> userLists(Integer page, Integer limit, String realName, String deptId, String searchDeptId) {
        //二者存一，解决用户管理依据机构码查询的问题
        Page<SysUser> userPage = new Page<>(page, limit);
        if (deptId != null && !deptId.isEmpty()) {
            searchDeptId = null;
        }
        IPage<SysUser> sysUserIPage = userMapper.selectUserList(userPage, realName, deptId, searchDeptId);
        BeanUtils.copyProperties(sysUserIPage, userPage);
        return userPage;
    }
}
