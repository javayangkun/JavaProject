package com.huaiwei.druid.domian;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yangkun
 * @since 2021-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;

    private String username;

    private String password;

    private String realName;

    private String phoneNumber;

    private String deptName;

    private String deptId;

    private Integer isDisabled;

    private Date createTime;

    private Date updateTime;

    private String roleName;
}
