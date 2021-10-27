package com.huaiwei.rbac.vo;

import lombok.Data;

@Data
public class RoleTransfer {
    private String value;
    private String title;
    private Boolean distributed;//角色是否已经分配
}
