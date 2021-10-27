package com.huaiwei.rbac.vo;

import lombok.Data;

import java.util.List;

@Data
public class Tree {
    private String title;               //节点标题
    private String id;                  //节点唯一索引值，用于对指定节点进行各类操作
    private boolean spread = true;      //节点是否初始展开，默认 false
    private boolean disabled;           //节点是否为禁用状态。默认 false
    private List<Tree> children;        //子节点。支持设定选项同父节点
    // 可拓展字段
    // private String field;            //节点字段名一般对应表字段名
    // private boolean checked;        //节点是否初始为选中状态（如果开启复选框的话），默认 false	Boolean	true
    // private String href;            //点击节点弹出新窗口对应的 url。需开启 isJump 参数
    // private boolean isJump = false; //是否跳转
}
