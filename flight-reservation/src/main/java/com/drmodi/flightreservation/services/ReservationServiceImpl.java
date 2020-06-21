package com.drmodi.flightreservation.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.drmodi.flightreservation.dto.PassengerReservationRequest;
import com.drmodi.flightreservation.entities.Flight;
import com.drmodi.flightreservation.entities.Passenger;
import com.drmodi.flightreservation.entities.Reservation;
import com.drmodi.flightreservation.repositories.FlightRepository;
import com.drmodi.flightreservation.repositories.PassengerRepository;
import com.drmodi.flightreservation.repositories.ReservationRepository;
import com.drmodi.flightreservation.util.EmailUtil;
import com.drmodi.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.drmodi.flightreservation.itinerary.dirpath}")
	private String ITINERARY_STORED_DIR;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Override
	@Transactional
	public Reservation bookFlight(PassengerReservationRequest request) {
		
		//First check the card details
		//Validate the card details with payment gateway services
		//Once it get successful, proceed with passenger, reservation, etc.
		
	LOGGER.info("Inside bookFlight()");
	
	Long flightId = request.getFlightId();
	LOGGER.info("Fetching Flight for id: "+flightId);
	Flight flight = flightRepository.findById(flightId).get();
	
	
	Passenger passenger = new Passenger();
	passenger.setFirstName(request.getPassengerFirstName());
	passenger.setLastName(request.getPassengerLastName());
	passenger.setEmail(request.getPassengerEmail());
	passenger.setPhone(request.getPassengerPhone());
	LOGGER.info("Saving the passenger: "+passenger);
	Passenger savedPassenger = passengerRepository.save(passenger);
		
	Reservation reservation = new Reservation();
	reservation.setFlight(flight);
	reservation.setPassenger(savedPassenger);
	reservation.setCheckedIn(false);
	LOGGER.info("Saving the reservation: "+reservation);
	Reservation savedReservation = reservationRepository.save(reservation);
	
	
	//String filePath = "/Users/omsairam/Documents/ddrive/training/LocalDocs/reservations/reservation_"+savedReservation.getId()+"_"+savedReservation.getPassenger().getLastName()+".pdf";
	String fileName = "/reservation_"+savedReservation.getId()+"_"+savedReservation.getPassenger().getLastName()+".pdf";
	String filePath = ITINERARY_STORED_DIR+fileName;
	
	//Generate Itinerary
	LOGGER.info("Generating Itinerary with a name: "+filePath);
	
	pdfGenerator.generateItinerary(savedReservation, filePath);
		
	//Send Email
	LOGGER.info("Emailing Itinerary to email: "+ passenger.getEmail() + " - with name: "+filePath);
	emailUtil.sendItinerary(passenger.getEmail(), filePath);
	
	return savedReservation;
	}

}
