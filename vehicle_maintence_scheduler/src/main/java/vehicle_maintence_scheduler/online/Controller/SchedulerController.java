package vehicle_maintence_scheduler.online.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vehicle_maintence_scheduler.online.Model.ScheduleResult;
import vehicle_maintence_scheduler.online.Service.SchedulerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
public class SchedulerController {

    private final SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResult>> getSchedule() {
        List<ScheduleResult> results = schedulerService.generateSchedule();
        return ResponseEntity.ok(results);
    }

}
