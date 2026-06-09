package vehicle_maintence_scheduler.online.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Depot {
    @JsonProperty("ID")
    private int id;

    @JsonProperty("MechanicHours")
    private int mechanicHours;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMechanicHours() { return mechanicHours; }
    public void setMechanicHours(int mechanicHours) { this.mechanicHours = mechanicHours; }
}
