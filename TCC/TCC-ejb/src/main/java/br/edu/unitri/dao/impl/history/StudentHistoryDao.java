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
import br.edu.unitri.model.history.StudentHistory;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class StudentHistoryDao extends GenericDAO<Long, StudentHistory> implements IGenericDao<StudentHistory>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(StudentHistory entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<StudentHistory> findByEntity(StudentHistory entity) {
		
		CriteriaQuery<StudentHistory> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<StudentHistory> listAllEntity(StudentHistory entity, int first, int max) {

		CriteriaQuery<StudentHistory> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(StudentHistory entity, CriteriaBuilder cb, Root<StudentHistory> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getTexto() != null) {
			predicados.add(cb.equal(root.get("texto"), entity.getTexto()));
		}
		return predicados;
	}

	@Override
	public CriteriaQuery<StudentHistory> query(StudentHistory entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<StudentHistory> query = cb.createQuery(StudentHistory.class);
		Root<StudentHistory> root = query.from(StudentHistory.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
