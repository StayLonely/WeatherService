package ru.staylonely.course.weatherapi.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private int cloudiness;
    private int humidity;
    private String precType;
    private int precStrength;
    private int pressure;
    private double temperature;
    private double fahrenheit;
    private double windSpeed;
    private String windDirection;

    // Getters and Setters
}