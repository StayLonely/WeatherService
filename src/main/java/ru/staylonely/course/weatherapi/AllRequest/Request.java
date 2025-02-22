package ru.staylonely.course.weatherapi.AllRequest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.staylonely.course.weatherapi.user.User;
import ru.staylonely.course.weatherapi.weather.Weather;
import ru.staylonely.course.weatherapi.weather.WeatherResponse;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @Embedded
    private WeatherResponse weatherResponse;

    public Request(User user, WeatherResponse weatherResponse) {
        this.user = user;
        this.weatherResponse = weatherResponse;
    }
}
