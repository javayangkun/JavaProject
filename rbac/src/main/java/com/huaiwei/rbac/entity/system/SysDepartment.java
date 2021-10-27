package com.huaiwei.rbac.entity.system;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author yangkun
 * @since 2021-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysDepartment对象", description="部门表")
public class SysDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "部门编号")
    private String code;

    @ApiModelProperty(value = "部门描述")
    private String describ;

    @ApiModelProperty(value = "父级id")
    private String pid;

    @ApiModelProperty(value = "父级部门名称")
    private String pName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
