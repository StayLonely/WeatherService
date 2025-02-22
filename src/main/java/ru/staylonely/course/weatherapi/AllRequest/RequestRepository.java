package ru.staylonely.course.weatherapi.AllRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.staylonely.course.weatherapi.user.User;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
