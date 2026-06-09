package vehicle_maintence_scheduler.online.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class VehicleResponse {

    @JsonProperty("Vehicles")
    private List<Vehicle> vehicles;
}