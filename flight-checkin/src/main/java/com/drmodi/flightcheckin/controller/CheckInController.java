package com.drmodi.flightcheckin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.drmodi.flightcheckin.integration.ReservationRestClient;
import com.drmodi.flightcheckin.integration.dto.Reservation;
import com.drmodi.flightcheckin.integration.dto.ReservationUpdateRequest;

@Controller
public class CheckInController {

	@Autowired
	ReservationRestClient restClient;
	
	@RequestMapping("/showStartCheckin")
	public String showStartCheckin() {
		System.out.println("***** Inside Start CheckIN");
		return "startCheckin";
	}
	
	@RequestMapping("/startCheckin")
	public String startCheckin(@RequestParam("reservationId") Long reservationId, ModelMap map) {
		System.out.println("Reservation ID: "+reservationId);
		Reservation reservation = restClient.findReservation(reservationId);
		System.out.println("Reservation ID from DB: "+reservation.getId());
		map.addAttribute("reservation", reservation);
		return "displayReservationDetails";
	}
	
	@RequestMapping("/completeCheckIn")
	public String completeCheckin(@RequestParam("reservationId") Long reservationId, @RequestParam("numberOfBags")
			int numberOfBags) {
		ReservationUpdateRequest request = new ReservationUpdateRequest();
		request.setCheckedIn(true);
		request.setId(reservationId);
		request.setNumberOfBags(numberOfBags);
		restClient.updateReservation(request);
		return "checkInConfirmation";
	}
}
