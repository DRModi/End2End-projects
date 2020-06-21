package com.drmodi.flightcheckin.integration;

import com.drmodi.flightcheckin.integration.dto.Reservation;
import com.drmodi.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {

	public Reservation findReservation(Long id);
	public Reservation updateReservation(ReservationUpdateRequest request);
}
