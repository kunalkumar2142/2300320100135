package vehicle_maintence_scheduler.online.Repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import vehicle_maintence_scheduler.online.Model.Depot;
import vehicle_maintence_scheduler.online.Model.DepotResponse;
import vehicle_maintence_scheduler.online.Model.Vehicle;
import vehicle_maintence_scheduler.online.Model.VehicleResponse;
import vehicle_maintence_scheduler.online.Service.AuthService;

import java.util.Collections;
import java.util.List;

@Repository
public class DataRepository {

    private final RestTemplate restTemplate;
    private final AuthService authService;

    @Value("${api.base-url}")
    private String baseUrl;

    public DataRepository(RestTemplate restTemplate, AuthService authService) {
        this.restTemplate = restTemplate;
        this.authService = authService;
    }

    public List<Depot> getDepots() {
        String url = baseUrl + "/depots";

        HttpEntity<Void> request = new HttpEntity<>(createAuthHeaders());

        DepotResponse response = restTemplate
                .exchange(url, HttpMethod.GET, request, DepotResponse.class)
                .getBody();

        if (response == null || response.getDepots() == null) {
            return Collections.emptyList();
        }

        return response.getDepots();
    }

    public List<Vehicle> getVehicles() {
        String url = baseUrl + "/vehicles";

        HttpEntity<Void> request = new HttpEntity<>(createAuthHeaders());

        VehicleResponse response = restTemplate
                .exchange(url, HttpMethod.GET, request, VehicleResponse.class)
                .getBody();

        if (response == null || response.getVehicles() == null) {
            return Collections.emptyList();
        }

        return response.getVehicles();
    }

    private HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authService.getToken());
        return headers;
    }
}