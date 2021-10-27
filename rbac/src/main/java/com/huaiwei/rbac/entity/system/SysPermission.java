package com.huaiwei.rbac.entity.system;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * <p>
 * 权限菜单表
 * </p>
 *
 * @author yangkun
 * @since 2021-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysPermission对象", description = "权限菜单表")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "权限菜单名称")
    private String title;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "访问路径")
    private String href;

    @ApiModelProperty(value = "打开方式 默认为空或者_self")
    private String target;

    @ApiModelProperty(value = "父级菜单id")
    private String pid;

    @ApiModelProperty(value = "父级菜单名称")
    private String pName;

    @ApiModelProperty(value = "资源序号")
    private String code;

    @ApiModelProperty(value = "资源类型 0按钮 1菜单 2目录")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private List<SysPermission> child;

    @TableField(exist = false)
    private String image;

    @TableField(exist = false)
    private Boolean checked = false;


}
