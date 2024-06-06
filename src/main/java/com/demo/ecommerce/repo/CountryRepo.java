package com.demo.ecommerce.repo;

import com.demo.ecommerce.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CountryRepo extends JpaRepository<Country, UUID> {
}
