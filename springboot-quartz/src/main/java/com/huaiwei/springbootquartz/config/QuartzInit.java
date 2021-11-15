package com.huaiwei.springbootquartz.config;

import com.huaiwei.springbootquartz.domian.QuartzInfo;
import com.huaiwei.springbootquartz.utils.QuartzUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuartzInit {

    private final QuartzUtil quartzUtil;

    @Autowired
    public QuartzInit(QuartzUtil quartzUtil) {
        this.quartzUtil = quartzUtil;
    }

    @Autowired
    public void initQuartzJob() {
        /*从数据库中查询所有状态为1的定时任务,任务启动,懒得写就弄几个模拟数据*/
        String sql = "select * from quartz_info";
        List<QuartzInfo> list = quartzUtil.quartzInfoList();
        for (QuartzInfo info : list) {
            quartzUtil.createJob(info);
        }
    }


}
