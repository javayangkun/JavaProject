package com.huaiwei.rbac.vo;

import lombok.Data;

@Data
public class PermissionTree extends Tree {
    private boolean checked;
    private String pid;
}
