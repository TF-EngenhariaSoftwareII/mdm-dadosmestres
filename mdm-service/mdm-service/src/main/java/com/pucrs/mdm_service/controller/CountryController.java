package com.pucrs.mdm_service.controller;

import com.pucrs.mdm_service.entities.Country;
import com.pucrs.mdm_service.service.CountryService;
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
    public Country createCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }
}
