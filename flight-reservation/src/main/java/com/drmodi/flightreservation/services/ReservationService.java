package com.drmodi.flightreservation.services;

import org.springframework.stereotype.Component;
import com.drmodi.flightreservation.dto.PassengerReservationRequest;
import com.drmodi.flightreservation.entities.Reservation;

@Component
public interface ReservationService {
	
	public Reservation bookFlight(PassengerReservationRequest request);

}
