package vehicle_maintence_scheduler.online.Model;

import lombok.Data;

import java.util.List;

@Data
public class VehicleResponse {

    private List<Vehicle> vehicles;

    public VehicleResponse() {}

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
