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
import br.edu.unitri.model.Content.Template;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class TemplateDao extends GenericDAO<Long, Template> implements IGenericDao<Template>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(Template entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Template> findByEntity(Template entity) {
		
		CriteriaQuery<Template> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Template> listAllEntity(Template entity, int first, int max) {

		CriteriaQuery<Template> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(Template entity, CriteriaBuilder cb, Root<Template> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getDescription() != null) {
			predicados.add(cb.equal(root.get("description"), entity.getDescription()));
		}
		if (entity.getContent() != null) {
			predicados.add(cb.equal(root.get("content"), entity.getContent()));
		}		
		return predicados;
	}

	@Override
	public CriteriaQuery<Template> query(Template entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Template> query = cb.createQuery(Template.class);
		Root<Template> root = query.from(Template.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
