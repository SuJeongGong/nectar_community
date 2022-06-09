package kr.co.nectarsoft.community.scheduler.jobDetail;

import kr.co.nectarsoft.community.scheduler.service.UserSchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * description    : 유저 관련 스케쥴러
 * packageName    : kr.co.nectarsoft.community.scheduler
 * fileName       : UserSchedulerJobDetail
 * author         : GongSuJeong
 * date           : 2022-06-02
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-02        GongSuJeong       최초 생성
 */
@Slf4j
@Component
public class UserSchedulerJobDetail implements Job {
    
    //여기서 Autowired 안됨    
    UserSchedulerService service;

    public void setService(UserSchedulerService service) {
        log.warn("service={}",service);
        this.service = service;
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 스케쥴러가 수행 할 코드
        log.warn("schedule test~~ start");
//        JobDataMap dataMap  = jobExecutionContext.getJobDetail().getJobDataMap();
//        UserSchedulerService service= (UserSchedulerService) dataMap.get("service");
//
//        JobKey key = jobExecutionContext.getJobDetail().getKey();
//        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();  // Note the difference from the previous example

        service.sendMailNotLoginedUser();
        service.deleteUserAfterOneMonth();
        service.withdrawUserAfterSendMail();
        log.warn("schedule test~~ end");
    }

}
