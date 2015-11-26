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
import br.edu.unitri.model.person.Person;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class PersonDao extends GenericDAO<Long, Person> implements IGenericDao<Person>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(Person entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Person> findByEntity(Person entity) {
		
		CriteriaQuery<Person> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Person> listAllEntity(Person entity, int first, int max) {

		CriteriaQuery<Person> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(Person entity, CriteriaBuilder cb, Root<Person> root) {

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
		return predicados;
	}

	@Override
	public CriteriaQuery<Person> query(Person entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Person> query = cb.createQuery(Person.class);
		Root<Person> root = query.from(Person.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
