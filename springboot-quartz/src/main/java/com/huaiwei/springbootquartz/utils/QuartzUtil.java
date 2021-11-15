package com.huaiwei.springbootquartz.utils;

import com.huaiwei.springbootquartz.domian.QuartzInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class QuartzUtil {
    /*此处应该有操作数据库的类*/

    private final Scheduler scheduler;
    private final JdbcTemplate template;

    @Autowired
    public QuartzUtil(Scheduler scheduler, JdbcTemplate template) {
        this.scheduler = scheduler;
        this.template = template;
    }

    /**
     * 创建定时任务
     *
     * @param info quartz信息bean
     */
    public void createJob(QuartzInfo info) {
        Class<? extends Job> clz = null;
        try {
            clz = (Class<? extends Job>) Class.forName(info.getJobClass());
        } catch (ClassNotFoundException e) {
            log.error("job类出错", e);
        }
        JobDetail detail = JobBuilder.newJob(clz)
                .withDescription(info.getJobDescription())
                .withIdentity(info.getJobName())
                .build();
        CronScheduleBuilder cronSchedule = null;
        try {
            cronSchedule = CronScheduleBuilder.cronSchedule(info.getCronExpression());
        } catch (Exception e) {
            log.error("cron表达式出错", e);
        }
        CronTrigger cron = TriggerBuilder.newTrigger().withDescription(info.getTriggerDescription())
                .withIdentity(info.getTriggerName())
                .withSchedule(cronSchedule)
                .startNow()
                .build();
        try {
            scheduler.scheduleJob(detail, cron);
            /*保存QuartzInfo*/
        } catch (SchedulerException e) {
            log.error("调度定时任务出错", e);
        }
    }

    /**
     * 根据定时任务名称从调度器当中删除定时任务
     *
     * @param scheduler 调度器
     * @param jobName   任务名称（唯一）
     */
    public void deleteScheduleJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.deleteJob(jobKey);
            /*删除 QuartzInfo */
        } catch (SchedulerException e) {
            log.error("删除定时任务出错", e);
        }
    }


    /**
     * 根据任务名称暂停定时任务
     *
     * @param scheduler 调度器
     * @param jobName   任务名称（唯一）
     */
    public void pauseScheduleJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.pauseJob(jobKey);
            /*修改info 状态为暂停*/
        } catch (SchedulerException e) {
            log.error("暂停定时任务出错", e);
        }
    }

    /**
     * 根据任务名称恢复定时任务
     *
     * @param scheduler 调度器
     * @param jobName   任务名称（唯一）
     */
    public void resumeScheduleJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.resumeJob(jobKey);
            /*修改status数据为正运行*/
        } catch (SchedulerException e) {
            log.error("恢复定时任务出错", e);
        }
    }


    /**
     * 更新定时任务
     *
     * @param scheduler   调度器
     * @param triggerName 调度器名称
     */
    public void updateScheduleJob(Scheduler scheduler, String triggerName, String cronExpression) {
        try {
            //获取到对应任务的触发器
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName);
            //设置定时任务执行方式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            //重新构建任务的触发器trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //重置对应的job
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("更新定时任务出错", e);
        }
    }

}
