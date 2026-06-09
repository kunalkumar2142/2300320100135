package vehicle_maintence_scheduler.online.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponse {

    @JsonProperty("token")
    private String token;
}