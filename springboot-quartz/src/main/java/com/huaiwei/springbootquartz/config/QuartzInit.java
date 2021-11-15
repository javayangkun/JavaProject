package com.huaiwei.springbootquartz.config;

import com.huaiwei.springbootquartz.domian.QuartzInfo;
import com.huaiwei.springbootquartz.utils.QuartzUtil;
import org.quartz.Scheduler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class QuartzInit {

    private final JdbcTemplate template;
    private final QuartzUtil quartzUtil;

    @Autowired
    public QuartzInit(QuartzUtil quartzUtil, JdbcTemplate template) {
        this.quartzUtil = quartzUtil;
        this.template = template;
    }

    @Autowired
    public void initQuartzJob() {
        /*从数据库中查询所有状态为1的定时任务,任务启动,懒得写就弄几个模拟数据*/
        String sql = "select * from quartz_info";
        List<QuartzInfo> list = template.query(sql, new BeanPropertyRowMapper<>(QuartzInfo.class));
        for (QuartzInfo info : list) {
            quartzUtil.createJob(info);
        }
    }


}
