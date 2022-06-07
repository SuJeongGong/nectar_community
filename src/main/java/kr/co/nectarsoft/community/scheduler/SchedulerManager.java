package kr.co.nectarsoft.community.scheduler;

import kr.co.nectarsoft.community.scheduler.jobDetail.UserSchedulerJobDetail;
import kr.co.nectarsoft.community.scheduler.service.UserSchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * description    :
 * packageName    : kr.co.nectarsoft.community.scheduler
 * fileName       : SchedulerManager
 * author         : GongSuJeong
 * date           : 2022-06-02
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-02        GongSuJeong       최초 생성
 */
@Slf4j
@Component
public class SchedulerManager {
    //static
    @Autowired
    UserSchedulerService userSchedulerService;

    private SchedulerFactory schedulerFactory;
    private Scheduler scheduler;

    @PostConstruct
    public void start() throws SchedulerException {
        log.warn("실행은 하는거지?");
        schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();

        //UserService 사용하기 위해서
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("service", userSchedulerService);

        // job 지정
        JobDetail job = JobBuilder.newJob(UserSchedulerJobDetail.class).setJobData(dataMap).withIdentity("testJob").build();

        // trigger 지정 - cron식으로
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 5 * * * ?")).build();
//        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("* /1 * * * ?")).build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }

}
