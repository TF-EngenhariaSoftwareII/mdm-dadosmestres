package com.pucrs.mdm_service.repositories;

import com.pucrs.mdm_service.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}