package com.drmodi.flightreservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.drmodi.flightreservation.entities.User;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String userName);

}
