package com.drmodi.flightreservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drmodi.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
