package com.huaiwei.rbac.service.system.impl;

import com.huaiwei.rbac.entity.system.SysPermission;
import com.huaiwei.rbac.mapper.system.SysPermissionMapper;
import com.huaiwei.rbac.service.system.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaiwei.rbac.vo.PermissionTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限菜单表 服务实现类
 * </p>
 *
 * @author yangkun
 * @since 2021-09-03
 */
@Transactional
@Service
@Slf4j
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    private SysPermissionMapper mapper;

    @Autowired
    public void setMapper(SysPermissionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<SysPermission> selectTree() {
        List<SysPermission> sysPermissions = baseMapper.selectList(null);
        return this.buildTreeMenu(sysPermissions);
    }

    @Override
    public List<SysPermission> selectTree(String userId) {
        List<SysPermission> sysPermissions = mapper.selectPermissionList(userId);
        return this.buildTreeMenu(sysPermissions);
    }

    private List<SysPermission> buildTreeMenu(List<SysPermission> sysPermissions) {
        List<SysPermission> root = new ArrayList<>();
        for (SysPermission permission : sysPermissions) {
            if ("0".equals(permission.getPid())) {
                root.add(this.buildTreeChild(permission, sysPermissions));
            }
        }
        return root;
    }

    private SysPermission buildTreeChild(SysPermission permission, List<SysPermission> sysPermissions) {
        permission.setChild(new ArrayList<>());
        for (SysPermission child : sysPermissions) {
            if (permission.getId().equals(child.getPid())) {
                permission.getChild().add(buildTreeChild(child, sysPermissions));
            }
        }
        return permission;
    }

    /**
     * 根据角色id查询出角色的权限树
     *
     * @param roleId 角色id
     * @return List<PermissionTree>
     */
    @Override
    public List<PermissionTree> selectPermissionTree(String roleId) {
        List<SysPermission> checked = mapper.selectSysPermissionByroleId(roleId);
        List<SysPermission> sysPermissions = baseMapper.selectList(null);
        List<SysPermission> list = new ArrayList<>();

        for (SysPermission p : sysPermissions) {
            for (SysPermission c : checked) {
                //过滤掉父节点
                if (!p.getPid().equals("0") && p.getId().equals(c.getId())) {
                    p.setChecked(true);
                }
            }
            list.add(p);
        }

        List<PermissionTree> p = new ArrayList<>();
        for (SysPermission s : list) {
            String id = s.getId();
            String pid = s.getPid();
            String title = s.getTitle();
            Boolean c = s.getChecked();
            PermissionTree t = new PermissionTree();
            t.setId(id);
            t.setTitle(title);
            t.setPid(pid);
            t.setChecked(c);
            p.add(t);
        }

        return this.buildTreeMenuVO(p);
    }


    private List<PermissionTree> buildTreeMenuVO(List<PermissionTree> sysPermissions) {
        List<PermissionTree> root = new ArrayList<>();
        for (PermissionTree permission : sysPermissions) {
            if ("0".equals(permission.getPid())) {
                root.add(this.buildTreeChild(permission, sysPermissions));
            }
        }
        return root;
    }

    private PermissionTree buildTreeChild(PermissionTree permission, List<PermissionTree> sysPermissions) {
        permission.setChildren(new ArrayList<>());
        for (PermissionTree child : sysPermissions) {
            if (permission.getId().equals(child.getPid())) {
                permission.getChildren().add(buildTreeChild(child, sysPermissions));
            }
        }
        return permission;
    }
}
