package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lapinlisss.olympic_api.model.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
