package vehicle_maintence_scheduler.online.Model;

import java.util.List;

public class ScheduleResult {

    private int depotId;
    private int maxImpact;
    private int totalDurationUsed;
    private List<String> scheduledTaskIds;

    public ScheduleResult(int depotId, int maxImpact, int totalDurationUsed, List<String> scheduledTaskIds) {
        this.depotId = depotId;
        this.maxImpact = maxImpact;
        this.totalDurationUsed = totalDurationUsed;
        this.scheduledTaskIds = scheduledTaskIds;
    }

    public int getDepotId() {
        return depotId;
    }

    public int getMaxImpact() {
        return maxImpact;
    }

    public int getTotalDurationUsed() {
        return totalDurationUsed;
    }

    public List<String> getScheduledTaskIds() {
        return scheduledTaskIds;
    }
}
