package vehicle_maintence_scheduler.online.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import vehicle_maintence_scheduler.online.Model.Depot;
import vehicle_maintence_scheduler.online.Model.Vehicle;

import java.util.List;

@Repository
public class DataRepository {

    private final RestTemplate restTemplate;

    private final String BASE_URL = "http://4.224.186.213/evaluation-service";

    public DataRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Depot> getDepots() {
        return null;
    }

    public List<Vehicle> getVehicles() {
        return null;
    }
}
