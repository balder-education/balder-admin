/**
 * 
 */
package br.edu.unitri.dao.impl.address;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.unitri.dao.GenericDAO;
import br.edu.unitri.dao.IGenericDao;
import br.edu.unitri.model.address.City;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class CityDao extends GenericDAO<Long, City> implements IGenericDao<City>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(City entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<City> findByEntity(City entity) {
		
		CriteriaQuery<City> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<City> listAllEntity(City entity, int first, int max) {

		CriteriaQuery<City> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(City entity, CriteriaBuilder cb, Root<City> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getState() != null) {
			predicados.add(cb.equal(root.get("state"), entity.getState()));
		}
		if (entity.getName() != null) {
			predicados.add(cb.equal(root.get("name"), entity.getName()));
		}		
		return predicados;
	}

	@Override
	public CriteriaQuery<City> query(City entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<City> query = cb.createQuery(City.class);
		Root<City> root = query.from(City.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
