package com.drmodi.flightreservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drmodi.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
