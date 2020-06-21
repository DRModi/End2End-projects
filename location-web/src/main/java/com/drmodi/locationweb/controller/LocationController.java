package com.drmodi.locationweb.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.drmodi.locationweb.entities.Location;
import com.drmodi.locationweb.repository.LocationRepository;
import com.drmodi.locationweb.services.LocationService;
import com.drmodi.locationweb.utils.EmailUtil;
import com.drmodi.locationweb.utils.ReportUtil;

@Controller
public class LocationController {
	
	@Autowired
	LocationService service;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	LocationRepository repository;
	
	@Autowired
	ReportUtil reportUtil;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}
	
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap map) {
		Location savedLocation = service.saveLocation(location);
		String msg = "Location Saved with Id: " + savedLocation.getId();
		map.addAttribute("msg", msg);
		
		//Sending email before saving the location
		emailUtil.sendEmail("alerts.dmodi@gmail.com", "Added Location Saved!", "Location called - " +location.getName()+" have been added Successfully!");
		return "createLocation";
		
	}
	
	@RequestMapping("/displayLocations")
	public String viewAllLocations(ModelMap map) {
		List<Location> locationList = service.getAllLocation();
		map.addAttribute("locationList", locationList);
		return "viewAllLocations";
	}
	
	
	@RequestMapping("/deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap map) {
		// Require db call
		//Location locationById = service.getLocationById(id);
		
		Location locationById = new Location();
		locationById.setId(id);
		service.deleteLocation(locationById);
		
		//after delete get all the locations again
		List<Location> locationList = service.getAllLocation();
		map.addAttribute("locationList", locationList);
		
		return "viewAllLocations";
	}
	
	
	@RequestMapping("/editLocation")
	public String showLocation(@RequestParam("id") int id, ModelMap map) {
		Location locationById = service.getLocationById(id);
		map.addAttribute("retrievedLocation", locationById);
		return "showLocation";
	}
	
	
	@RequestMapping("/updateLocation")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap map) {
		
		service.updateLocation(location);
		
		//after delete get all the locations again
		List<Location> locationList = service.getAllLocation();
		map.addAttribute("locationList", locationList);
		
		return "viewAllLocations";
		
	}
	
	
	@RequestMapping("/generateReport")
	public String generateReport() {
		String path=context.getRealPath("/"); 
		List<Object[]> findTypeAndTypeCount = repository.findTypeAndTypeCount();
		reportUtil.generatePieChart(path, findTypeAndTypeCount);
		return "viewReport";
	}

}
