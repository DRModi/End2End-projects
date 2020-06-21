package com.drmodi.flightreservation.repositories;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.drmodi.flightreservation.entities.Flight;


public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity "
			+ "and dateOfDeparture=:dateOfDeparture")
	List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to, @Param("dateOfDeparture") Date departureDate);


/*	@Query("from Flight f where f.departureCity=:departureCity and f.arrivalCity=:arrivalCity and year(f.dateOfDeparture)=year(:departureDate) and month(f.dateOfDeparture)=month(:departureDate) and day(f.dateOfDeparture-1)=day(:departureDate)")
	List<Flight> findFlights(@Param("departureCity") String departCity, @Param("arrivalCity") String arrivalCity, 
			@Param("departureDate") Date departDate);
*/
	
}
