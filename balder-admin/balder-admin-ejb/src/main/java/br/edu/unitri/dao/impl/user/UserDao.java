/**
 * 
 */
package br.edu.unitri.dao.impl.user;

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
import br.edu.unitri.model.user.User;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class UserDao extends GenericDAO<Long, User> implements IGenericDao<User>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(User entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<User> findByEntity(User entity) {
		
		CriteriaQuery<User> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<User> listAllEntity(User entity, int first, int max) {

		CriteriaQuery<User> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}
	
	public User findByUser(User entity) {

		CriteriaQuery<User> query = query(entity);
		return manager.createQuery(query).getSingleResult();
		
	}

	@Override
	public List<Predicate> getPredicados(User entity, CriteriaBuilder cb, Root<User> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getRole() != null) {
			predicados.add(cb.equal(root.get("role"), entity.getRole()));
		}
		if (entity.getUserName() != null) {
			predicados.add(cb.equal(root.get("userName"), entity.getUserName()));
		}
		if (entity.getPassword() != null) {
			predicados.add(cb.equal(root.get("password"), entity.getPassword()));
		}
		if (entity.getTipoUsuario() != null) {
			predicados.add(cb.equal(root.get("tipoUsuario"), entity.getTipoUsuario()));
		}
		return predicados;
	}

	@Override
	public CriteriaQuery<User> query(User entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> root = query.from(User.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
