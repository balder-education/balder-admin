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
import br.edu.unitri.model.Content.Course;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class CourseDao extends GenericDAO<Long, Course> implements IGenericDao<Course>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(Course entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Course> findByEntity(Course entity) {
		
		CriteriaQuery<Course> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Course> listAllEntity(Course entity, int first, int max) {

		CriteriaQuery<Course> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(Course entity, CriteriaBuilder cb, Root<Course> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getDescription() != null) {
			predicados.add(cb.equal(root.get("description"), entity.getDescription()));
		}
		if (entity.getInitialDate() != null) {
			predicados.add(cb.equal(root.get("initialDate"), entity.getInitialDate()));
		}		
		if (entity.getEndDate() != null) {
			predicados.add(cb.equal(root.get("endDate"), entity.getEndDate()));
		}		
		if (entity.getTeacher() != null) {
			predicados.add(cb.equal(root.get("teacher"), entity.getTeacher()));
		}		
		return predicados;
	}

	@Override
	public CriteriaQuery<Course> query(Course entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Course> query = cb.createQuery(Course.class);
		Root<Course> root = query.from(Course.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
