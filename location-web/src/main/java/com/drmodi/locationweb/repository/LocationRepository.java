package com.drmodi.locationweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.drmodi.locationweb.entities.Location;


public interface LocationRepository extends JpaRepository<Location, Integer> {
	
	//for reporting purpose
	@Query("select type,count(type) from location group by type")
	public List<Object[]> findTypeAndTypeCount();

}
