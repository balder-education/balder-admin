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
import br.edu.unitri.model.Content.Content;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class ContentDao extends GenericDAO<Long, Content> implements IGenericDao<Content>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(Content entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Content> findByEntity(Content entity) {
		
		CriteriaQuery<Content> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Content> listAllEntity(Content entity, int first, int max) {

		CriteriaQuery<Content> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(Content entity, CriteriaBuilder cb, Root<Content> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getDescription() != null) {
			predicados.add(cb.equal(root.get("description"), entity.getDescription()));
		}
		if (entity.getTipoConteudo() != null) {
			predicados.add(cb.equal(root.get("tipoConteudo"), entity.getTipoConteudo()));
		}		
		if (entity.getTipoLevel() != null) {
			predicados.add(cb.equal(root.get("tipoLevel"), entity.getTipoLevel()));
		}		
		return predicados;
	}

	@Override
	public CriteriaQuery<Content> query(Content entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Content> query = cb.createQuery(Content.class);
		Root<Content> root = query.from(Content.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
