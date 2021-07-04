package com.ficticiusclean.fleetsmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ficticiusclean.fleetsmanagement.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
	Optional<Brand> findByBrand(String brand);
}
