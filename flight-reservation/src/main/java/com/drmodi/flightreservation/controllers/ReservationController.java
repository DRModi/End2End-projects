package com.drmodi.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.drmodi.flightreservation.dto.PassengerReservationRequest;
import com.drmodi.flightreservation.entities.Flight;
import com.drmodi.flightreservation.entities.Reservation;
import com.drmodi.flightreservation.repositories.FlightRepository;
import com.drmodi.flightreservation.services.ReservationService;

@Controller
public class ReservationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	FlightRepository flightRepository;

	@Autowired
	ReservationService reservationService;
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap map) {
		LOGGER.info("Inside showCompleteReservation() invoked with flight id: "+flightId);
		Flight flight = flightRepository.findById(flightId).get();
		LOGGER.info("Inside showCompleteReservation() found flight: "+flight.toString());
		map.addAttribute("flight", flight);
		return "completeReservation";

	}
	
	@RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
	public String completeReservation(PassengerReservationRequest request, ModelMap map) {
		LOGGER.info("Inside completeReservation() invoked with request: "+request);
		Reservation reservation = reservationService.bookFlight(request);
		map.addAttribute("msg", "Reservation created Successfully. The reservation id is " + reservation.getId());
		return "reservationConfirmation";
	}

}

