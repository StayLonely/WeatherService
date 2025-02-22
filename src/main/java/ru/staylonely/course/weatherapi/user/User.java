package ru.staylonely.course.weatherapi.user;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.aot.generate.GeneratedMethod;

import java.lang.reflect.Method;

@Entity
@NoArgsConstructor(force = true)
@Getter
@Setter
@Table(name = "users")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String city;

    public User(String username, String password, String city) {
        this.city = city;
        this.password = password;
        this.username = username;
    }
}
