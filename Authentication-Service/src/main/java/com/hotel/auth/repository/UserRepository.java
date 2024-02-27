package com.hotel.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hotel.auth.model.User;

public interface UserRepository extends MongoRepository<User, Long>{

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
