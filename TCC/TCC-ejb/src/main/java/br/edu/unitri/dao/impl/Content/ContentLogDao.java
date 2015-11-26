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
import br.edu.unitri.model.Content.ContentLog;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class ContentLogDao extends GenericDAO<Long, ContentLog> implements IGenericDao<ContentLog>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(ContentLog entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ContentLog> findByEntity(ContentLog entity) {
		
		CriteriaQuery<ContentLog> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<ContentLog> listAllEntity(ContentLog entity, int first, int max) {

		CriteriaQuery<ContentLog> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(ContentLog entity, CriteriaBuilder cb, Root<ContentLog> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getStudent() != null) {
			predicados.add(cb.equal(root.get("student"), entity.getStudent()));
		}
		if (entity.getScore() > 0) {
			predicados.add(cb.equal(root.get("score"), entity.getScore()));
		}		
		if (entity.getContent() != null) {
			predicados.add(cb.equal(root.get("content"), entity.getContent()));
		}		
		return predicados;
	}

	@Override
	public CriteriaQuery<ContentLog> query(ContentLog entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<ContentLog> query = cb.createQuery(ContentLog.class);
		Root<ContentLog> root = query.from(ContentLog.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
