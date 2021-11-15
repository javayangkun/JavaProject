package com.huaiwei.springbootquartz.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class QuartzInfo {
    @JsonIgnore
    private Integer id;
    /*任务名称*/
    private String jobName;
    /*任务执行类*/
    private String jobClass;
    /*任务描述*/
    private String jobDescription;
    /*触发器名称*/
    private String triggerName;
    /* 任务运行时间表达式 */
    private String cronExpression;
    /*触发器描述*/
    private String triggerDescription;

    /*任务状态 运行中 1 暂停 0 */
    private Integer status = 1;

}
