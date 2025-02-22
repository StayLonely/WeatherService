package ru.staylonely.course.weatherapi.weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityCoordinates {
    private Map<String, Map<String, Double>> cities;

    public CityCoordinates() {
        cities = new HashMap<>();
        initializeCities();
    }

    private void initializeCities() {
        // Добавляем города и их координаты
        cities.put("New York", createCoordinates(40.7128, -74.0060));
        cities.put("London", createCoordinates(51.5074, -0.1278));
        cities.put("Tokyo", createCoordinates(35.6762, 139.6503));
        cities.put("Sydney", createCoordinates(-33.8688, 151.2093));
        cities.put("Moscow", createCoordinates(55.7558, 37.6173));
        cities.put("Paris", createCoordinates(48.8566, 2.3522));
        cities.put("Rio de Janeiro", createCoordinates(-22.9068, -43.1729));
        cities.put("Cairo", createCoordinates(30.0444, 31.2357));
        cities.put("Mumbai", createCoordinates(19.0760, 72.8777));
        cities.put("Toronto", createCoordinates(43.6510, -79.3470));
        cities.put("Berlin", createCoordinates(52.5200, 13.4050));
    }

    private Map<String, Double> createCoordinates(double lat, double lon) {
        Map<String, Double> coordinates = new HashMap<>();
        coordinates.put("lat", lat);
        coordinates.put("lon", lon);
        return coordinates;
    }

    public Map<String, Double> getCoordinates(String city) {
        return cities.getOrDefault(city, null);
    }

    public List<String> getAllCities(){
        return cities.keySet().stream().toList();
    }


}