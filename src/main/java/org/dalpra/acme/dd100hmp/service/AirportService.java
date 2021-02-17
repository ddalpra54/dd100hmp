package org.dalpra.acme.dd100hmp.service;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.dalpra.acme.dd100hmp.entity.Airport;
import org.dalpra.acme.dd100hmp.repository.AirportRepository;

@ApplicationScoped
public class AirportService {
	
	private final AirportRepository  airportRepository;
	
	@Inject
	public AirportService(AirportRepository airportRepository) {
		this.airportRepository = airportRepository;
	}
	
	@Transactional
	public Airport save(Airport airport) {
		return airportRepository.createOrUpdate(airport);
	}
	
	public List<Airport> getAllAirports() {
		return airportRepository.getAllAirports();
	}
	
	public Airport getAirportFromId(Long id) {
		return airportRepository.getAirportFromId(id);
	}
	
    @Transactional
	public void deleteByid(Long id) {
		airportRepository.deleteById(id);
	}

}