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
import br.edu.unitri.model.Content.ContentScore;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class ContentScoreDao extends GenericDAO<Long, ContentScore> implements IGenericDao<ContentScore>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(ContentScore entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ContentScore> findByEntity(ContentScore entity) {
		
		CriteriaQuery<ContentScore> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<ContentScore> listAllEntity(ContentScore entity, int first, int max) {

		CriteriaQuery<ContentScore> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(ContentScore entity, CriteriaBuilder cb, Root<ContentScore> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getStudent() != null) {
			predicados.add(cb.equal(root.get("student"), entity.getStudent()));
		}
		if (entity.getContent() != null) {
			predicados.add(cb.equal(root.get("content"), entity.getContent()));
		}		
		if (entity.isStatus() != null) {
			predicados.add(cb.equal(root.get("status"), entity.isStatus()));
		}		
		if (entity.getScore() > 0) {
			predicados.add(cb.equal(root.get("score"), entity.getScore()));
		}		
		return predicados;
	}

	@Override
	public CriteriaQuery<ContentScore> query(ContentScore entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<ContentScore> query = cb.createQuery(ContentScore.class);
		Root<ContentScore> root = query.from(ContentScore.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
