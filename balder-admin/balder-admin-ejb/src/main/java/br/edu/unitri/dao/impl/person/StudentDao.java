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
import br.edu.unitri.model.person.Student;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class StudentDao extends GenericDAO<Long, Student> implements IGenericDao<Student>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(Student entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Student> findByEntity(Student entity) {
		
		CriteriaQuery<Student> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Student> listAllEntity(Student entity, int first, int max) {

		CriteriaQuery<Student> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(Student entity, CriteriaBuilder cb, Root<Student> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getName() != null) {
			predicados.add(cb.equal(root.get("name"), entity.getName()));
		}
		if (entity.getBurnDate() != null) {
			predicados.add(cb.equal(root.get("burnDate"), entity.getBurnDate()));
		}
		if (entity.getRg() != null) {
			predicados.add(cb.equal(root.get("rg"), entity.getRg()));
		}
		if (entity.getCpf() != null) {
			predicados.add(cb.equal(root.get("cpf"), entity.getCpf()));
		}
		if (entity.getEmail() != null) {
			predicados.add(cb.equal(root.get("email"), entity.getEmail()));
		}
		if (entity.getTipoLevelStudent() != null) {
			predicados.add(cb.equal(root.get("tipoLevelStudent"), entity.getTipoLevelStudent()));
		}
		return predicados;
	}

	@Override
	public CriteriaQuery<Student> query(Student entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Student> query = cb.createQuery(Student.class);
		Root<Student> root = query.from(Student.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
