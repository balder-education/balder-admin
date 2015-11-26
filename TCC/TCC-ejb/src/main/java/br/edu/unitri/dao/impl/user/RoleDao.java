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
import br.edu.unitri.model.user.Role;


/**
 * @author marcos.fernando
 *
 */
@Stateless
@LocalBean
public class RoleDao extends GenericDAO<Long, Role> implements IGenericDao<Role>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public boolean deleteEntity(Role entity) {
		try {
			super.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Role> findByEntity(Role entity) {
		
		CriteriaQuery<Role> query = query(entity);
		return manager.createQuery(query).getResultList();
	}

	@Override
	public List<Role> listAllEntity(Role entity, int first, int max) {

		CriteriaQuery<Role> query = query(entity);
		return manager.createQuery(query).setFirstResult(first).setMaxResults(max).getResultList();
	}

	@Override
	public List<Predicate> getPredicados(Role entity, CriteriaBuilder cb, Root<Role> root) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		if (entity.getId() != null) {
			predicados.add(cb.equal(root.get("id"), entity.getId()));
		}
		if (entity.getRolename() != null) {
			predicados.add(cb.equal(root.get("rolename"), entity.getRolename()));
		}
		if (entity.getAcronym() != null) {
			predicados.add(cb.equal(root.get("acronym"), entity.getAcronym()));
		}
		return predicados;
	}

	@Override
	public CriteriaQuery<Role> query(Role entity) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Role> query = cb.createQuery(Role.class);
		Root<Role> root = query.from(Role.class);

		List<Predicate> predicados = getPredicados(entity, cb, root);
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[] {})));
		}
		return query;
	}

}
