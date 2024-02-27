package com.hotel.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hotel.security.model.Userinfo;

public interface UserRepository extends MongoRepository<Userinfo, Long> {

	Optional<Userinfo> findByName(String username);

}
