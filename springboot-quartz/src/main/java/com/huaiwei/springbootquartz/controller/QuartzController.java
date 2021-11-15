package com.huaiwei.springbootquartz.controller;

import com.huaiwei.springbootquartz.domian.QuartzInfo;
import com.huaiwei.springbootquartz.utils.QuartzUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartz")
public class QuartzController {

    private final QuartzUtil quartzUtil;

    @Autowired
    public QuartzController(QuartzUtil quartzUtil) {
        this.quartzUtil = quartzUtil;
    }

    @PostMapping("/create")
    public String createJob(@RequestBody QuartzInfo info) {
        quartzUtil.createJob(info);
        return "创建成功";
    }

    @PutMapping("/update")
    public String updateJob(String triggerName, String cronExpression) {
        quartzUtil.updateScheduleJob(triggerName, cronExpression);
        return "更新成功";
    }

    @GetMapping("/pause/{jobName}")
    public String pauseJob(@PathVariable String jobName) {
        quartzUtil.pauseScheduleJob(jobName);
        return "暂停成功";
    }


    @DeleteMapping("/delete/{jobName}")
    public String deleteJob(@PathVariable String jobName) {
        quartzUtil.deleteScheduleJob(jobName);
        return "删除成功";
    }

    @GetMapping("/resumeJob/{jobName}")
    public String resumeJob(@PathVariable String jobName) {
        quartzUtil.resumeScheduleJob(jobName);
        return "恢复成功";
    }

    @GetMapping("/list")
    public List<QuartzInfo> ListJob() {
        return quartzUtil.quartzInfoList();
    }
}
