package com.huaiwei.springbootquartz.utils;

import com.huaiwei.springbootquartz.domian.QuartzInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class QuartzUtil {

    private final Scheduler scheduler;
    private final JdbcTemplate template;

    /**
     * 初始化所有定时任务
     *
     * @param scheduler 调度器
     * @param template  jdbcApi
     */
    @Autowired
    public QuartzUtil(Scheduler scheduler, JdbcTemplate template) {
        this.scheduler = scheduler;
        this.template = template;
        String sql = "select * from quartz_info where status = 1 ";
        /*从数据库中查询所有状态为1的定时任务*/
        List<QuartzInfo> list = this.template.query(sql, new BeanPropertyRowMapper<>(QuartzInfo.class));
        for (QuartzInfo info : list) {
            this.createJob(info);
        }
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
            String sql = "insert into quartz_info(job_name,job_class,job_description,trigger_Name,cronExpression,trigger_Description) " +
                    "values(?,?,?,?,?,?)";
            if (info.getId() == null || info.getId() == -1) {
                template.update(sql, info.getJobName(), info.getJobClass(),
                        info.getJobDescription(), info.getTriggerName(), info.getCronExpression(), info.getTriggerDescription());
            }
        } catch (SchedulerException e) {
            log.error("调度定时任务出错", e);
        }
    }

    /**
     * 根据定时任务名称从调度器当中删除定时任务
     *
     * @param jobName 任务名称（唯一）
     */
    public void deleteScheduleJob(String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.deleteJob(jobKey);
            /*删除 QuartzInfo */
            String sql = "delete from quartz_info where job_name = ?";
            template.update(sql, jobName);
        } catch (SchedulerException e) {
            log.error("删除定时任务出错", e);
        }
    }


    /**
     * 根据任务名称暂停定时任务
     *
     * @param jobName 任务名称（唯一）
     */
    public void pauseScheduleJob(String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.pauseJob(jobKey);
            /*修改info 状态为暂停*/
            String sql = "update quartz_info set status = 0 where job_Name = ?";
            template.update(sql, jobName);
        } catch (SchedulerException e) {
            log.error("暂停定时任务出错", e);
        }
    }

    /**
     * 根据任务名称恢复定时任务
     *
     * @param jobName 任务名称（唯一）
     */
    public void resumeScheduleJob(String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.resumeJob(jobKey);
            /*修改status数据为正运行*/
            String sql = "update quartz_info set status = 1 where job_Name = ?";
            template.update(sql, jobName);
        } catch (SchedulerException e) {
            log.error("恢复定时任务出错", e);
        }
    }


    /**
     * 更新定时任务
     *
     * @param triggerName 调度器名称
     */
    public void updateScheduleJob(String triggerName, String cronExpression) {
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
            /*更新数据库信息*/
            String sql = "update quartz_info set cronExpression = ? where trigger_Name = ? ";
            template.update(sql, cronExpression, triggerName);
        } catch (SchedulerException e) {
            log.error("更新定时任务出错", e);
        }
    }

    /**
     * 所有定时任务列表
     *
     * @return List<QuartzInfo>
     */
    public List<QuartzInfo> quartzInfoList() {
        String sql = "select * from quartz_info";
        return template.query(sql, new BeanPropertyRowMapper<>(QuartzInfo.class));
    }
}
