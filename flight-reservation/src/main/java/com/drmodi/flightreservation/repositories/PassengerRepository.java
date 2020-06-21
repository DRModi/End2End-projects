package com.drmodi.flightreservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drmodi.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long>{

}
