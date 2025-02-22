package ru.staylonely.course.weatherapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserContoller {

    @Autowired
    private final UserService userService;

    public UserContoller(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/createUser")
    public String createUser(
            @ModelAttribute User user
    ) {
        userService.createUser(user.getUsername(),user.getPassword(), user.getCity());
        return "redirect:/" ;
    }

    @PostMapping("/deleteUser")
    public String deleteUser(
            @RequestParam String username
    ){
        userService.deleteUser(username);
        return "redirect:/" ;
    }

    @PostMapping("/updateCity")
    public String updateCity(
            @RequestParam String username,
            @RequestParam String city
    ){
        userService.updateCity(username,city);
        return "redirect:/" ;
    }

}
