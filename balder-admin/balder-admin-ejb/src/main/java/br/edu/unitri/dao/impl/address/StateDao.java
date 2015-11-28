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
import br.edu.unitri.model.address.State;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class StateDao extends GenericDAO<Long, State> implements IGenericDao<State>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(State entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<State> findByEntity(State entity) {
		
		CriteriaQuery<State> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<State> listAllEntity(State entity, int first, int max) {

		CriteriaQuery<State> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(State entity, CriteriaBuilder cb, Root<State> root) {

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
		if (entity.getCountry() != null) {
			predicados.add(cb.equal(root.get("country"), entity.getCountry()));
		}		
		return predicados;
	}

	@Override
	public CriteriaQuery<State> query(State entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<State> query = cb.createQuery(State.class);
		Root<State> root = query.from(State.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
