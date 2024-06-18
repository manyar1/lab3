package ru.daniel.exchangeRateTracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.daniel.exchangeRateTracking.repositories.ExchangeRepository;
import ru.daniel.exchangeRateTracking.model.ExchangeDB;

@Service
@Transactional(readOnly = true)
public class ExchangeService {
    private final ExchangeRepository repository;

    @Autowired
    public ExchangeService(ExchangeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(ExchangeDB exchangeDB) {
        repository.save(exchangeDB);
    }
}
