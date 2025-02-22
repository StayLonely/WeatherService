package ru.staylonely.course.weatherapi.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable // делаем класс встраиваемым
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    private String city;
    private double currentTemp;
    private int humidity;
    private double windSpeed;
    private String condition;
    private double tomorrowTemp;

}
