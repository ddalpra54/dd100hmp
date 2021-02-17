package org.dalpra.acme.dd100hmp.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.dalpra.acme.dd100hmp.entity.Airport;

@ApplicationScoped
public class AirportRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public Airport createOrUpdate(Airport airport) {
		if (airport.getId() == null) {
			this.entityManager.persist(airport);
			return airport;
		} else {
			return this.entityManager.merge(airport);
		}
	}

	@Transactional
	public void deleteById(Long id) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaDelete<Airport> delete = cb.createCriteriaDelete(Airport.class);
		Root<Airport> root = delete.from(Airport.class);
		delete.where(cb.equal(root.get("id"), id));
		this.entityManager.createQuery(delete).executeUpdate();
	}

	public List<Airport> getAllAirports() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Airport> cq = cb.createQuery(Airport.class);
		Root<Airport> rootEntry = cq.from(Airport.class);
		CriteriaQuery<Airport> all = cq.select(rootEntry);
		TypedQuery<Airport> allQuery = entityManager.createQuery(all);
		return allQuery.getResultList();

	}
	
	public Airport getAirportFromId(Long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Airport> cq = cb.createQuery(Airport.class);
		Root<Airport> rootEntry = cq.from(Airport.class);
		cq.where(cb.equal(rootEntry.get("id"), id));
		Airport airport = entityManager.createQuery(cq).getSingleResult();
		return airport;
	}
	
}