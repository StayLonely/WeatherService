package ru.staylonely.course.weatherapi.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import reactor.core.publisher.Mono;
import ru.staylonely.course.weatherapi.AllRequest.Request;
import ru.staylonely.course.weatherapi.user.User;
import ru.staylonely.course.weatherapi.user.UserRepository;

import java.util.Map;

@Controller
public class WeatherController {

    private WebClient webClient = WebClient.create("http://localhost:8080");

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    private final String API_KEY = "432faa4d-e417-4e2b-97c8-c55c2084c4ae";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/weather")
    public String getWeather(@RequestParam String username,
                                        RedirectAttributes redirectAttributes) {
        try {
            CityCoordinates cityCoordinates = new CityCoordinates();
            User user = userRepository.findByUsername(username);

            if (user == null) {
                redirectAttributes.addFlashAttribute("error", ResponseEntity.badRequest().body("User not found"));
                return "redirect:/weather";
            }

            Map<String, Double> coords = cityCoordinates.getCoordinates(user.getCity());
            if (coords == null) {
                redirectAttributes.addFlashAttribute("error", ResponseEntity.badRequest().body("Coordinates not found for city: " + user.getCity()));
                return "redirect:/weather";
            }

            String apiUrl = String.format(
                    "https://api.weather.yandex.ru/v2/forecast?lat=%s&lon=%s",
                    coords.get("lat"),
                    coords.get("lon")
            );

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Yandex-Weather-Key", API_KEY);
            ResponseEntity<String> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );

            // Парсинг JSON ответа
            Map<String, Object> responseMap = objectMapper.readValue(response.getBody(), Map.class);

            // Извлечение нужных данных
            Map<String, Object> fact = (Map<String, Object>) responseMap.get("fact");
            Map<String, Object> tomorrowForecast = (Map<String, Object>) ((Map<String, Object>)
                    ((Map<String, Object>) ((java.util.List<?>) responseMap.get("forecasts")).get(1))
                            .get("parts")).get("day");

            // Безопасное преобразование числовых значений
            double currentTemp = safeToDouble(fact.get("temp"));
            int humidity = safeToInt(fact.get("humidity"));
            double windSpeed = safeToDouble(fact.get("wind_speed"));
            double tomorrowTemp = safeToDouble(tomorrowForecast.get("temp_avg"));

            // Формирование структурированного ответа
            WeatherResponse weatherResponse = new WeatherResponse(
                    user.getCity(),
                    currentTemp,
                    humidity,
                    windSpeed,
                    (String) fact.get("condition"),
                    tomorrowTemp
            );
            redirectAttributes.addFlashAttribute("weatherResponse", weatherResponse);


            Request request = new Request(user, weatherResponse);


            webClient.post()
                    .uri("/requests/createRequest")
                    .body(Mono.just(request), Request.class)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();

            return "redirect:/weather" ;

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ошибка: " + e.getMessage());
            return "redirect:/weather";
        }
    }

    // Метод для безопасного преобразования в Double
    private double safeToDouble(Object value) {
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        } else if (value instanceof Double) {
            return (Double) value;
        } else if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return 0.0; // или выбросить исключение, если значение не может быть преобразовано
    }

    // Метод для безопасного преобразования в Integer
    private int safeToInt(Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Double) {
            return ((Double) value).intValue();
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return 0; // или выбросить исключение, если значение не может быть преобразовано
    }
}