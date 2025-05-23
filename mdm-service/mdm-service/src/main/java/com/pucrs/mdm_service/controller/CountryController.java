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
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }
}
