package com.drmodi.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drmodi.flightreservation.entities.User;
import com.drmodi.flightreservation.repositories.UserRepository;
import com.drmodi.flightreservation.services.SecurityService;

@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private SecurityService securityService;

	@RequestMapping("/showRegistration")
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage()");
		return "login/registerUser";
	}

	@RequestMapping("/showLogin")
	public String showRLoginPage() {
		LOGGER.info("Inside showRLoginPage()");
		return "login/login";
	}

	@RequestMapping(value = "registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register(), with user info: " + user.toString());
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("email") String userName, @ModelAttribute("password") String password,
			ModelMap map) {
		LOGGER.info("Inside login(), with user login id: " + userName);
		/*
		 * // * UPDATING MANUAL TO SPRING JPA AUTHENTICATION
		 * 
		 * User user = userRepository.findByEmail(userName);
		 * 
		 * if(user!=null && user.getPassword().equals(password)) { return "findFlights";
		 * }else { map.addAttribute("msg", "Invalid credential, please try again!!"); }
		 */

		boolean loggedIn = securityService.login(userName, password);
		if (loggedIn) {
			return "findFlights";
		} else {
			map.addAttribute("msg", "Invalid credential, please try again!!");
		}

		return "login/login";
	}

}
