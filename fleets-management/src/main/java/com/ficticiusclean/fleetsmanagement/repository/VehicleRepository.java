package com.ficticiusclean.fleetsmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ficticiusclean.fleetsmanagement.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
}
