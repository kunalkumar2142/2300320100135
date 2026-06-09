package vehicle_maintence_scheduler.online.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vehicle_maintence_scheduler.online.Model.AuthResponse;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final RestTemplate restTemplate;

    @Value("${api.base-url}")
    private String baseUrl;

    @Value("${api.email}")
    private String email;

    @Value("${api.password}")
    private String password;

    private String token;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getToken() {
        if (token == null) {
            register();
            token = login();
        }
        return token;
    }

    private void register() {
        String url = baseUrl + "/auth/register";

        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            restTemplate.postForObject(url, request, Void.class);
        } catch (Exception e) {
            // user may already exist
        }
    }

    private String login() {
        String url = baseUrl + "/auth/login";

        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        AuthResponse response = restTemplate.postForObject(url, request, AuthResponse.class);

        if (response == null || response.getToken() == null) {
            throw new RuntimeException("Login failed: token is null. Check email/password and auth URL.");
        }
        return response.getToken();
    }
}