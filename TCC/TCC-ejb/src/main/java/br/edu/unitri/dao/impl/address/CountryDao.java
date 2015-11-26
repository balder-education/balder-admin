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
import br.edu.unitri.model.address.Country;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class CountryDao extends GenericDAO<Long, Country> implements IGenericDao<Country>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(Country entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Country> findByEntity(Country entity) {
		
		CriteriaQuery<Country> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Country> listAllEntity(Country entity, int first, int max) {

		CriteriaQuery<Country> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(Country entity, CriteriaBuilder cb, Root<Country> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getDescription() != null) {
			predicados.add(cb.equal(root.get("description"), entity.getDescription()));
		}
		if (entity.getName() != null) {
			predicados.add(cb.equal(root.get("name"), entity.getName()));
		}		
		return predicados;
	}

	@Override
	public CriteriaQuery<Country> query(Country entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Country> query = cb.createQuery(Country.class);
		Root<Country> root = query.from(Country.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
