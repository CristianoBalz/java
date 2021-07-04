package com.ficticiusclean.fleetsmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ficticiusclean.fleetsmanagement.model.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
	Optional<Model> findByModel(String model);	
}
