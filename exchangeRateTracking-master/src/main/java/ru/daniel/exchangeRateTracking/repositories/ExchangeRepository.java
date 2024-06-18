package ru.daniel.exchangeRateTracking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daniel.exchangeRateTracking.model.ExchangeDB;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeDB, Integer> {
}
