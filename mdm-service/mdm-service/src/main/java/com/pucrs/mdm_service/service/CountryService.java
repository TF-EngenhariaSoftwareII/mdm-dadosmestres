package com.pucrs.mdm_service.service;

import com.pucrs.mdm_service.entities.Country;
import com.pucrs.mdm_service.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id: " + id));
    }

    public Country updateCountry(Country country) {
        return countryRepository.save(country);
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }

    public Country getCountryByName(String name) {
        return countryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Country not found with name: " + name));
    }

    public boolean countryExistsByName(String name) {
        return countryRepository.findByName(name).isPresent();
    }

    public boolean countryExistsById(Long id) {
        return countryRepository.existsById(id);
    }
}