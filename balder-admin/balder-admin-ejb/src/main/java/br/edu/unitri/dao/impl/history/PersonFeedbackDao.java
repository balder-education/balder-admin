/**
 * 
 */
package br.edu.unitri.dao.impl.history;

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
import br.edu.unitri.model.history.PersonFeedback;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class PersonFeedbackDao extends GenericDAO<Long, PersonFeedback> implements IGenericDao<PersonFeedback>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(PersonFeedback entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<PersonFeedback> findByEntity(PersonFeedback entity) {
		
		CriteriaQuery<PersonFeedback> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<PersonFeedback> listAllEntity(PersonFeedback entity, int first, int max) {

		CriteriaQuery<PersonFeedback> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(PersonFeedback entity, CriteriaBuilder cb, Root<PersonFeedback> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getFeedback() != null) {
			predicados.add(cb.equal(root.get("feedback"), entity.getFeedback()));
		}
		return predicados;
	}

	@Override
	public CriteriaQuery<PersonFeedback> query(PersonFeedback entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<PersonFeedback> query = cb.createQuery(PersonFeedback.class);
		Root<PersonFeedback> root = query.from(PersonFeedback.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
