package ru.lapinlisss.olympic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lapinlisss.olympic_api.model.Sport;

import java.util.Optional;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {

    Optional<Sport> findSportByName(String name);

}
