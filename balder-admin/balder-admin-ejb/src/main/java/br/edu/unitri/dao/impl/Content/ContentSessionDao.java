/**
 * 
 */
package br.edu.unitri.dao.impl.Content;

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
import br.edu.unitri.model.Content.ContentSession;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class ContentSessionDao extends GenericDAO<Long, ContentSession> implements IGenericDao<ContentSession>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(ContentSession entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ContentSession> findByEntity(ContentSession entity) {
		
		CriteriaQuery<ContentSession> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<ContentSession> listAllEntity(ContentSession entity, int first, int max) {

		CriteriaQuery<ContentSession> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(ContentSession entity, CriteriaBuilder cb, Root<ContentSession> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getInitialTime() != null) {
			predicados.add(cb.equal(root.get("initialTime"), entity.getInitialTime()));
		}
		if (entity.getFinalTime() != null) {
			predicados.add(cb.equal(root.get("finalTime"), entity.getFinalTime()));
		}		
		if (entity.getPerson() != null) {
			predicados.add(cb.equal(root.get("person"), entity.getPerson()));
		}		
		return predicados;
	}

	@Override
	public CriteriaQuery<ContentSession> query(ContentSession entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<ContentSession> query = cb.createQuery(ContentSession.class);
		Root<ContentSession> root = query.from(ContentSession.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
