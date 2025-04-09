package com.example.garbandgo.service;

import com.example.garbandgo.entities.Country;
import com.example.garbandgo.repositories.CountryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountryRepository() {
        return countryRepository.findAll();
    }

    public void saveAddress(Country address) {
        countryRepository.save(address);
    }
}
