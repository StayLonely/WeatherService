package ru.staylonely.course.weatherapi.AllRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;


    @PostMapping("/createRequest")
    public ResponseEntity<Void> createRequest(@RequestBody Request request) {
        requestService.saveRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/deleteRequest/{id}")
    public String deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return "redirect:/";
    }
}
