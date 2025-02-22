package ru.staylonely.course.weatherapi.AllRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.staylonely.course.weatherapi.user.User;
import ru.staylonely.course.weatherapi.weather.WeatherResponse;

import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void saveRequest(Request request){
        requestRepository.save(request);
    }

    public void deleteRequest(Long id){
        requestRepository.deleteById(id);
    }

    public List<Request> getAllRequests(){
        return requestRepository.findAll();
    }
}
