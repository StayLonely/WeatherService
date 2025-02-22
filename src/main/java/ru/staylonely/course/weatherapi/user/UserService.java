package ru.staylonely.course.weatherapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(String username, String password, String city) {
        System.out.println(userRepository.findByUsername(username) != null);
        if(userRepository.findByUsername(username) == null) {

            User user = new User(username, password, city);
            userRepository.save(user);
            return "Пользователь - " + user.getUsername() + " - создан" ;
        }

        return "Пользователь уже существует";
    }

    public String deleteUser(String username) {
        if (userRepository.findByUsername(username) != null) {
            User user = userRepository.findByUsername(username);
            userRepository.delete(user);
            return "Пользователь - " + user.getUsername() + " - удален";
        }
        return "Пользователь не найден";
    }

    public String updateCity(String username, String city) {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            user.setCity(city);
            userRepository.save(user);
            return "Новый город - " + city;
        }
        return "Пользователь не найден";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
