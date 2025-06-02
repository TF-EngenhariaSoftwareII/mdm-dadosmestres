package com.pucrs.mdm_service.controller;

import com.pucrs.mdm_service.entities.Country;
import com.pucrs.mdm_service.entities.Currency;
import com.pucrs.mdm_service.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<Void> createCountry(@RequestBody Country country) {
        if (country.getCurrencies() != null) {
            for (Currency currency : country.getCurrencies()) {
                currency.setCountry(country);
            }
        }

        countryService.saveCountry(country);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        try {
            Country country = countryService.getCountryById(id);
            return ResponseEntity.ok(country);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country country) {
        try {
            countryService.getCountryById(id); 
            country.setId(id);

            if (country.getCurrencies() != null) {
                for (Currency currency : country.getCurrencies()) {
                    currency.setCountry(country);
                }
            }

            Country updatedCountry = countryService.updateCountry(country);
            return ResponseEntity.ok(updatedCountry);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        try {
            countryService.getCountryById(id); 
            countryService.deleteCountry(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Country> getCountryByName(@PathVariable String name) {
        try {
            Country country = countryService.getCountryByName(name);
            return ResponseEntity.ok(country);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/currencies")
    public ResponseEntity<List<Currency>> getCountryCurrencies(@PathVariable Long id) {
        try {
            Country country = countryService.getCountryById(id);
            return ResponseEntity.ok(country.getCurrencies());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/exists/by-name/{name}")
    public ResponseEntity<Boolean> countryExistsByName(@PathVariable String name) {
        return ResponseEntity.ok(countryService.countryExistsByName(name));
    }

    @GetMapping("/exists/by-id/{id}")
    public ResponseEntity<Boolean> countryExistsById(@PathVariable Long id) {
        return ResponseEntity.ok(countryService.countryExistsById(id));
    }
}