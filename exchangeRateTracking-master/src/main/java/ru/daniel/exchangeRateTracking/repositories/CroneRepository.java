package ru.daniel.exchangeRateTracking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daniel.exchangeRateTracking.model.CroneDB;

@Repository
public interface CroneRepository extends JpaRepository<CroneDB, Integer> {
}
