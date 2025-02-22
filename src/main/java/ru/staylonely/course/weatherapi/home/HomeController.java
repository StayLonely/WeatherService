package ru.staylonely.course.weatherapi.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.staylonely.course.weatherapi.AllRequest.RequestService;
import ru.staylonely.course.weatherapi.user.User;
import ru.staylonely.course.weatherapi.user.UserRepository;
import ru.staylonely.course.weatherapi.user.UserService;
import ru.staylonely.course.weatherapi.weather.CityCoordinates;

@Controller
public class HomeController {

    @Autowired
    public UserService userService;

    @Autowired
    public RequestService requestService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("requests", requestService.getAllRequests());
        return "index";
    }

    @GetMapping("/createUser")
    public String createUser(Model model, CityCoordinates coordinates) {
        model.addAttribute("user", new User());
        model.addAttribute("cities", coordinates.getAllCities());
        return "createUser";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "deleteUser";
    }

    @GetMapping("/updateCity")
    public String updateCity(Model model, CityCoordinates coordinates) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("cities", coordinates.getAllCities());
        return "updateCity";
    }

    @GetMapping("/weather")
    public String getWeather(Model model, CityCoordinates coordinates) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("cities", coordinates.getAllCities());
        return "getWeather";
    }

}
