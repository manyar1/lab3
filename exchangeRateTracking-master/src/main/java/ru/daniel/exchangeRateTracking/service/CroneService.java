package ru.daniel.exchangeRateTracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.daniel.exchangeRateTracking.repositories.CroneRepository;
import ru.daniel.exchangeRateTracking.model.CroneDB;

@Service
@Transactional(readOnly = true)
public class CroneService {

    private final CroneRepository croneRepository;

    @Autowired
    public CroneService(CroneRepository repository) {
        this.croneRepository = repository;
    }

    @Transactional
    public void save(CroneDB croneDB) {
        croneRepository.save(croneDB);
    }
}
