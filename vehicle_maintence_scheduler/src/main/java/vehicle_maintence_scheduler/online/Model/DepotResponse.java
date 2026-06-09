package vehicle_maintence_scheduler.online.Model;
import lombok.Data;

import java.util.List;

@Data
public class DepotResponse {

    private List<Depot> depots;

    public DepotResponse() {}

    public List<Depot> getDepots() {
        return depots;
    }

    public void setDepots(List<Depot> depots) {
        this.depots = depots;
    }
}
