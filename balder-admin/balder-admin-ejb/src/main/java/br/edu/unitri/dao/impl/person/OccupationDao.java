/**
 * 
 */
package br.edu.unitri.dao.impl.person;

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
import br.edu.unitri.model.person.Occupation;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class OccupationDao extends GenericDAO<Long, Occupation> implements IGenericDao<Occupation>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(Occupation entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Occupation> findByEntity(Occupation entity) {
		
		CriteriaQuery<Occupation> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Occupation> listAllEntity(Occupation entity, int first, int max) {

		CriteriaQuery<Occupation> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(Occupation entity, CriteriaBuilder cb, Root<Occupation> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getPerson() != null) {
			predicados.add(cb.equal(root.get("person"), entity.getPerson()));
		}
		if (entity.getDescription() != null) {
			predicados.add(cb.equal(root.get("description"), entity.getDescription()));
		}
		return predicados;
	}

	@Override
	public CriteriaQuery<Occupation> query(Occupation entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Occupation> query = cb.createQuery(Occupation.class);
		Root<Occupation> root = query.from(Occupation.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
