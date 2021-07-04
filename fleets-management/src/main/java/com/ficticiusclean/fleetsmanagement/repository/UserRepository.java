package com.ficticiusclean.fleetsmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ficticiusclean.fleetsmanagement.model.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
	Optional<Users> findByLogin(String login);
}
