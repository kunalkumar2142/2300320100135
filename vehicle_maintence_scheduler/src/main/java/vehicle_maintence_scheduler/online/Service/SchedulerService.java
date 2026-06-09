package vehicle_maintence_scheduler.online.Service;

import org.springframework.stereotype.Service;
import vehicle_maintence_scheduler.online.Model.Depot;
import vehicle_maintence_scheduler.online.Model.ScheduleResult;
import vehicle_maintence_scheduler.online.Model.Vehicle;
import vehicle_maintence_scheduler.online.Repository.DataRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SchedulerService {

    private final DataRepository dataRepository;

    public SchedulerService(DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    public List<ScheduleResult> generateSchedule() {
        List<Depot> depots = dataRepository.getDepots();
        List<Vehicle> vehicles = dataRepository.getVehicles();

        List<ScheduleResult> results = new ArrayList<>();

        for(Depot depot : depots){
            ScheduleResult result = scheduleForDepot(depot, vehicles);
            results.add(result);
        }
        return results;
    }

    private ScheduleResult scheduleForDepot(Depot depot, List<Vehicle> vehicles) {
        int capacity = depot.getMechanicHours();
        KnapsackAnswer answer = solveKnapsack(vehicles, capacity);
        return new ScheduleResult(
                depot.getId(),
                answer.maxImpact,
                answer.totalDuration,
                answer.taskIds
        );
    }

    private KnapsackAnswer solveKnapsack(List<Vehicle> vehicles, int capacity) {
        int n = vehicles.size();
        int[][] dp = new int[n+1][capacity+1];
        for (int i=1;i<=n;i++) {
            Vehicle task = vehicles.get(i - 1);
            int weight = task.getDuration();
            int value = task.getImpact();

            for(int w=0;w<=capacity;w++){
                dp[i][w] = dp[i-1][w];
                if(weight<=w){
                    int takeTask = dp[i-1][w-weight] + value;
                    dp[i][w] = Math.max(dp[i][w], takeTask);
                }
            }
        }

        List<String> selectedTaskIds = new ArrayList<>();
        int remainingCapacity = capacity;
        for (int i=n;i>=1;i--){
            if (dp[i][remainingCapacity] != dp[i - 1][remainingCapacity]) {
                Vehicle task = vehicles.get(i-1);
                selectedTaskIds.add(task.getTaskId());
                remainingCapacity -= task.getDuration();
            }
        }
        Collections.reverse(selectedTaskIds);
        int totalDuration = capacity - remainingCapacity;
        int maxImpact = dp[n][capacity];
        return new KnapsackAnswer(maxImpact, totalDuration, selectedTaskIds);
    }
    private static class KnapsackAnswer {
        int maxImpact;
        int totalDuration;
        List<String> taskIds;

        KnapsackAnswer(int maxImpact, int totalDuration, List<String> taskIds) {
            this.maxImpact = maxImpact;
            this.totalDuration = totalDuration;
            this.taskIds = taskIds;
        }
    }
}